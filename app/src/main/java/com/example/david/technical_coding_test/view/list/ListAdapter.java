package com.example.david.technical_coding_test.view.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.technical_coding_test.IPresenter;
import com.example.david.technical_coding_test.R;
import com.example.david.technical_coding_test.model.IModel;
import com.example.david.technical_coding_test.model.data_model.base.BaseData;
import com.example.david.technical_coding_test.model.data_model.list.ListDataBundle;
import com.example.david.technical_coding_test.model.data_model.list.item.CardData;
import com.example.david.technical_coding_test.model.data_model.list.item.PictureData;
import com.example.david.technical_coding_test.model.data_model.pager.PagerItem;
import com.example.david.technical_coding_test.tools.Tools;

import java.util.ArrayList;

import static com.example.david.technical_coding_test.view.list.ListAdapter.ViewType.CARD;
import static com.example.david.technical_coding_test.view.list.ListAdapter.ViewType.LOADING;
import static com.example.david.technical_coding_test.view.list.ListAdapter.ViewType.PICTURE;

/**
 * Created by David on 2018/1/2.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> implements IListAdapter {


    final class ViewType {
        public final static int UNKNOWN = -1;

        public final static int PICTURE = 0;
        public final static int CARD = 1;
        public final static int LOADING = 2;
    }

    private Context mContext;
    private IPresenter mPresenter;
    private RecyclerView mList;

    /**
     * Parent Data
     **/
    private PagerItem mPagerItem;

    /**
     * Data
     **/
    private ListDataBundle mListDataBundle;
    private boolean mIsLoading;

    public ListAdapter(Context context, IPresenter presenter, RecyclerView list) {
        this.mContext = context;
        this.mPresenter = presenter;
        this.mList = list;

        mIsLoading = false;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        switch (viewType) {
            case PICTURE:
                itemView = inflater.inflate(R.layout.list_item_picture, parent, false);
                break;

            case CARD:
                itemView = inflater.inflate(R.layout.list_item_card, parent, false);
                break;

            case LOADING:
                itemView = inflater.inflate(R.layout.list_item_loading, parent, false);
                break;

            default:
                itemView = inflater.inflate(R.layout.list_item_empty, parent, false);

        }

        return new ItemViewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ArrayList<BaseData> items = mListDataBundle.getItems();

        switch (holder.viewType) {
            case PICTURE:
                PictureData pictureData = (PictureData) items.get(position);

                holder.updatePicture(pictureData);
                break;

            case CARD:
                CardData cardData = (CardData) items.get(position);

                holder.updateCard(cardData);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mListDataBundle == null) {
            return 0;

        } else {
            if (mIsLoading) {
                return mListDataBundle.getItems().size() + 1;

            } else {
                return mListDataBundle.getItems().size();
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mIsLoading && position == getItemCount() - 1) {
            return ViewType.LOADING;

        } else {
            BaseData item = mListDataBundle.getItems().get(position);

            if (item instanceof PictureData) {
                return ViewType.PICTURE;

            } else if (item instanceof CardData) {
                return ViewType.CARD;
            }
        }

        return ViewType.UNKNOWN;
    }

    @Override
    public void update(PagerItem pagerItem) {
        this.mPagerItem = pagerItem;

        showLoadOnDemandProgress();

        mPresenter.getListData(pagerItem, IModel.DownloadMode.NEXT_PAGE, new IModel.Callback<ListDataBundle>() {
            @Override
            public void onResult(boolean isSuccessful, ListDataBundle listDataBundle) {
                ListAdapter.this.mListDataBundle = listDataBundle;

                update();
            }
        });
    }

    private void showLoadOnDemandProgress() {
        mIsLoading = true;
        notifyItemInserted(getItemCount() - 1);
    }

    private void update() {
        switch (mListDataBundle.getDownloadMode()) {
            case IModel.DownloadMode.NEXT_PAGE:

                break;

            case IModel.DownloadMode.PRE_PAGE:
                break;
        }
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private static final float HEIGHT_ITEM_LOADING = 0.1f;
        private static final float HEIGHT_ITEM_PICTURE = 0.2f;
        private static final float HEIGHT_ITEM_CARD = 0.25f;

        int viewType;

        ImageView picture;
        TextView title;
        TextView description;

        public ItemViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;

            initLayout();
        }

        private void initLayout() {
            int height;

            switch (viewType) {
                case PICTURE:
                    height = (int) (Tools.getScreenSize(mContext)[1] * HEIGHT_ITEM_PICTURE);
                    picture = itemView.findViewById(R.id.list_item_picture);
                    itemView.getLayoutParams().height = height;
                    break;

                case CARD:
                    height = (int) (Tools.getScreenSize(mContext)[1] * HEIGHT_ITEM_CARD);
                    picture = itemView.findViewById(R.id.list_item_card_picture);
                    title = itemView.findViewById(R.id.list_item_card_title);
                    description = itemView.findViewById(R.id.list_item_card_description);
                    itemView.getLayoutParams().height = height;
                    break;

                case LOADING:
                    height = (int) (Tools.getScreenSize(mContext)[1] * HEIGHT_ITEM_LOADING);
                    itemView.getLayoutParams().height = height;
                    break;
            }
        }

        public void updatePicture(PictureData pictureData) {

        }

        public void updateCard(CardData cardData) {
            title.setText(cardData.getTitle());
            description.setText(cardData.getDescription());
        }
    }
}

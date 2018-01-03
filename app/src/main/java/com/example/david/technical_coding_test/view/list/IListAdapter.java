package com.example.david.technical_coding_test.view.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.technical_coding_test.R;

import static com.example.david.technical_coding_test.view.list.ListAdapter.ViewType.CARD;
import static com.example.david.technical_coding_test.view.list.ListAdapter.ViewType.PICTURE;

/**
 * Created by David on 2018/1/2.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {

    final class ViewType {
        public final static int PICTURE = 0;
        public final static int CARD = 1;

    }

    private Context mContext;
    private RecyclerView mList;

    public ListAdapter(Context context, RecyclerView list) {
        this.mContext = context;
        this.mList = list;
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

            default:
                itemView = inflater.inflate(R.layout.list_item_empty, parent, false);

        }

        return new ItemViewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        switch (holder.viewType) {
            case PICTURE:
                break;

            case CARD:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

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
            switch (viewType) {
                case PICTURE:
                    picture = itemView.findViewById(R.id.list_item_picture);
                    break;

                case CARD:
                    picture = itemView.findViewById(R.id.list_item_card_picture);
                    title = itemView.findViewById(R.id.list_item_card_title);
                    description = itemView.findViewById(R.id.list_item_card_description);
                    break;
            }
        }
    }
}

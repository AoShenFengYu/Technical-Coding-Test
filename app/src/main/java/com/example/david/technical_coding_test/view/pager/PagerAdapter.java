package com.example.david.technical_coding_test.view.pager;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.david.technical_coding_test.IPresenter;
import com.example.david.technical_coding_test.R;
import com.example.david.technical_coding_test.model.data_model.pager.PagerItem;
import com.example.david.technical_coding_test.view.list.IListAdapter;
import com.example.david.technical_coding_test.view.list.ListAdapter;

import java.util.ArrayList;

/**
 * Created by David on 2018/1/2.
 */

public class PagerAdapter extends BaseViewPagerAdapter<PagerAdapter.ItemViewHolder> implements IPagerAdapter {

    private IPresenter mPresenter;

    /**
     * Data
     **/
    private ArrayList<PagerItem> mPagerItems;

    public PagerAdapter(Context context, IPresenter presenter, ViewPager viewPager) {
        super(context, viewPager);
        this.mPresenter = presenter;

        mPagerItems = new ArrayList<>();
    }

    @Override
    protected void onPageSelected(int position) {

    }

    @Override
    protected void onBindViewHolder(ItemViewHolder holder, int page) {
        PagerItem pagerItem = mPagerItems.get(page);

        holder.adapter.update(pagerItem);
    }

    @Override
    protected int getItemCount() {
        if (mPagerItems == null) {
            return 0;

        } else {
            return mPagerItems.size();
        }
    }

    @Override
    protected ItemViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = mInflater.inflate(R.layout.view_pager_item, parent, false);

        return new ItemViewHolder(itemView);
    }

    /**
     * IPagerAdapter
     **/
    @Override
    public void update() {
        mPagerItems = mPresenter.getPageItems();
        notifyDataSetChanged();
    }

    class ItemViewHolder extends BaseViewPagerAdapter.ViewHolder {
        RecyclerView list;
        IListAdapter adapter;

        public ItemViewHolder(View itemView) {
            super(itemView);

            initLayout();
        }

        private void initLayout() {
            list = itemView.findViewById(R.id.view_pager_item_list);

            initList();
        }

        private void initList() {
            adapter = new ListAdapter(mContext, mPresenter, list);

            list.setItemAnimator(null);
            
            list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

            list.setAdapter((ListAdapter) adapter);
        }
    }

}

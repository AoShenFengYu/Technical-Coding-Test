package com.example.david.technical_coding_test.view;


import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by David on 2018/1/2.
 */

public class PagerAdapter extends BaseViewPagerAdapter {

    public PagerAdapter(Context context, ViewPager viewPager) {
        super(context, viewPager);
    }

    @Override
    protected void onPageSelected(int position) {

    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, int page) {

    }

    @Override
    protected int getItemCount() {
        return 0;
    }

    @Override
    protected ViewHolder onCreateViewHolder(ViewGroup parent) {
        return null;
    }

    class ItemViewHolder extends BaseViewPagerAdapter.ViewHolder {


        public ItemViewHolder(View itemView) {
            super(itemView);

            initLayout();
        }

        private void initLayout() {
             = itemView.findViewById(R.id.symbol_view_pager_item_recycler_view);
//            fastScroller = itemView.findViewById(R.id.symbol_view_pager_item_recycler_view_fast_scroll_bar);

            initList();
            initFastScroller();
        }

        private void initFastScroller() {
//            fastScroller.attachRecyclerView(list);
        }

        private void initList() {
            int itemSize = (int) (Tools.getRollerListHeight(mContext) * SymbolView.SYMBOL_VIEW_ITEM_SIZE);
            int[] screenSize = Tools.getScreenSize(mContext);
            int columnCount = screenSize[0] / itemSize;
            SymbolSize symbolSize = new SymbolSize(columnCount, itemSize);
            GridLayoutManager manager = new GridLayoutManager(mContext, columnCount, RecyclerView.VERTICAL, false);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (Tools.isSymbolTableRTLSupported(mContext)) {
                    list.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

                } else {
                    list.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                }
            }

            adapter = new SymbolViewListAdapter(mContext, mSymbolView, list, symbolSize, mSymbolSelectedListener);

            list.setLayoutManager(manager);
            list.setItemAnimator(null);
            list.setAdapter(adapter);
        }

        public void updateItemListAdapter(int position, int action, boolean canPage, boolean updateAll) {
            SymbolTable symbolTable = mSymbolTables.get(position);

            adapter.update(symbolTable, action, canPage, updateAll);
        }
    }

}

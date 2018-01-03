package com.example.david.technical_coding_test.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by kika-david on 2017/8/30.
 */

public abstract class BaseViewPagerAdapter<VH extends BaseViewPagerAdapter.ViewHolder> extends PagerAdapter {

    protected Context mContext;
    protected ViewPager mViewPager;

    private Stack<View> mItemViews;
    protected LayoutInflater mInflater;
    private HashMap<Integer, VH> mCurrentUsedItemViews;

    public BaseViewPagerAdapter(Context context, final ViewPager viewPager) {
        this.mContext = context;
        this.mViewPager = viewPager;

        initValue();
    }

    private void initValue() {
        mInflater = LayoutInflater.from(mContext);
        mItemViews = new Stack<>();
        mCurrentUsedItemViews = new HashMap<>();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                VH holder = getViewHolder(position);

                if (holder != null) {
                    onBindViewHolder(holder, position);
                }

                BaseViewPagerAdapter.this.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;

        mCurrentUsedItemViews.remove(position);

        viewPager.removeView(view);
        mItemViews.push(view);
//        Log.e(TAG, "放回view, "+mItemViews.size());
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = inflateItemView(container);
        VH holder = (VH) itemView.getTag();
        ViewPager viewPager = (ViewPager) container;

        viewPager.addView(itemView);

        // set Data
        mCurrentUsedItemViews.put(position, holder);
        onBindViewHolder(holder, position);
        return holder.itemView;
    }

    protected abstract void onPageSelected(int position);

    protected abstract void onBindViewHolder(VH holder, int page);

    protected abstract int getItemCount();

    protected abstract VH onCreateViewHolder(ViewGroup parent);

    @Override
    public int getCount() {
        return getItemCount();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // apply from:http://stackoverflow.com/questions/19020114/how-to-implement-view-recycling-mechanism-for-pager-adapter
    private View inflateItemView(ViewGroup container) {
        View itemView;

        if (mItemViews.isEmpty()) {
//            Log.e(TAG, "新增view");
            VH holder = onCreateViewHolder(container);

            itemView = holder.itemView;
            itemView.setTag(holder);
        } else {
            itemView = mItemViews.pop();
//            Log.e(TAG, "拿出view, "+mItemViews.size());
        }

        return itemView;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    protected VH getCurrentViewHolder() {
        return mCurrentUsedItemViews.get(mViewPager.getCurrentItem());
    }


    protected VH getViewHolder(int position) {
        return mCurrentUsedItemViews.get(position);
    }

    public class ViewHolder {

        protected View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
        }
    }
}

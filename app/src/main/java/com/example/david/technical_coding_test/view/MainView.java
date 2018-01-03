package com.example.david.technical_coding_test.view;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.david.technical_coding_test.IPresenter;
import com.example.david.technical_coding_test.Presenter;
import com.example.david.technical_coding_test.R;
import com.example.david.technical_coding_test.view.pager.IPagerAdapter;
import com.example.david.technical_coding_test.view.pager.PagerAdapter;

/**
 * Created by David on 2018/1/1.
 */

public class MainView implements IMainView {

    public final static int PAGE_COUNT = 3;

    private final Activity mActivity;
    private final IPresenter mPresenter;

    private TabLayout mTabLayout;

    private ViewPager mViewPager;
    private IPagerAdapter mViewPagerAdapter;

    public MainView(Activity activity, Presenter presenter) {
        this.mActivity = activity;
        this.mPresenter = presenter;

        initView();
    }

    private void initView() {
        initViewPager();

        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout = mActivity.findViewById(R.id.activity_main_tab_layout);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initViewPager() {
        mViewPager = mActivity.findViewById(R.id.activity_main_pager);

        mViewPagerAdapter = new PagerAdapter(mActivity, mPresenter, mViewPager);

        mViewPager.setAdapter((PagerAdapter) mViewPagerAdapter);
    }


    @Override
    public void updatePager() {
        mViewPagerAdapter.update();
    }
}

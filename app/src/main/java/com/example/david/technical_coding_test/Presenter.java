package com.example.david.technical_coding_test;

import android.app.Activity;

import com.example.david.technical_coding_test.model.IModel;
import com.example.david.technical_coding_test.model.Model;
import com.example.david.technical_coding_test.model.data_model.list.ListDataBundle;
import com.example.david.technical_coding_test.model.data_model.pager.PagerItem;
import com.example.david.technical_coding_test.view.IMainView;
import com.example.david.technical_coding_test.view.MainView;

import java.util.ArrayList;

/**
 * Created by David on 2018/1/1.
 */

public class Presenter implements IPresenter {

    private IMainView mMainView;
    private IModel mModel;

    public Presenter(Activity activity) {
        mModel = new Model();
        mMainView = new MainView(activity, this);

        start();
    }

    private void start() {
        mMainView.updatePager();
    }

    @Override
    public void setView(IMainView mainView) {
        this.mMainView = mainView;
    }

    @Override
    public int getPageCount() {
        return mModel.getPageCount();
    }

    @Override
    public int getListType(int page) {
        return mModel.getListType(page);
    }

    @Override
    public ArrayList<PagerItem> getPageItems() {
        return mModel.getPageItems();
    }

    @Override
    public void getListData(PagerItem pagerItem, int downloadMode, IModel.Callback<ListDataBundle> callback) {
        mModel.getListItems(pagerItem, downloadMode, callback);
    }


}

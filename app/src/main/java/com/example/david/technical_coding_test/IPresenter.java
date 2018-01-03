package com.example.david.technical_coding_test;

import com.example.david.technical_coding_test.model.IModel;
import com.example.david.technical_coding_test.model.data_model.list.ListDataBundle;
import com.example.david.technical_coding_test.model.data_model.pager.PagerItem;
import com.example.david.technical_coding_test.view.IMainView;

import java.util.ArrayList;

/**
 * Created by David on 2018/1/1.
 */

public interface IPresenter {

    void setView(IMainView mainView);

    /**
     * for pager
     **/
    int getPageCount();

    int getListType(int page);

    ArrayList<PagerItem> getPageItems();

    /**
     * for List
     **/
    void getListData(PagerItem pagerItem, int downloadMode, IModel.Callback<ListDataBundle> callback);
}

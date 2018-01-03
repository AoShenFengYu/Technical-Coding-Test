package com.example.david.technical_coding_test.model;

import com.example.david.technical_coding_test.model.data_model.list.ListDataBundle;
import com.example.david.technical_coding_test.model.data_model.pager.PagerItem;
import com.example.david.technical_coding_test.model.data_model.base.BaseData;

import java.util.ArrayList;

/**
 * Created by David on 2018/1/1.
 */

public interface IModel {
    interface Callback<T> {
        void onResult(boolean isSuccessful, ListDataBundle listDataBundle);
    }

    /**
     * for pager
     **/
    int getPageCount();

    int getListType(int page);

    ArrayList<PagerItem> getPageItems();

    class ListType {
        public final static int DOG = 0;
        public final static int CAT = 1;
        public final static int DUCK = 2;
    }

    class ListTypePosition {
        public final static int DOG = 0;
        public final static int CAT = 1;
        public final static int DUCK = 2;
    }

    int PAGE_COUNT = 3;

    /**
     * for list
     **/

    class DownloadMode {
        public final static int INIT = 2;
        public final static int NEXT_PAGE = 0;
        public final static int PRE_PAGE = 1;
    }

    void getListItems(PagerItem pagerItem, int downloadMode, Callback<ListDataBundle> callback);

}

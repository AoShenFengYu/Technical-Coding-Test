package com.example.david.technical_coding_test.model.data_model.pager;

/**
 * Created by David on 2018/1/3.
 */

public class PagerItem {
    private int listType;
    private int position;

    public PagerItem(int listType, int position) {
        this.listType = listType;
        this.position = position;
    }

    public int getListType() {
        return listType;
    }

    public int getPosition() {
        return position;
    }
}

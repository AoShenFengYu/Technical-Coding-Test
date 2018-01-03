package com.example.david.technical_coding_test.model.data_model.list;

import com.example.david.technical_coding_test.model.IModel;
import com.example.david.technical_coding_test.model.data_model.base.BaseData;

import java.util.ArrayList;

/**
 * Created by David on 2018/1/3.
 */

public class ListDataBundle {

    private ArrayList<BaseData> items;
    private int firstPage;
    private int lastPage;

    private int downloadMode;
    private int newItemPosition;

    public ListDataBundle(ArrayList<BaseData> items, int firstPage, int lastPage, int downloadMode, int newItemPosition) {
        this.items = items;
        this.firstPage = firstPage;
        this.lastPage = lastPage;
        this.downloadMode = downloadMode;
        this.newItemPosition = newItemPosition;
    }

    public ArrayList<BaseData> getItems() {
        return items;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public int getDownloadMode() {
        return downloadMode;
    }

    public int getNewItemPosition() {
        return newItemPosition;
    }

    public static ListDataBundle create() {
        return new ListDataBundle(new ArrayList<BaseData>(), 0, 0, IModel.DownloadMode.NEXT_PAGE, 0);
    }
}


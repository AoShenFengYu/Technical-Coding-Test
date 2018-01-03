package com.example.david.technical_coding_test.view.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.technical_coding_test.R;
import com.example.david.technical_coding_test.model.data_model.PageItem;

import static com.example.david.technical_coding_test.view.list.ListAdapter.ViewType.CARD;
import static com.example.david.technical_coding_test.view.list.ListAdapter.ViewType.PICTURE;

/**
 * Created by David on 2018/1/2.
 */

public interface IListAdapter {
    void updateParentPage(PageItem pageItem);
}
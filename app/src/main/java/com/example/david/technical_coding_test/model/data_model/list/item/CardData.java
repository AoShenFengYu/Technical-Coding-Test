package com.example.david.technical_coding_test.model.data_model.list.item;

import com.example.david.technical_coding_test.model.data_model.base.BaseData;

/**
 * Created by David on 2018/1/1.
 */

public class CardData extends BaseData {

    private final String title;
    private final String description;

    public CardData(String title, String description, String picUrl) {
        super(picUrl);
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

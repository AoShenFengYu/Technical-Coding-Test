package com.example.david.technical_coding_test.model.data_model.base;

/**
 * Created by David on 2018/1/1.
 */

public abstract class BaseData {

    private final String picUrl;

    public BaseData(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }
}

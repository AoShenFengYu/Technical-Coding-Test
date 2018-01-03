package com.example.david.technical_coding_test.tools;

import com.example.david.technical_coding_test.model.data_model.list.item.CardData;
import com.example.david.technical_coding_test.model.data_model.list.item.PictureData;
import com.example.david.technical_coding_test.model.data_model.base.BaseData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by David on 2018/1/1.
 */

public class DataParser {

    private final static String KEY_ITEMS = "items";
    private final static String KEY_TITLE = "title";
    private final static String KEY_DESCRIPTION = "description";
    private final static String KEY_PIC_URL = "picUrl";

    private final static String DEFAULT_TITLE = "Title";
    private final static String DEFAULT_DESCRIPTION = "Description";
    private final static String DEFAULT_PIC_URL = "https://image.damanwoo.com/files/styles/rs-big/public/flickr/4/3151/5820170825_59418deec8_o.jpg";

    public static ArrayList<BaseData> parseListData(String data) {
        ArrayList<BaseData> listData = new ArrayList<>();

        try {
            JSONObject dataJsonObject = new JSONObject(data);
            JSONArray itemsJsonArray = dataJsonObject.optJSONArray(KEY_ITEMS);

            for (int i = 0; i < itemsJsonArray.length(); i++) {
                JSONObject itemJSONObject = itemsJsonArray.getJSONObject(i);
                BaseData baseData = parseListItemData(itemJSONObject);

                listData.add(baseData);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listData;
    }

    private static BaseData parseListItemData(JSONObject itemJSONObject) {
        if (itemJSONObject.length() == 3) {
            return parseCardData(itemJSONObject);

        } else {
            return parsePictureData(itemJSONObject);
        }
    }

    private static CardData parseCardData(JSONObject itemJSONObject) {
        String title = itemJSONObject.optString(KEY_TITLE, DEFAULT_TITLE);
        String description = itemJSONObject.optString(KEY_DESCRIPTION, DEFAULT_DESCRIPTION);
        String picUrl = itemJSONObject.optString(KEY_PIC_URL, DEFAULT_PIC_URL);

        return new CardData(title, description, picUrl);
    }

    private static PictureData parsePictureData(JSONObject itemJSONObject) {
        String picUrl = itemJSONObject.optString(KEY_PIC_URL, DEFAULT_PIC_URL);

        return new PictureData(picUrl);
    }
}

package com.example.david.technical_coding_test.model;

import com.example.david.technical_coding_test.IPresenter;
import com.example.david.technical_coding_test.model.data_model.list.ListDataBundle;
import com.example.david.technical_coding_test.model.data_model.pager.PagerItem;
import com.example.david.technical_coding_test.model.data_model.base.BaseData;
import com.example.david.technical_coding_test.tools.DataParser;
import com.example.david.technical_coding_test.tools.Tools;

import java.util.ArrayList;

/**
 * Created by David on 2018/1/1.
 */

public class Model implements IModel {

    private IPresenter mPresenter;

    /**
     * Data
     **/
    private ListDataBundle mListDataBundle;

    public Model() {
        mListDataBundle = ListDataBundle.create();
    }

    @Override
    public int getPageCount() {
        return IModel.PAGE_COUNT;
    }

    @Override
    public int getListType(int page) {
        switch (page) {
            case ListTypePosition.DOG:
                return ListType.DOG;

            case ListTypePosition.CAT:
                return ListType.CAT;

            case ListTypePosition.DUCK:
                return ListType.DUCK;
        }

        return ListType.DOG;
    }

    @Override
    public ArrayList<PagerItem> getPageItems() {
        ArrayList<PagerItem> pagerItems = new ArrayList<>();

        pagerItems.add(new PagerItem(ListType.DOG, ListTypePosition.DOG));
        pagerItems.add(new PagerItem(ListType.CAT, ListTypePosition.CAT));
        pagerItems.add(new PagerItem(ListType.DUCK, ListTypePosition.DUCK));

        return pagerItems;
    }

    @Override
    public void getListItems(PagerItem pagerItem, int downloadMode, final Callback<ListDataBundle> callback) {
        ArrayList<BaseData> newListItems;
        boolean isSuccessful = true;

        switch (pagerItem.getListType()) {
            case ListType.DOG:
                newListItems = DataParser.parseListData(getDogData());
                break;

            case ListType.CAT:
                newListItems = DataParser.parseListData(getCatData());
                break;

            case ListType.DUCK:
                newListItems = DataParser.parseListData(getDuckData());
                break;

            default:
                isSuccessful = false;
                newListItems = new ArrayList<>();
        }

        mListDataBundle = appendListItems(downloadMode, newListItems);

        // 模擬三秒後取得資料
        final boolean finalIsSuccessful = isSuccessful;

        Tools.delayToDo(new Runnable() {
            @Override
            public void run() {
                callback.onResult(finalIsSuccessful, mListDataBundle);
            }
        }, 3000);
    }

    private ListDataBundle appendListItems(int downloadMode, ArrayList<BaseData> newListItems) {
        ArrayList<BaseData> listItems = mListDataBundle.getItems();
        int newItemPosition = listItems.size();
        int firstPage = mListDataBundle.getFirstPage();
        int lastPage = mListDataBundle.getLastPage();

        switch (downloadMode) {
            case DownloadMode.NEXT_PAGE:
                listItems.addAll(newListItems);
                lastPage++;
                break;

            case DownloadMode.PRE_PAGE:
                newItemPosition = newListItems.size() - 1;
                newListItems.addAll(listItems);
                firstPage--;
                break;
        }

        return new ListDataBundle(listItems, firstPage, lastPage, downloadMode, newItemPosition);
    }

    private String getDogData() {
        String data = "{\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"title\": \"狗狗睡眠習慣與姿勢解析\",\n" +
                "      \"description\": \"當家中有養狗狗時，想必每次主人們看到狗狗超可愛的睡姿心都會被俘虜吧！閉著眼彷彿熟睡，但靈敏的牠下一秒可能就會因為發現什麼而驚醒，狗狗在睡眠時也是有些習慣你不可不知道的，請看以下的整理吧！\",\n" +
                "      \"picUrl\": \"https://petbird.tw/wp-content/uploads/2014/03/P28.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"picUrl\": \"https://simg314.magcasa.com/content_images/2016/02/08/235551/1454870738_7182.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"世界上最長壽的狗狗在相當人類203歲\",\n" +
                "      \"description\": \"每天帶狗狗到戶外散步，呼吸新鮮空氣。如果狗狗年齡較大，還是不要強迫牠運動，日常的散步就可以滿足。\",\n" +
                "      \"picUrl\": \"https://www.bomb01.com/upload/news/original/4d0defced3af488155d27b70b921bc24.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"picUrl\": \"https://petbird.tw/wp-content/uploads/2014/02/SF289.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"春季來臨小狗狗發情期應該怎麼辦?\",\n" +
                "      \"description\": \"若此時發情，多會持續一到兩周，專情的小狗狗甚至會堅持到三周。雖然狗狗是多情的，但一般是沒有鍾情到很久的小母狗。\",\n" +
                "      \"picUrl\": \"https://www.bomb01.com/upload/news/original/4d0defced3af488155d27b70b921bc24.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"狗狗秋季養護（圖文）\",\n" +
                "      \"description\": \"秋天不管對人還是對狗狗而言,都是一個美好的季節,煩人的蚊蟲少了,溫度下降了,空氣的濕度相對低,這對厭惡熱天的狗狗們來說將是擁有活力的開始，因此狗狗家長們在此時一定要做好保健工作來迎接冬天的到來。\",\n" +
                "      \"picUrl\": \"http://i1.sinaimg.cn/bj/p/2008-05-06/U2062P52T4D165799F139DT20080506171252.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"笑到噴淚的「狗界變裝Top 15 」！\",\n" +
                "      \"description\": \"對於愛狗人士來說，狗的存在根本就如同天使下凡般，治癒著我們，但只是養牠還不夠，許多人還會將他們的狗寶貝打扮成各種搞笑、新奇的角色，就算只是躺著不動，也能製造 ...\",\n" +
                "      \"picUrl\": \"https://static.juksy.com/files/articles/61581/5877016ee9d24.jpg\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        return data;
    }

    private String getCatData() {
        String data = "{\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"title\": \"貓奴在什麼情況下最有魅力?居然讓喵喵難以抗拒!!\",\n" +
                "      \"description\": \"不曉得貓奴是否注意過：當貓奴洗好澡走出浴室後，就會看見喵喵水汪汪的大眼望著你，接著走到你的身旁開始愛的磨蹭?彷彿喵喵特別喜愛在貓奴洗好澡的時候過來撒嬌，是因為喜歡這款沐浴乳的香味嗎?還是因為剛洗好澡的貓奴特別有魅力呢?\",\n" +
                "      \"picUrl\": \"http://www.twgreatdaily.com/imgs/image/42/4202536.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"picUrl\": \"https://attach.setn.com/newsimages/2014/02/22/44672-XXL.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"picUrl\": \"http://s6.gigacircle.com/media/s6_5617303c7fffa.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"picUrl\": \"https://images.gamme.com.tw/news2/2017/05/45/paCRnZ_WlKWYr6c.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"picUrl\": \"https://petbird.tw/wp-content/uploads/2014/03/hh026.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"千萬不要這樣做，你正在損害貓咪的視力！\",\n" +
                "      \"description\": \"大家應該都知道貓咪本身非常容易受到驚嚇，不管是不存在的東西突然蹦出來（ 前陣子很多人用小黃瓜嚇貓）、或是你突然衝向他，都會讓牠嚇的跳起來或在家橫衝直撞，所以最好還是別這樣對可愛的小貓們，好好溫柔地對待牠們，就算要拍攝牠們，也盡量輕輕的、慢慢的靠近他們，才不會讓牠們小小心靈出現陰影啊！\",\n" +
                "      \"picUrl\": \"http://www.gooden.link/image/Document/201607221732385362.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"貓奴們，請注意 - 貓咪可能帶給你的皮膚疾病\",\n" +
                "      \"description\": \"毛小孩、喵星人，真的太可愛了。總會讓人忍不住想去逗弄他們。但，有時候不得不說，有時候貓奴真的也必須受點委屈，對於這些可愛的小惡魔們。以下就是介紹一些常見跟貓咪有關的皮膚疾病。這是飼養貓咪有時候必須要付出的甜蜜的代價呀。\",\n" +
                "      \"picUrl\": \"https://lh5.googleusercontent.com/4HxYxO_kYq_RGYV3gSY8NZcEOYnjk5c9YTNUkiA15WuJE4sQn5OEHarFy_3u0PWy0Ml0WVL2kXV2kmM4A_PKSEAR5FklE9zZeSVYK4OPJjSo0ylSVY0GXlRh-ON56hh-M4cJeUmq\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        return data;
    }

    private String getDuckData() {
        String data = "{\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"title\": \"鸭子 \",\n" +
                "      \"description\": \"鸭子 英文名称：Duck。脊索动物门，脊椎动物亚门，鸟纲雁形目，鸭科鸭属动物，是由野生绿头鸭和斑嘴鸭驯化而来。是一种常见家禽。鸭是雁形目鸭科鸭亚科水禽的统称。是一种水、陆两栖动物。但不能在水中待太久，是卵生动物。\",\n" +
                "      \"picUrl\": \"https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=b8da5acd65380cd7e21ea5ef9145ad14/f11f3a292df5e0fe91812c7c5c6034a85edf7235.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"picUrl\": \"https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=6d2155d9b051f819f5250448eab54a76/42166d224f4a20a41210ef3590529822720ed032.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"picUrl\": \"http://b.blog.xuite.net/b/2/d/2/242887276/blog_4290199/txt/263825317/1.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"鴨子也會發酒瘋！？英國這隻鴨星人因為喝太多酒，居然跑去揍汪星人，結果當然是...\",\n" +
                "      \"description\": \"英國德文郡有隻超火紅的鴨子，因為牠不僅是許多酒吧的忠實老客戶，還曾經因為喝醉發酒瘋，跑去跟隻狗狗打架，讓牠一戰成名。\",\n" +
                "      \"picUrl\": \"http://tw.ptt01.cc/zhtw/c021/Image/20151022/6358107793223965265710496.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"picUrl\": \"https://i2.kknews.cc/SIG=5ncvur/7n60009o297s950n529.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"picUrl\": \"http://i.zgjm.org/uploads/allimg/160607/1_160607124058_1.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"19張完勝你手機裡所有「鴨子臉」\",\n" +
                "      \"description\": \"一點都不性感好嗎！ ▽你是不是希望你拍照時的臉看起來跟我一樣呀！\",\n" +
                "      \"picUrl\": \"http://www.bombdaily.com/upload/news/original/1dd6e55d0e8c9d2ac33c565b6ede0e7c.jpg\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        return data;
    }
}

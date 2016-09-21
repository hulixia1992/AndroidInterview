package com.hulixia1992.interview.androidinterview.data;

import java.util.List;

/**
 * Created by hulixia on 2016/9/21.
 */
public class ItemData {
    public List<String> itemTitles;
    public List<ItemSmallData> mSmallDatas;

    static class  ItemSmallData {
        public String itemTitle;
        public String itemUrl;
    }
}

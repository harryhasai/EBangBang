package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/11/24.
 */
public class TopCategoryEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"id":7,"commodityName":"快餐小吃","rank":2,"parentId":1,"isDelete":0,"createUser":1,"updateTime":1536802882000},{"id":8,"commodityName":"地方菜","rank":2,"parentId":1,"isDelete":0,"createUser":1,"updateTime":1536802905000},{"id":9,"commodityName":"烧烤海鲜","rank":2,"parentId":1,"isDelete":0,"createUser":1,"updateTime":1536802926000}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 7
         * commodityName : 快餐小吃
         * rank : 2
         * parentId : 1
         * isDelete : 0
         * createUser : 1
         * updateTime : 1536802882000
         */

        public int id;
        public String commodityName;
        public int rank;
        public int parentId;
        public int isDelete;
        public int createUser;
        public long updateTime;
    }
}

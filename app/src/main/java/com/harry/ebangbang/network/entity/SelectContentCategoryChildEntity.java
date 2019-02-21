package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/12/25.
 */
public class SelectContentCategoryChildEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"id":7,"commodityName":"快餐小吃","rank":2,"parentId":1,"isDelete":null,"createUser":null,"updateTime":null},{"id":8,"commodityName":"地方菜","rank":2,"parentId":1,"isDelete":null,"createUser":null,"updateTime":null},{"id":9,"commodityName":"烧烤海鲜","rank":2,"parentId":1,"isDelete":null,"createUser":null,"updateTime":null}]
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
         * isDelete : null
         * createUser : null
         * updateTime : null
         */

        public int id;
        public String commodityName;
        public int rank;
        public int parentId;
        public Object isDelete;
        public Object createUser;
        public Object updateTime;
    }
}

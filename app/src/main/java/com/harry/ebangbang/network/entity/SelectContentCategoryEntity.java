package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/12/25.
 */
public class SelectContentCategoryEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"id":1,"commodityName":"美食","rank":1,"parentId":0,"isDelete":0,"createUser":1,"updateTime":1536802713000},{"id":2,"commodityName":"甜点饮品","rank":1,"parentId":0,"isDelete":0,"createUser":1,"updateTime":1536802743000},{"id":3,"commodityName":"生活超市","rank":1,"parentId":0,"isDelete":0,"createUser":1,"updateTime":1536802762000},{"id":4,"commodityName":"生鲜果蔬","rank":1,"parentId":0,"isDelete":0,"createUser":1,"updateTime":1536802785000},{"id":5,"commodityName":"鲜花绿植","rank":1,"parentId":0,"isDelete":0,"createUser":1,"updateTime":1536802809000},{"id":6,"commodityName":"医药健康","rank":1,"parentId":0,"isDelete":0,"createUser":1,"updateTime":1536802826000}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1
         * commodityName : 美食
         * rank : 1
         * parentId : 0
         * isDelete : 0
         * createUser : 1
         * updateTime : 1536802713000
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

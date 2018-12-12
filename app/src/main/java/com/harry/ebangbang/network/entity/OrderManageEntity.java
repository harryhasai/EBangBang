package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/12/11.
 */
public class OrderManageEntity {

    /**
     * msg : 查询成功
     * CODE : 1
     * data : [{"orderFormStatus":-1,"actualMoney":0.01,"shopName":"王牌小龙虾1","goods":[{"imgLink":"20180926160337796_238.jpg","goodsId":5,"price":0.01,"num":1,"name":"牛火腿"}],"id":14,"stateOfPayment":0,"shopId":8},{"orderFormStatus":4,"actualMoney":0.01,"shopName":"刘海鸥DE店","goods":[{"imgLink":"20181108095947758_327.jpg","goodsId":3,"price":90,"num":1,"name":"火腿1"},{"imgLink":"20181108095927871_558.jpg","goodsId":4,"price":100,"num":1,"name":"牛肉"}],"id":13,"stateOfPayment":0,"shopId":10},{"orderFormStatus":3,"actualMoney":0.01,"shopName":"刘海鸥DE店","goods":[{"imgLink":"20181108095927871_558.jpg","goodsId":4,"price":100,"num":3,"name":"牛肉"}],"id":12,"stateOfPayment":0,"shopId":10},{"orderFormStatus":2,"actualMoney":0.01,"shopName":"刘海鸥DE店","goods":[{"imgLink":"20181108095947758_327.jpg","goodsId":3,"price":90,"num":3,"name":"火腿1"},{"imgLink":"20181108095927871_558.jpg","goodsId":4,"price":100,"num":3,"name":"牛肉"}],"id":11,"stateOfPayment":0,"shopId":10},{"orderFormStatus":1,"actualMoney":0.01,"shopName":"刘海鸥DE店","goods":[{"imgLink":"20181108095947758_327.jpg","goodsId":3,"price":90,"num":3,"name":"火腿1"},{"imgLink":"20181108095927871_558.jpg","goodsId":4,"price":100,"num":3,"name":"牛肉"}],"id":10,"stateOfPayment":0,"shopId":10},{"orderFormStatus":0,"actualMoney":0.01,"shopName":"刘海鸥DE店","goods":[{"imgLink":"20181108095947758_327.jpg","goodsId":3,"price":90,"num":3,"name":"火腿1"},{"imgLink":"20181108095927871_558.jpg","goodsId":4,"price":100,"num":3,"name":"牛肉"}],"id":9,"stateOfPayment":0,"shopId":10},{"orderFormStatus":-1,"actualMoney":0.01,"shopName":"刘海鸥DE店","goods":[{"imgLink":"20181108095927871_558.jpg","goodsId":4,"price":100,"num":3,"name":"牛肉"}],"id":8,"stateOfPayment":0,"shopId":10}]
     * page : {"currentPage":1,"pageSize":10,"searchMap":{},"recordCount":7,"pageCount":1,"beginPageIndex":1,"endPageIndex":1,"next":1,"prev":1}
     */

    public String msg;
    public int code;
    public PageBean page;
    public List<DataBean> data;

    public static class PageBean {
        /**
         * currentPage : 1
         * pageSize : 10
         * searchMap : {}
         * recordCount : 7
         * pageCount : 1
         * beginPageIndex : 1
         * endPageIndex : 1
         * next : 1
         * prev : 1
         */

        public int currentPage;
        public int pageSize;
        public SearchMapBean searchMap;
        public int recordCount;
        public int pageCount;
        public int beginPageIndex;
        public int endPageIndex;
        public int next;
        public int prev;

        public static class SearchMapBean {
        }
    }

    public static class DataBean {
        /**
         * orderFormStatus : -1
         * actualMoney : 0.01
         * shopName : 王牌小龙虾1
         * goods : [{"imgLink":"20180926160337796_238.jpg","goodsId":5,"price":0.01,"num":1,"name":"牛火腿"}]
         * id : 14
         * stateOfPayment : 0
         * shopId : 8
         */

        public int orderFormStatus;
        public double actualMoney;
        public String shopName;
        public int id;
        public int stateOfPayment;
        public int shopId;
        public List<GoodsBean> goods;

        public static class GoodsBean {
            /**
             * imgLink : 20180926160337796_238.jpg
             * goodsId : 5
             * price : 0.01
             * num : 1
             * name : 牛火腿
             */

            public String imgLink;
            public int goodsId;
            public double price;
            public int num;
            public String name;
        }
    }
}

package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/12/11.
 */
public class OrderDetailEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : {"orderFormStatus":1,"actualMoney":0.01,"accomplishTime":1544506882000,"orderNumber":"1540533497601598114","ridePhone":"15036441575","distance":0,"userLongitude":"112.482834","userphone":"15020001111","orderFormtime":1540533497000,"shopLatitude":"34.674628","shopName":"刘海鸥DE店","goods":[{"goodsId":3,"num":3,"goodsPrice":90,"goodsName":"火腿1","goodsLogo":"20181108095947758_327.jpg"},{"goodsId":4,"num":3,"goodsPrice":100,"goodsName":"牛肉","goodsLogo":"20181108095927871_558.jpg"}],"remark":"多放辣","deliveryManId":6,"userName":"Kk2","shopLongitude":"112.448501","rideName":"王五","userSite":"18766132222","mealBoxMoney":0.03,"rideLongitude":"116.7654","userLatitude":"112.482834","orderFormId":10,"rideLatitude":"33.96134","meetSubtract":20}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * orderFormStatus : 1
         * actualMoney : 0.01
         * accomplishTime : 1544506882000
         * orderNumber : 1540533497601598114
         * ridePhone : 15036441575
         * distance : 0
         * userLongitude : 112.482834
         * userphone : 15020001111
         * orderFormtime : 1540533497000
         * shopLatitude : 34.674628
         * shopName : 刘海鸥DE店
         * goods : [{"goodsId":3,"num":3,"goodsPrice":90,"goodsName":"火腿1","goodsLogo":"20181108095947758_327.jpg"},{"goodsId":4,"num":3,"goodsPrice":100,"goodsName":"牛肉","goodsLogo":"20181108095927871_558.jpg"}]
         * remark : 多放辣
         * deliveryManId : 6
         * userName : Kk2
         * shopLongitude : 112.448501
         * rideName : 王五
         * userSite : 18766132222
         * mealBoxMoney : 0.03
         * rideLongitude : 116.7654
         * userLatitude : 112.482834
         * orderFormId : 10
         * rideLatitude : 33.96134
         * meetSubtract : 20
         */

        public int orderFormStatus;
        public double actualMoney;
//        public long accomplishTime;
        public String orderNumber;
        public String ridePhone;
        public double distance;
        public String userLongitude;
        public String userphone;
        public long orderFormtime;
        public String shopLatitude;
        public String shopName;
        public String remark;
        public int deliveryManId;
        public String userName;
        public String shopLongitude;
        public String rideName;
        public String userSite;
        public double mealBoxMoney;
        public double dispatchingMoney;
        public String rideLongitude;
        public String userLatitude;
        public int orderFormId;
        public String rideLatitude;
        public double meetSubtract;
        public List<GoodsBean> goods;

        public static class GoodsBean {
            /**
             * goodsId : 3
             * num : 3
             * goodsPrice : 90
             * goodsName : 火腿1
             * goodsLogo : 20181108095947758_327.jpg
             */

            public int goodsId;
            public int num;
            public double goodsPrice;
            public String goodsName;
            public String goodsLogo;
        }
    }
}

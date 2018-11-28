package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/11/26.
 */
public class ShoppingCartEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"totalBox":10,"minus":0,"total":460,"meet":0,"shopName":"刘海鸥DE店","goods":[{"amount":1,"goodsImgLink":"20181108095937722_612.jpg","goodsId":1,"goodsPrice":50,"boxMoney":2,"goodsName":"豆沙包"},{"amount":4,"goodsImgLink":"20181108095927871_558.jpg","goodsId":4,"goodsPrice":100,"boxMoney":2,"goodsName":"牛肉"}],"shopId":10},{"totalBox":2,"minus":0,"amount":1,"total":42,"meet":0,"shopName":"王牌小龙虾1","goods":[{"amount":1,"goodsImgLink":"20180926160337796_238.jpg","goodsId":5,"goodsPrice":40,"boxMoney":2,"goodsName":"牛火腿"}],"shopId":8}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * totalBox : 10
         * minus : 0
         * total : 460
         * meet : 0
         * shopName : 刘海鸥DE店
         * goods : [{"amount":1,"goodsImgLink":"20181108095937722_612.jpg","goodsId":1,"goodsPrice":50,"boxMoney":2,"goodsName":"豆沙包"},{"amount":4,"goodsImgLink":"20181108095927871_558.jpg","goodsId":4,"goodsPrice":100,"boxMoney":2,"goodsName":"牛肉"}]
         * shopId : 10
         * amount : 1
         */

        public int totalBox;
        public int minus;
        public int total;
        public int meet;
        public String shopName;
        public int shopId;
        public int amount;
        public List<GoodsBean> goods;

        public static class GoodsBean {
            /**
             * amount : 1
             * goodsImgLink : 20181108095937722_612.jpg
             * goodsId : 1
             * goodsPrice : 50
             * boxMoney : 2
             * goodsName : 豆沙包
             */

            public int amount;
            public String goodsImgLink;
            public int goodsId;
            public int goodsPrice;
            public int standby1;
            public int boxMoney;
            public String goodsName;
        }
    }
}

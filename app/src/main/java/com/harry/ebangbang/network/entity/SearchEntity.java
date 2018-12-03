package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/12/3.
 */
public class SearchEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"distance":1.0032,"meetMinus":[{"minus":30,"meet":70,"id":1},{"minus":5,"meet":10,"id":2},{"minus":40,"meet":50,"id":3}],"shopName":"王牌小龙虾1","logo":"20181124110601392_843.jpg","goods":[{"imgLink":"20180926160337796_238.jpg","goodsSalesVolume":0,"goodsId":5,"price":0.01,"goodsName":"牛火腿"}],"shopId":8},{"distance":2.6259,"meetMinus":[{"minus":30,"meet":70,"id":1},{"minus":5,"meet":10,"id":2},{"minus":40,"meet":50,"id":3}],"shopName":"刘海鸥DE店","logo":"20181124110601392_843.jpg","goods":[{"imgLink":"20181124180154142_503.jpg","goodsSalesVolume":0,"goodsId":2,"price":80,"goodsName":"牛肉哦"},{"imgLink":"20181108095927871_558.jpg","goodsSalesVolume":0,"goodsId":4,"price":100,"goodsName":"牛肉"}],"shopId":10}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * distance : 1.0032
         * meetMinus : [{"minus":30,"meet":70,"id":1},{"minus":5,"meet":10,"id":2},{"minus":40,"meet":50,"id":3}]
         * shopName : 王牌小龙虾1
         * logo : 20181124110601392_843.jpg
         * goods : [{"imgLink":"20180926160337796_238.jpg","goodsSalesVolume":0,"goodsId":5,"price":0.01,"goodsName":"牛火腿"}]
         * shopId : 8
         */

        public double distance;
        public String shopName;
        public String logo;
        public int shopId;
        public List<MeetMinusBean> meetMinus;
        public List<GoodsBean> goods;

        public static class MeetMinusBean {
            /**
             * minus : 30
             * meet : 70
             * id : 1
             */

            public int minus;
            public int meet;
            public int id;
        }

        public static class GoodsBean {
            /**
             * imgLink : 20180926160337796_238.jpg
             * goodsSalesVolume : 0
             * goodsId : 5
             * price : 0.01
             * goodsName : 牛火腿
             */

            public String imgLink;
            public int goodsSalesVolume;
            public int goodsId;
            public double price;
            public String goodsName;
        }
    }
}

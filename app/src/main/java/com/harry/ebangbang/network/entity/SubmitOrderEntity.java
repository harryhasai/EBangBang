package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/11/28.
 */
public class SubmitOrderEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : {"total":310,"shop":{"shopName":"刘海鸥DE店","shopId":10},"addressee":{"id":4,"phone":"18766111111","userId":20,"name":"1231","site":"北京市东城区霞公府街3号霞公府&  11111","longitude":"112.482834","latitude":"34.653073","isDefault":1,"createTime":1539684732000,"createId":20,"isDelete":0},"deliveryTime":20798,"dispatchingCost":138541.516,"meetMinus":{"minus":50,"meet":60},"goods":[{"goodsimg":"20181108095937722_612.jpg","goodsunit":"元","standby":3,"goodsId":1,"price":50,"boxMoney":2,"goodsspec":"个","goodsName":"豆沙包"},{"goodsimg":"20181108095927871_558.jpg","goodsunit":"斤","standby":2,"goodsId":4,"price":100,"boxMoney":2,"goodsspec":"元","goodsName":"牛肉"}],"countBox":10}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * total : 310
         * shop : {"shopName":"刘海鸥DE店","shopId":10}
         * addressee : {"id":4,"phone":"18766111111","userId":20,"name":"1231","site":"北京市东城区霞公府街3号霞公府&  11111","longitude":"112.482834","latitude":"34.653073","isDefault":1,"createTime":1539684732000,"createId":20,"isDelete":0}
         * deliveryTime : 20798
         * dispatchingCost : 138541.516
         * meetMinus : {"minus":50,"meet":60}
         * goods : [{"goodsimg":"20181108095937722_612.jpg","goodsunit":"元","standby":3,"goodsId":1,"price":50,"boxMoney":2,"goodsspec":"个","goodsName":"豆沙包"},{"goodsimg":"20181108095927871_558.jpg","goodsunit":"斤","standby":2,"goodsId":4,"price":100,"boxMoney":2,"goodsspec":"元","goodsName":"牛肉"}]
         * countBox : 10
         */

        public int total;
        public ShopBean shop;
        public AddresseeBean addressee;
        public int deliveryTime;
        public double dispatchingCost;
        public MeetMinusBean meetMinus;
        public int countBox;
        public List<GoodsBean> goods;

        public static class ShopBean {
            /**
             * shopName : 刘海鸥DE店
             * shopId : 10
             */

            public String shopName;
            public int shopId;
        }

        public static class AddresseeBean {
            /**
             * id : 4
             * phone : 18766111111
             * userId : 20
             * name : 1231
             * site : 北京市东城区霞公府街3号霞公府&  11111
             * longitude : 112.482834
             * latitude : 34.653073
             * isDefault : 1
             * createTime : 1539684732000
             * createId : 20
             * isDelete : 0
             */

            public int id;
            public String phone;
            public int userId;
            public String name;
            public String site;
            public String longitude;
            public String latitude;
            public int isDefault;
            public long createTime;
            public int createId;
            public int isDelete;
        }

        public static class MeetMinusBean {
            /**
             * minus : 50
             * meet : 60
             */

            public int minus;
            public int meet;
        }

        public static class GoodsBean {
            /**
             * goodsimg : 20181108095937722_612.jpg
             * goodsunit : 元
             * standby : 3
             * goodsId : 1
             * price : 50
             * boxMoney : 2
             * goodsspec : 个
             * goodsName : 豆沙包
             */

            public String goodsimg;
            public String goodsunit;
            public int standby;
            public int goodsId;
            public int price;
            public int boxMoney;
            public String goodsspec;
            public String goodsName;
        }
    }
}

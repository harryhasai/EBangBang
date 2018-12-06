package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/12/4.
 */
public class ShopDetailChildEntity {

    /**
     * msg : 查询成功
     * headPortraitLink : http://116.62.218.136:9055/guozongapp/file/
     * code : 1
     * data : {"monthSales":0,"distance":2.6259,"shopLogo":"20181124110601392_843.jpg","shopSite":"大萨达","meetMInus":[{"minus":4000,"meet":5000,"meetMinusId":3},{"minus":1000,"meet":5000,"meetMinusId":7}],"shopName":"刘海鸥DE店","goods":[{"mealBoxMoney":1000,"isPutaway":1,"isRecommend":0,"serialNumber":"1543628264953282029","imgLink":"20181201093744943_879.jpg","salesVolume":0,"price":99999,"name":"满汉全席","goodsUnit":"份","id":10,"inventory":1,"goodsSpec":"10000"},{"mealBoxMoney":33,"isPutaway":1,"isRecommend":0,"serialNumber":"1543629397370194693","imgLink":"20181201095637369_994.jpg","salesVolume":0,"price":33,"name":"空军建军节","goodsUnit":"22","id":11,"inventory":33,"goodsSpec":"33"},{"mealBoxMoney":33,"isPutaway":1,"isRecommend":0,"serialNumber":"1543629801736593051","imgLink":"20181201100321734_595.jpg","salesVolume":0,"price":25,"name":"啊堵死了","goodsUnit":"33","id":12,"inventory":33,"goodsSpec":"33"},{"mealBoxMoney":22,"isPutaway":1,"isRecommend":0,"serialNumber":"1543630314472630065","imgLink":"20181201101154470_410.jpg","salesVolume":0,"price":22,"name":"啦咯啦咯啦咯啦咯","goodsUnit":"22","id":13,"inventory":22,"goodsSpec":"22"},{"mealBoxMoney":10,"isPutaway":1,"isRecommend":0,"serialNumber":"1543630822201863863","imgLink":"20181201102022200_963.jpg","salesVolume":0,"price":50,"name":"casc1","goodsUnit":"a","id":15,"inventory":500,"goodsSpec":"a"},{"mealBoxMoney":10,"isPutaway":1,"isRecommend":0,"serialNumber":"1543630845908693967","imgLink":"20181201102045907_337.jpg","salesVolume":0,"price":50,"name":"casc1","goodsUnit":"a","id":16,"inventory":500,"goodsSpec":"a"},{"mealBoxMoney":22,"isPutaway":1,"isRecommend":0,"serialNumber":"1543630980199867721","imgLink":"20181201102300197_6.jpg","salesVolume":0,"price":33,"name":"KKK几哈","goodsUnit":"33","id":17,"inventory":22,"goodsSpec":"33"},{"mealBoxMoney":10,"isPutaway":1,"isRecommend":0,"serialNumber":"1543631145831416303","imgLink":"20181201102545830_247.jpg","salesVolume":0,"price":50,"name":"casc1","goodsUnit":"a","id":18,"inventory":500,"goodsSpec":"a"},{"mealBoxMoney":10,"isPutaway":1,"isRecommend":0,"serialNumber":"1543631200511186406","imgLink":"20181201102640506_378.jpg","salesVolume":0,"price":50,"name":"casc1","goodsUnit":"a","id":19,"inventory":500,"goodsSpec":"a"},{"mealBoxMoney":10,"isPutaway":1,"isRecommend":0,"serialNumber":"1543631229621317703","imgLink":"20181201102709619_469.jpg","salesVolume":0,"price":50,"name":"casc1","goodsUnit":"a","id":20,"inventory":500,"goodsSpec":"a"},{"mealBoxMoney":10,"isPutaway":1,"isRecommend":0,"serialNumber":"1543830153253558755","imgLink":"20181203174233252_624.jpg","salesVolume":0,"price":99,"name":"千页豆腐","goodsUnit":"份","id":23,"inventory":20,"goodsSpec":"1000"}],"shopId":10}
     */

    public String msg;
    public String headPortraitLink;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * monthSales : 0
         * distance : 2.6259
         * shopLogo : 20181124110601392_843.jpg
         * shopSite : 大萨达
         * meetMInus : [{"minus":4000,"meet":5000,"meetMinusId":3},{"minus":1000,"meet":5000,"meetMinusId":7}]
         * shopName : 刘海鸥DE店
         * goods : [{"mealBoxMoney":1000,"isPutaway":1,"isRecommend":0,"serialNumber":"1543628264953282029","imgLink":"20181201093744943_879.jpg","salesVolume":0,"price":99999,"name":"满汉全席","goodsUnit":"份","id":10,"inventory":1,"goodsSpec":"10000"},{"mealBoxMoney":33,"isPutaway":1,"isRecommend":0,"serialNumber":"1543629397370194693","imgLink":"20181201095637369_994.jpg","salesVolume":0,"price":33,"name":"空军建军节","goodsUnit":"22","id":11,"inventory":33,"goodsSpec":"33"},{"mealBoxMoney":33,"isPutaway":1,"isRecommend":0,"serialNumber":"1543629801736593051","imgLink":"20181201100321734_595.jpg","salesVolume":0,"price":25,"name":"啊堵死了","goodsUnit":"33","id":12,"inventory":33,"goodsSpec":"33"},{"mealBoxMoney":22,"isPutaway":1,"isRecommend":0,"serialNumber":"1543630314472630065","imgLink":"20181201101154470_410.jpg","salesVolume":0,"price":22,"name":"啦咯啦咯啦咯啦咯","goodsUnit":"22","id":13,"inventory":22,"goodsSpec":"22"},{"mealBoxMoney":10,"isPutaway":1,"isRecommend":0,"serialNumber":"1543630822201863863","imgLink":"20181201102022200_963.jpg","salesVolume":0,"price":50,"name":"casc1","goodsUnit":"a","id":15,"inventory":500,"goodsSpec":"a"},{"mealBoxMoney":10,"isPutaway":1,"isRecommend":0,"serialNumber":"1543630845908693967","imgLink":"20181201102045907_337.jpg","salesVolume":0,"price":50,"name":"casc1","goodsUnit":"a","id":16,"inventory":500,"goodsSpec":"a"},{"mealBoxMoney":22,"isPutaway":1,"isRecommend":0,"serialNumber":"1543630980199867721","imgLink":"20181201102300197_6.jpg","salesVolume":0,"price":33,"name":"KKK几哈","goodsUnit":"33","id":17,"inventory":22,"goodsSpec":"33"},{"mealBoxMoney":10,"isPutaway":1,"isRecommend":0,"serialNumber":"1543631145831416303","imgLink":"20181201102545830_247.jpg","salesVolume":0,"price":50,"name":"casc1","goodsUnit":"a","id":18,"inventory":500,"goodsSpec":"a"},{"mealBoxMoney":10,"isPutaway":1,"isRecommend":0,"serialNumber":"1543631200511186406","imgLink":"20181201102640506_378.jpg","salesVolume":0,"price":50,"name":"casc1","goodsUnit":"a","id":19,"inventory":500,"goodsSpec":"a"},{"mealBoxMoney":10,"isPutaway":1,"isRecommend":0,"serialNumber":"1543631229621317703","imgLink":"20181201102709619_469.jpg","salesVolume":0,"price":50,"name":"casc1","goodsUnit":"a","id":20,"inventory":500,"goodsSpec":"a"},{"mealBoxMoney":10,"isPutaway":1,"isRecommend":0,"serialNumber":"1543830153253558755","imgLink":"20181203174233252_624.jpg","salesVolume":0,"price":99,"name":"千页豆腐","goodsUnit":"份","id":23,"inventory":20,"goodsSpec":"1000"}]
         * shopId : 10
         */

        public int monthSales;
        public double distance;
        public String shopLogo;
        public String shopSite;
        public String shopName;
        public int shopId;
        public List<MeetMInusBean> meetMInus;
        public List<GoodsBean> goods;

        public static class MeetMInusBean {
            /**
             * minus : 4000
             * meet : 5000
             * meetMinusId : 3
             */

            public double minus;
            public double meet;
            public int meetMinusId;
        }

        public static class GoodsBean {
            /**
             * mealBoxMoney : 1000
             * isPutaway : 1
             * isRecommend : 0
             * serialNumber : 1543628264953282029
             * imgLink : 20181201093744943_879.jpg
             * salesVolume : 0
             * price : 99999
             * name : 满汉全席
             * goodsUnit : 份
             * id : 10
             * inventory : 1
             * goodsSpec : 10000
             */

            public int mealBoxMoney;
            public int isPutaway;
            public int isRecommend;
            public String serialNumber;
            public String imgLink;
            public int salesVolume;
            public double price;
            public String name;
            public String goodsUnit;
            public int id;
            public int classId;
            public int inventory;
            public String goodsSpec;
            public String className;
            //客户要购买的份数
            public int goodsCount;
        }
    }
}

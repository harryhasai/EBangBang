package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/11/20.
 */
public class HomeEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"category2":8,"siteDistrict":130802,"upToPar":20,"category3":13,"distributionWay":1,"appearanceLink":"20180913160656069_313.png","siteCity":130800,"siteDetail":"大萨达","meetMinus":[{"minus":30,"meet":70,"id":1},{"minus":5,"meet":10,"id":2},{"minus":40,"meet":50,"id":3}],"category1":1,"latitude":"34.667874","contactsPhone":"刘海鸥","siteProvince":130000,"name":"刘海鸥DE店","logo":"20180913153522483_795.png","id":10,"recommendGrade":1,"longitude":"112.4349"}]
     * attachmentPath : http://127.0.0.1:8080/guozongapp/file/
     */

    public String msg;
    public int code;
    public String attachmentPath;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * category2 : 8
         * siteDistrict : 130802
         * upToPar : 20
         * category3 : 13
         * distributionWay : 1
         * appearanceLink : 20180913160656069_313.png
         * siteCity : 130800
         * siteDetail : 大萨达
         * meetMinus : [{"minus":30,"meet":70,"id":1},{"minus":5,"meet":10,"id":2},{"minus":40,"meet":50,"id":3}]
         * category1 : 1
         * latitude : 34.667874
         * contactsPhone : 刘海鸥
         * siteProvince : 130000
         * name : 刘海鸥DE店
         * logo : 20180913153522483_795.png
         * id : 10
         * recommendGrade : 1
         * longitude : 112.4349
         */

        public int category2;
        public int siteDistrict;
        public int upToPar;
        public int category3;
        public int distributionWay;
        public String appearanceLink;
        public int siteCity;
        public String siteDetail;
        public int category1;
        public String latitude;
        public String contactsPhone;
        public int siteProvince;
        public String name;
        public String logo;
        public int id;
        public int recommendGrade;
        public String longitude;
        public List<MeetMinusBean> meetMinus;

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
    }
}

package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/11/24.
 */
public class SecondCategoryEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"siteDetail":"大萨达","distance":4.9999,"meetMinus":[{"minus":3000,"meet":7000},{"minus":500,"meet":1000},{"minus":4000,"meet":5000}],"name":"刘海鸥DE店","className":"美食","id":10},{"siteDetail":"我的家在山西过了河还有200里","distance":2.3153,"meetMinus":[],"name":"刘浩的店","className":"美食","id":11},{"siteDetail":"详细地址","distance":13.0874,"meetMinus":[],"name":"店铺名字","className":"美食","id":12}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * siteDetail : 大萨达
         * distance : 4.9999
         * meetMinus : [{"minus":3000,"meet":7000},{"minus":500,"meet":1000},{"minus":4000,"meet":5000}]
         * name : 刘海鸥DE店
         * className : 美食
         * id : 10
         */

        public String siteDetail;
        public double distance;
        public String name;
        public String className;
        public int id;
        public String Logo;
        public List<MeetMinusBean> meetMinus;

        public static class MeetMinusBean {
            /**
             * minus : 3000
             * meet : 7000
             */

            public int minus;
            public int meet;
        }
    }
}

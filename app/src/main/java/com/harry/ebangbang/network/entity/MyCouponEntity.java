package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/12/15.
 */
public class MyCouponEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"couponRemarks":"爱仕达多","couponName":"哈哈","couponMoney":1,"couponEndPeriod":1547803040000,"couponMeetMoney":3,"couponStatus":0,"id":4,"couponStartPeriod":1538755200000}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * couponRemarks : 爱仕达多
         * couponName : 哈哈
         * couponMoney : 1
         * couponEndPeriod : 1547803040000
         * couponMeetMoney : 3
         * couponStatus : 0
         * id : 4
         * couponStartPeriod : 1538755200000
         */

        public String couponRemarks;
        public String couponName;
        public double couponMoney;
        public long couponEndPeriod;
        public double couponMeetMoney;
        public int couponStatus;
        public int id;
        public long couponStartPeriod;
    }
}

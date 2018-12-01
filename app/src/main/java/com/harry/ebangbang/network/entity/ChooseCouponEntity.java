package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/11/30.
 */
public class ChooseCouponEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"startPeriod":1538755200000,"money":60,"endPeriod":1540545440000,"name":"哈哈","id":4,"meetMoney":10},{"startPeriod":1539756636000,"money":2.16,"endPeriod":1540966243000,"name":"优惠卷11","id":5,"meetMoney":5.23}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * startPeriod : 1538755200000
         * money : 60
         * endPeriod : 1540545440000
         * name : 哈哈
         * id : 4
         * meetMoney : 10
         */

        public long startPeriod;
        public double money;
        public long endPeriod;
        public String name;
        public int id;
        public double meetMoney;
    }
}

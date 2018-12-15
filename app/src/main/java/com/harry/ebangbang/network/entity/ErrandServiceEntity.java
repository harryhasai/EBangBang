package com.harry.ebangbang.network.entity;

/**
 * Created by Harry on 2018/12/15.
 */
public class ErrandServiceEntity {

    /**
     * msg : 成功生成订单
     * code : 1
     * data : {"orderNumber":"1541232657135925152","orderId":2}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * orderNumber : 1541232657135925152
         * orderId : 2
         */

        public String orderNumber;
        public int orderId;
    }
}

package com.harry.ebangbang.network.entity;

/**
 * Created by Harry on 2018/11/28.
 */
public class GeneratePrepaidOrdersEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : {"orderNumber":"1543629820743715016","money":"409","orderFormId":57}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * orderNumber : 1543629820743715016
         * money : 409
         * orderFormId : 57
         */

        public String orderNumber;
        public String money;
        public int orderFormId;
    }
}

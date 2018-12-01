package com.harry.ebangbang.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Harry on 2018/12/1.
 */
public class WxPayEntity {

    /**
     * msg : {"appid":"wx76be99258cfaf85a","noncestr":"4ToBoDepShtsYJpA","package":"Sign=WXPay","partnerid":"1517376531","prepayid":"wx01103824448592de84f82ce21318767792","sign":"829370EDEFE98A7DC4C2FF2CC927B889","timestamp":1543631905}
     * code : 100
     */

    public MsgBean msg;
    public String code;

    public static class MsgBean {
        /**
         * appid : wx76be99258cfaf85a
         * noncestr : 4ToBoDepShtsYJpA
         * package : Sign=WXPay
         * partnerid : 1517376531
         * prepayid : wx01103824448592de84f82ce21318767792
         * sign : 829370EDEFE98A7DC4C2FF2CC927B889
         * timestamp : 1543631905
         */

        public String appid;
        public String noncestr;
        @SerializedName("package")
        public String packageX;
        public String partnerid;
        public String prepayid;
        public String sign;
        public int timestamp;
    }
}

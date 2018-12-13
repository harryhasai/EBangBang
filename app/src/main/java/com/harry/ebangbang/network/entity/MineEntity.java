package com.harry.ebangbang.network.entity;

/**
 * Created by Harry on 2018/12/13.
 */
public class MineEntity {

    /**
     * msg : 查询成功
     * code : 1
     * userDetails : {"id":11,"userId":20,"headAddress":"20180918160606383_217.png","nickname":"Po2mx18xR","shippingAddress":null,"birthday":null,"siteLongitude":"112.45361","siteLatitude":"34.61812","weixin":null,"qq":null,"weibo":null}
     */

    public String msg;
    public int code;
    public UserDetailsBean userDetails;

    public static class UserDetailsBean {
        /**
         * id : 11
         * userId : 20
         * headAddress : 20180918160606383_217.png
         * nickname : Po2mx18xR
         * shippingAddress : null
         * birthday : null
         * siteLongitude : 112.45361
         * siteLatitude : 34.61812
         * weixin : null
         * qq : null
         * weibo : null
         */

        public int id;
        public int userId;
        public String headAddress;
        public String nickname;
        public Object shippingAddress;
        public String birthday;
        public String siteLongitude;
        public String siteLatitude;
        public Object weixin;
        public Object qq;
        public Object weibo;
    }
}

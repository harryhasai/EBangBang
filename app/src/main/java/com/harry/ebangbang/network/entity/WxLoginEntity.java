package com.harry.ebangbang.network.entity;

/**
 * Created by Harry on 2018/12/14.
 */
public class WxLoginEntity {

    /**
     * msg : 授权成功
     * headAddress : 20181214102828959_461.jpg
     * code : 2
     * openid : ofbAV1tpHv_GoXUGG5MKMgCmkmdc
     * nickname : [机智] 快到碗里来！
     * Isbinding : 0
     */

    public String msg;
    public String headAddress;
    public int code;
    public String openid;
    public String nickname;
    public int Isbinding;
    public String headPortraitLink;
    public DataBean data;

    public static class DataBean {
        /**
         * id : 207
         * loginName : 13633898977
         * password : null
         * salt : null
         * phone : 13633898977
         * state : false
         * type : user
         * remark : null
         * lastLoginTime : null
         * createTime : null
         * createUser : null
         * updateTime : null
         * updateUser : null
         * isDelete : null
         * plainPassword : null
         * nickname : [机智] 快到碗里来！
         * shippingAddress : null
         * birthday : null
         * siteLongitude : null
         * siteLatitude : null
         * headAddress : 20181214143413098_317.jpg
         */

        public int id;
        public String loginName;
        public Object password;
        public Object salt;
        public String phone;
        public boolean state;
        public String type;
        public Object remark;
        public Object lastLoginTime;
        public Object createTime;
        public Object createUser;
        public Object updateTime;
        public Object updateUser;
        public Object isDelete;
        public Object plainPassword;
        public String nickname;
        public Object shippingAddress;
        public Object birthday;
        public Object siteLongitude;
        public Object siteLatitude;
        public String headAddress;
    }
}

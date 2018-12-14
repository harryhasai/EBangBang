package com.harry.ebangbang.network.entity;

/**
 * Created by Harry on 2018/12/14.
 */
public class BindingPhoneEntity {

    /**
     * msg : 创建成功
     * code : 1
     * data : {"id":206,"loginName":"13633898977","password":null,"salt":null,"phone":"13633898977","state":false,"type":null,"remark":"用户","lastLoginTime":null,"createTime":null,"createUser":null,"updateTime":null,"updateUser":null,"isDelete":null,"plainPassword":null,"nickname":"[机智] 快到碗里来！","shippingAddress":null,"birthday":null,"siteLongitude":null,"siteLatitude":null,"headAddress":null}
     */

    public String msg;
    public String headPortraitLink;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * id : 206
         * loginName : 13633898977
         * password : null
         * salt : null
         * phone : 13633898977
         * state : false
         * type : null
         * remark : 用户
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
         * headAddress : null
         */

        public int id;
        public String loginName;
        public Object password;
        public Object salt;
        public String phone;
        public boolean state;
        public Object type;
        public String remark;
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
        public Object headAddress;
    }
}

package com.harry.ebangbang.network.entity;

/**
 * Created by Harry on 2018/11/6.
 */
public class LoginEntity {

    /**
     * msg : 登录成功
     * code : 1
     * data : {"id":5,"loginName":"yonghu","password":"0ac372901b1731d6a44fc70500f274b8ae73f429","salt":"dba00f8571f58b14","phone":"18888888888","state":false,"type":"yonghu","remark":"用户","lastLoginTime":null,"createTime":1538012964000,"createUser":1,"updateTime":null,"updateUser":null,"isDelete":false,"plainPassword":null,"nickname":"hahahah","shippingAddress":null,"birthday":null,"siteLongitude":null,"siteLatitude":null,"headAddress":"20181108095906008_846.jpg"}
     * token : 10718984F6AEADCA511F40323D626271
     * headPortraitLink : http://116.62.218.136:9055/guozongapp/file/
     */

    public String msg;
    public int code;
    public DataBean data;
    public String token;
    public String headPortraitLink;

    public static class DataBean {
        /**
         * id : 5
         * loginName : yonghu
         * password : 0ac372901b1731d6a44fc70500f274b8ae73f429
         * salt : dba00f8571f58b14
         * phone : 18888888888
         * state : false
         * type : yonghu
         * remark : 用户
         * lastLoginTime : null
         * createTime : 1538012964000
         * createUser : 1
         * updateTime : null
         * updateUser : null
         * isDelete : false
         * plainPassword : null
         * nickname : hahahah
         * shippingAddress : null
         * birthday : null
         * siteLongitude : null
         * siteLatitude : null
         * headAddress : 20181108095906008_846.jpg
         */

        public int id;
        public String loginName;
        public String password;
        public String salt;
        public String phone;
        public boolean state;
        public String type;
        public String remark;
        public Object lastLoginTime;
        public long createTime;
        public int createUser;
        public Object updateTime;
        public Object updateUser;
        public boolean isDelete;
        public Object plainPassword;
        public String nickname;
        public Object shippingAddress;
        public Object birthday;
        public Object siteLongitude;
        public Object siteLatitude;
        public String headAddress;
    }
}

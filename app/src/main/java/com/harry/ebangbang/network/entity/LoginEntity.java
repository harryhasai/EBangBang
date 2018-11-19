package com.harry.ebangbang.network.entity;

/**
 * Created by Harry on 2018/11/6.
 */
public class LoginEntity {

    /**
     * msg : 登录成功
     * code : 1
     * data : {"id":6,"loginName":"yuangong","password":"0ac372901b1731d6a44fc70500f274b8ae73f429","salt":"dba00f8571f58b14","phone":"17777777777","state":false,"type":"yuangong","remark":"员工","lastLoginTime":1538186029000,"createTime":1538186038000,"createUser":1,"updateTime":null,"updateUser":null,"isDelete":false,"plainPassword":null,"nickname":null,"shippingAddress":null,"birthday":null,"siteLongitude":null,"siteLatitude":null}
     * token : D67C670C5EC5F68413850E2A298C9E5D
     */

    public String msg;
    public int code;
    public DataBean data;
    public String token;

    public static class DataBean {
        /**
         * id : 6
         * loginName : yuangong
         * password : 0ac372901b1731d6a44fc70500f274b8ae73f429
         * salt : dba00f8571f58b14
         * phone : 17777777777
         * state : false
         * type : yuangong
         * remark : 员工
         * lastLoginTime : 1538186029000
         * createTime : 1538186038000
         * createUser : 1
         * updateTime : null
         * updateUser : null
         * isDelete : false
         * plainPassword : null
         * nickname : null
         * shippingAddress : null
         * birthday : null
         * siteLongitude : null
         * siteLatitude : null
         */

        public int id;
        public String loginName;
        public String password;
        public String salt;
        public String phone;
        public boolean state;
        public String type;
        public String remark;
        public long lastLoginTime;
        public long createTime;
        public int createUser;
        public Object updateTime;
        public Object updateUser;
        public boolean isDelete;
        public Object plainPassword;
        public Object nickname;
        public Object shippingAddress;
        public Object birthday;
        public Object siteLongitude;
        public Object siteLatitude;
    }
}

package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/12/15.
 */
public class ErrandListEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"orderNumber":"1540459502109400482","practicalMoney":30,"addressee":"中州国际","orderStatus":0,"remark":"快卡快","id":1,"collect":"18766132222"},{"orderNumber":"1541232657135925152","practicalMoney":50,"addressee":"河南洛阳","orderStatus":0,"remark":"啊啊啊啊","id":2,"collect":"河南1"},{"orderNumber":"1544838263447723794","practicalMoney":56,"addressee":"河南省洛阳市","orderStatus":0,"remark":"45454","id":3,"collect":"北京市东城区霞公府街3号霞公府& 11111"},{"orderNumber":"154485637880634596","practicalMoney":5,"addressee":"18711111111","orderStatus":0,"remark":"41245","id":4,"collect":"18711111111"},{"orderNumber":"154485663363780494","practicalMoney":11279,"addressee":"中州国际","orderStatus":0,"remark":"1212","id":5,"collect":"18711111111"},{"orderNumber":"1544857920511230039","practicalMoney":10011,"addressee":"北京市东城区霞公府街3号霞公府& 11111111","orderStatus":0,"remark":"4545","id":6,"collect":"北京市东城区霞公府街3号霞公府& 11111","rideName":"dasdad","ridePhone":"454554","rideLongitude":454.4545,"rideLatitude":4545454454},{"orderNumber":"1544858001500689986","practicalMoney":10011,"addressee":"北京市东城区霞公府街3号霞公府& 11111111","orderStatus":0,"remark":"4545","id":7,"collect":"北京市东城区霞公府街3号霞公府& 11111"},{"orderNumber":"1544858300846425325","practicalMoney":10011.02,"addressee":"北京市东城区霞公府街3号霞公府& 11111111","orderStatus":0,"remark":"77777","id":8,"collect":"北京市东城区霞公府街3号霞公府& 11111"}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * orderNumber : 1540459502109400482
         * practicalMoney : 30
         * addressee : 中州国际
         * orderStatus : 0
         * remark : 快卡快
         * id : 1
         * collect : 18766132222
         * rideName : dasdad
         * ridePhone : 454554
         * rideLongitude : 454.4545
         * rideLatitude : 4545454454
         */

        public String orderNumber;
        public double practicalMoney;
        public String addressee;
        public int orderStatus;
        public String remark;
        public int id;
        public String collect;
        public String rideName;
        public String ridePhone;
        public double rideLongitude;
        public double rideLatitude;
    }
}

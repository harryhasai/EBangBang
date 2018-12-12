package com.harry.ebangbang.network.entity;

/**
 * Created by Harry on 2018/12/12.
 */
public class AddCommentEntity {
    /**
     * msg : 查询成功
     * code : 1
     * data : {"accomplishTime":1537410288000,"orderId":2,"shopLogo":"20181124110601392_843.jpg","rideImg":"20181109131210517_632.jpg","shopName":"刘海鸥DE店","shopId":10,"rideId":6,"rideName":"王五"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * accomplishTime : 1537410288000
         * orderId : 2
         * shopLogo : 20181124110601392_843.jpg
         * rideImg : 20181109131210517_632.jpg
         * shopName : 刘海鸥DE店
         * shopId : 10
         * rideId : 6
         * rideName : 王五
         */

        public long accomplishTime;
        public int orderId;
        public String shopLogo;
        public String rideImg;
        public String shopName;
        public int shopId;
        public int rideId;
        public String rideName;
    }
}

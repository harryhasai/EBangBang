package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/12/14.
 */
public class AddressManagementEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"id":4,"phone":"18766111111","userId":20,"name":"1231","site":"北京市东城区霞公府街3号霞公府&  11111","longitude":"112.431253","latitude":"34.667978","isDefault":1,"createTime":1539684732000,"createId":20,"isDelete":0},{"id":5,"phone":"2131231231","userId":20,"name":"Kk","site":"北京市东城区霞公府街3号霞公府& 11111111","longitude":"112.482834","latitude":"34.653073","isDefault":0,"createTime":1539685023000,"createId":20,"isDelete":0},{"id":6,"phone":"18711111111","userId":20,"name":"Kk1","site":"18711111111","longitude":"112.482834","latitude":"34.653073","isDefault":0,"createTime":1539685940000,"createId":20,"isDelete":0},{"id":7,"phone":"18766121234","userId":20,"name":"Kk1","site":"18766121234","longitude":"112.482834","latitude":"34.653073","isDefault":0,"createTime":1539687132000,"createId":20,"isDelete":0},{"id":8,"phone":"18766132222","userId":20,"name":"Kk2","site":"18766132222","longitude":"112.482834","latitude":"34.653073","isDefault":0,"createTime":1539740448000,"createId":20,"isDelete":0},{"id":10,"phone":"15555555555","userId":20,"name":"常璐","site":"1单元2603","longitude":"34.663817524589405","latitude":"34.663817524589405","isDefault":0,"createTime":1543541639000,"createId":20,"isDelete":0}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 4
         * phone : 18766111111
         * userId : 20
         * name : 1231
         * site : 北京市东城区霞公府街3号霞公府&  11111
         * longitude : 112.431253
         * latitude : 34.667978
         * isDefault : 1
         * createTime : 1539684732000
         * createId : 20
         * isDelete : 0
         */

        public int id;
        public String phone;
        public int userId;
        public String name;
        public String site;
        public String longitude;
        public String latitude;
        public int isDefault;
        public long createTime;
        public int createId;
        public int isDelete;
    }
}

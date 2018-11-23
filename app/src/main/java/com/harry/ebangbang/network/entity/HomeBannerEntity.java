package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/11/20.
 */
public class HomeBannerEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"id":1,"bannerLink":"20180926150827295_858.jpg","createUser":1,"createTime":1539331674000,"relevanceId":null,"remark":"11111111111","relevanceUrl":null,"isDelect":0}]
     * attachmentPath : http://127.0.0.1:8080/guozongapp/file/
     */

    public String msg;
    public int code;
    public String attachmentPath;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1
         * bannerLink : 20180926150827295_858.jpg
         * createUser : 1
         * createTime : 1539331674000
         * relevanceId : null
         * remark : 11111111111
         * relevanceUrl : null
         * isDelect : 0
         */

        public int id;
        public String bannerLink;
        public int createUser;
        public long createTime;
        public Object relevanceId;
        public String remark;
        public Object relevanceUrl;
        public int isDelect;
    }
}

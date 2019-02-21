package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/12/25.
 */
public class SelectContentAddressEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"id":110000,"name":"北京市","arealevel":1,"parentId":null},{"id":120000,"name":"天津市","arealevel":1,"parentId":null},{"id":130000,"name":"河北省","arealevel":1,"parentId":null},{"id":140000,"name":"山西省","arealevel":1,"parentId":null},{"id":150000,"name":"内蒙古自治区","arealevel":1,"parentId":null},{"id":210000,"name":"辽宁省","arealevel":1,"parentId":null},{"id":220000,"name":"吉林省","arealevel":1,"parentId":null},{"id":230000,"name":"黑龙江省","arealevel":1,"parentId":null},{"id":310000,"name":"上海市","arealevel":1,"parentId":null},{"id":320000,"name":"江苏省","arealevel":1,"parentId":null},{"id":330000,"name":"浙江省","arealevel":1,"parentId":null},{"id":340000,"name":"安徽省","arealevel":1,"parentId":null},{"id":350000,"name":"福建省","arealevel":1,"parentId":null},{"id":360000,"name":"江西省","arealevel":1,"parentId":null},{"id":370000,"name":"山东省","arealevel":1,"parentId":null},{"id":410000,"name":"河南省","arealevel":1,"parentId":null},{"id":420000,"name":"湖北省","arealevel":1,"parentId":null},{"id":430000,"name":"湖南省","arealevel":1,"parentId":null},{"id":440000,"name":"广东省","arealevel":1,"parentId":null},{"id":450000,"name":"广西壮族自治区","arealevel":1,"parentId":null},{"id":460000,"name":"海南省","arealevel":1,"parentId":null},{"id":500000,"name":"重庆市","arealevel":1,"parentId":null},{"id":510000,"name":"四川省","arealevel":1,"parentId":null},{"id":520000,"name":"贵州省","arealevel":1,"parentId":null},{"id":530000,"name":"云南省","arealevel":1,"parentId":null},{"id":540000,"name":"西藏自治区","arealevel":1,"parentId":null},{"id":610000,"name":"陕西省","arealevel":1,"parentId":null},{"id":620000,"name":"甘肃省","arealevel":1,"parentId":null},{"id":630000,"name":"青海省","arealevel":1,"parentId":null},{"id":640000,"name":"宁夏回族自治区","arealevel":1,"parentId":null},{"id":650000,"name":"新疆维吾尔自治区","arealevel":1,"parentId":null},{"id":710000,"name":"台湾省","arealevel":1,"parentId":null},{"id":810000,"name":"香港特别行政区","arealevel":1,"parentId":null},{"id":820000,"name":"澳门特别行政区","arealevel":1,"parentId":null}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 110000
         * name : 北京市
         * arealevel : 1
         * parentId : null
         */

        public int id;
        public String name;
        public int arealevel;
        public Object parentId;
    }
}

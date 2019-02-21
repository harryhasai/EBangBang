package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/12/25.
 */
public class SelectContentAddressChildEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"id":410100,"name":"郑州市","arealevel":2,"parentId":410000},{"id":410200,"name":"开封市","arealevel":2,"parentId":410000},{"id":410300,"name":"洛阳市","arealevel":2,"parentId":410000},{"id":410400,"name":"平顶山市","arealevel":2,"parentId":410000},{"id":410500,"name":"安阳市","arealevel":2,"parentId":410000},{"id":410600,"name":"鹤壁市","arealevel":2,"parentId":410000},{"id":410700,"name":"新乡市","arealevel":2,"parentId":410000},{"id":410800,"name":"焦作市","arealevel":2,"parentId":410000},{"id":410900,"name":"濮阳市","arealevel":2,"parentId":410000},{"id":411000,"name":"许昌市","arealevel":2,"parentId":410000},{"id":411100,"name":"漯河市","arealevel":2,"parentId":410000},{"id":411200,"name":"三门峡市","arealevel":2,"parentId":410000},{"id":411300,"name":"南阳市","arealevel":2,"parentId":410000},{"id":411400,"name":"商丘市","arealevel":2,"parentId":410000},{"id":411500,"name":"信阳市","arealevel":2,"parentId":410000},{"id":411600,"name":"周口市","arealevel":2,"parentId":410000},{"id":411700,"name":"驻马店市","arealevel":2,"parentId":410000},{"id":411800,"name":"济源市","arealevel":2,"parentId":410000}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 410100
         * name : 郑州市
         * arealevel : 2
         * parentId : 410000
         */

        public int id;
        public String name;
        public int arealevel;
        public int parentId;
    }
}

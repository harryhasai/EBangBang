package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/12/10.
 */
public class GoodsDetailEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : {"goodsImg":"20181108095927871_558.jpg","comments":[{"score":4,"img":"1231","userimg":"20180918160606383_217.png","userNickName":"Po2mx18xR","time":1544414315000,"content":"sadasddas"},{"score":4,"img":"213123","userimg":"20180918160606383_217.png","userNickName":"Po2mx18xR","time":1544414344000,"content":"2312"},{"score":4,"img":"dasdasd","userimg":"20180918160606383_217.png","userNickName":"Po2mx18xR","time":1544414533000,"content":"sadasdsadas"}],"salesVolume":0,"price":100,"details":"","id":4,"goodsName":"牛肉"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * goodsImg : 20181108095927871_558.jpg
         * comments : [{"score":4,"img":"1231","userimg":"20180918160606383_217.png","userNickName":"Po2mx18xR","time":1544414315000,"content":"sadasddas"},{"score":4,"img":"213123","userimg":"20180918160606383_217.png","userNickName":"Po2mx18xR","time":1544414344000,"content":"2312"},{"score":4,"img":"dasdasd","userimg":"20180918160606383_217.png","userNickName":"Po2mx18xR","time":1544414533000,"content":"sadasdsadas"}]
         * salesVolume : 0
         * price : 100
         * details :
         * id : 4
         * goodsName : 牛肉
         */

        public String goodsImg;
        public int salesVolume;
        public double price;
        public String details;
        public int id;
        public String goodsName;
        public List<CommentsBean> comments;

        public static class CommentsBean {
            /**
             * score : 4
             * img : 1231
             * userimg : 20180918160606383_217.png
             * userNickName : Po2mx18xR
             * time : 1544414315000
             * content : sadasddas
             */

            public int score;
            public String img;
            public String userimg;
            public String userNickName;
            public long time;
            public String content;
        }
    }
}

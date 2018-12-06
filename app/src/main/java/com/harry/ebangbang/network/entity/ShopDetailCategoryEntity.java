package com.harry.ebangbang.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/12/4.
 */
public class ShopDetailCategoryEntity {

    /**
     * msg : 查询成功
     * code : 1
     * data : [{"catName":"妈妈","shopClassifyId":1},{"catName":"汤羹","shopClassifyId":2},{"catName":"甜点","shopClassifyId":3},{"catName":"主食","shopClassifyId":4},{"catName":"饮料","shopClassifyId":5},{"catName":"凉菜","shopClassifyId":8},{"catName":"奶奶","shopClassifyId":9},{"catName":"太可怜了","shopClassifyId":10},{"catName":"空军建军节","shopClassifyId":11},{"catName":"啦咯KKK","shopClassifyId":12}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * catName : 妈妈
         * shopClassifyId : 1
         */

        public String catName;
        public int shopClassifyId;
    }
}

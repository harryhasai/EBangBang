package com.harry.ebangbang.function.shopping_cart;

/**
 * Created by Harry on 2018/11/28.
 */
public class JsonFormatBean {

    private String num;
    private String id;

    public JsonFormatBean(String id, String num) {
        this.num = num;
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public String getId() {
        return id;
    }
}

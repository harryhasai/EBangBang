package com.harry.ebangbang.function.shopping_cart;

/**
 * Created by Harry on 2018/11/28.
 */
public class JsonFormatBean {

    private int num;
    private int id;

    public JsonFormatBean(int id, int num) {
        this.num = num;
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public int getId() {
        return id;
    }
}

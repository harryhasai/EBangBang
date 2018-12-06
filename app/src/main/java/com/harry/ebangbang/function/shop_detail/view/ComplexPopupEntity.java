package com.harry.ebangbang.function.shop_detail.view;

/**
 * Created by Harry on 2018/12/6.
 */
public class ComplexPopupEntity {
    public int id;
    public int count;
    public String goodsName;
    public double price;

    public ComplexPopupEntity(int id, int count, String goodsName, double price) {
        this.id = id;
        this.count = count;
        this.goodsName = goodsName;
        this.price = price;
    }
}

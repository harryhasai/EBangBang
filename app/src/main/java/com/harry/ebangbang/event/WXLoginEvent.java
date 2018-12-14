package com.harry.ebangbang.event;

/**
 * Created by Harry on 2018/12/14.
 */
public class WXLoginEvent {

    private String wxCode;

    public WXLoginEvent(String wxCode) {
        this.wxCode = wxCode;
    }

    public String getWxCode() {
        return wxCode;
    }
}

package com.harry.ebangbang.network.entity;

/**
 * Created by Harry on 2018/12/1.
 */
public class AliPayEntity {

    /**
     * msg : 订单生成成功
     * result : alipay_sdk=alipay-sdk-java-3.3.49.ALL&app_id=2018102461794469&biz_content=%7B%22body%22%3A%221%22%2C%22out_trade_no%22%3A%221543636610431430939%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E6%94%AF%E4%BB%98%E5%AE%9D%E5%85%85%E5%80%BC%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22410.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F39.108.239.193%3A8080%2FalipayNotify&sign=hRpojxP9gKsEu4lLroh84coTtw3zI2GWDiYtIo1juMnf1ap8uF2ZFvb9IVnZQcObsq5LB0XSuB1RJq7N6tjp5KXBG7wY%2FuDqaLBXpx4SsDAPKaW5Katv0UiBa8MmlADFDGWTMi%2FW8RvmOuYsuxIXuwwQnrg3aQigPp9MwXqa7otZzV12bxNBFkO9%2B2NSR3ipSRh6TLqvHg0QEhMWSrW%2BNvgl%2FWYTe%2FjhkgRdQT%2FqVkqfeX9KgHj26yqvHPzzUyXnPJbsKusVcgfe7pgAYGJU%2Ba8P1qOzRgPO%2FT9%2BPdHVz0Rv48rgp7KH3jeG8%2FwEEzvsDRjiWJfMbdQJiyigtK%2Fwbw%3D%3D&sign_type=RSA2&timestamp=2018-12-01+14%3A08%3A01&version=1.0
     * code : 1
     */

    public String msg;
    public String result;
    public int code;
}

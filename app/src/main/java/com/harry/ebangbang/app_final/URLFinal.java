package com.harry.ebangbang.app_final;

/**
 * Created by Harry on 2018/9/25.
 * URL地址类
 */
public class URLFinal {
    /**
     * BaseUrl
     */
    public static final String BASE_URL = "http://116.62.218.136:9055/guozongApp/";

    /**
     * 登录
     */
    public static final String LOGIN = "mobile/Login";
    /**
     * 验证验证码
     */
    public static final String CHECK_VERIFY_CODE = "mobile/SelectVerify";
    /**
     * 获取验证码
     */
    public static final String GET_VERIFY_CODE = "mobile/toregister";
    public static final String GET_VERIFY_CODE_FORGET_PASSWORD = "mobile/forgetAcquire";
    /**
     * 注册
     */
    public static final String REGISTER = "mobile/createUser";
    /**
     * 忘记密码接口
     */
    public static final String FORGET_PASSWORD = "mobile/forgetPassword";
    /**
     * 查询所有推荐的商家
     */
    public static final String HOME_GET_LIST = "mobile/home/allrecommend";
    /**
     * 首页banner
     */
    public static final String HOME_BANNER = "mobile/home/banner";
    /**
     * 进入首页获取当前位置
     */
    public static final String CURRENT_POSITION = "mobile/updataUserSite";
    /**
     * 获取一级分类
     */
    public static final String GET_FIRST_LEVEL = "mobile/home/firstOrder";
    /**
     * 获取一级分类下的二级分类
     */
    public static final String GET_SECOND_LEVEL = "mobile/home/classifyShop";
}

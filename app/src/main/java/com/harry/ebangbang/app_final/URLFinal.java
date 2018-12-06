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
//    public static final String BASE_URL = "http://15386nv005.iok.la/guozongApp/";

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
    /**
     * 查询我的购物车
     */
    public static final String GET_SHOPPING_LIST = "mobile/shoppingTrolley/selectUserShopShoppingTrolley1";
    /**
     * 购物车 - 移除商家及商品
     */
    public static final String DELETE_SHOPPING_LIST_ITEM = "mobile/shoppingTrolley/deleteShopShoppingTrolley";
    /**
     * 购物车 - 增加商品数量
     */
    public static final String ADD_COUNT = "mobile/shoppingTrolley/addGoodsNumber";
    /**
     * 购物车 - 减去商品数量
     */
    public static final String REDUCE_COUNT = "mobile/shoppingTrolley/minusGoodsNumber";
    /**
     * 购物车 - 是否选中
     */
    public static final String CHECK_GOODS = "mobile/shoppingTrolley/isPitchOn";
    /**
     * 跳转预支付订单
     */
    public static final String GET_ORDER_DETAIL = "mobile/shoppingTrolley/produceOrderForm";
    /**
     * 进入预支付状态 生成订单
     */
    public static final String SUBMIT_ORDER = "mobile/shoppingTrolley/WaitingForThePayment";
    /**
     * 新增收货地址
     */
    public static final String ADD_ADDRESS = "mobile/PersonalCenter/addReceiverAddress";
    /**
     * 查询条件满足的优惠卷
     */
    public static final String GET_COUPON = "mobile/shoppingTrolley/lookCoupon";
    /**
     * 微信统一下单接口
     */
    public static final String WX_PAY = "model/wxPay/api/wx/createOrder";
    /**
     * 支付宝下单
     */
    public static final String ALI_PAY = "alipay/api/alipay/createOrder";
    /**
     * 全局商品搜索
     */
    public static final String SEARCH = "mobile/home/linkName";
    /**
     * 查看商家的分类
     */
    public static final String SHOP_DETAIL_CATEGORY = "mobile/goods/selectShopClassify";
    /**
     * 查询此商家下的所有商品
     */
    public static final String SHOP_DETAIL_CHILD = "mobile/goods/goodsList";
}

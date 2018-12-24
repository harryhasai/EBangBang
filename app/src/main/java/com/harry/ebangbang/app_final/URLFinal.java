package com.harry.ebangbang.app_final;

/**
 * Created by Harry on 2018/9/25.
 * URL地址类
 */
public class URLFinal {
    /**
     * BaseUrl
     */
//    public static final String BASE_URL = "http://116.62.218.136:9055/guozongApp/";
    public static final String BASE_URL = "https://www.ebbtcps.com/";
//    public static final String BASE_URL = "http://15386nv005.iok.la/guozongApp/";
//    public static final String BASE_URL = "http://192.168.0.116:8080/guozongApp/";

    /**
     * 登录
     */
    public static final String LOGIN = "mobile/Login";
    public static final String WX_LOGIN = "model/wx/wxLogin";
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
     * 修改地址
     */
    public static final String MODIFY_ADDRESS = "mobile/PersonalCenter/updateReceiverAddress";
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
    /**
     * 批量增加购物车
     */
    public static final String SHOP_DETAIL_ADD_GOODS = "mobile/shoppingTrolley/batchAddShoppingTrolley";
    /**
     * 查询商品详情
     */
    public static final String GET_GOODS_DETAIL = "mobile/goods/goodsDetails";
    /**
     * 查询用户所有订单
     */
    public static final String GET_ORDER_LIST = "modele/orderForm/lockOrderForm1";
    /**
     * 查询用户订单详情
     */
    public static final String ORDER_DETAIL = "modele/orderForm/selectOrderFormId";
    /**
     * 确认收货
     */
    public static final String CONFIRM_GOODS = "modele/orderForm/affirmReceive";
    /**
     * 增加时候查询订单评价订单的信息
     */
    public static final String GET_COMMENT_INFO = "modele/orderForm/selectOrderForm";
    /**
     * 增加评论接口
     */
    public static final String ADD_COMMENT = "modele/orderForm/addAppraise";
    /**
     * 进入个人中心
     */
    public static final String GET_USER_INFO = "mobile/PersonalCenter/enterInto";
    /**
     * 上传头像
     */
    public static final String UPLOAD_HEADER = "mobile/PersonalCenter/uploadHeadPortrait";
    /**
     * 获取客服电话
     */
    public static final String CUSTOMER_SERVICE = "mobile/commonTools/lockOrderForm";
    /**
     * 修改个人信息
     */
    public static final String SAVE_USER_INFO = "mobile/PersonalCenter/updateDetails";
    /**
     * 微信登录 - 绑定新手机号
     */
    public static final String BINDING_NEW_PHONE = "mobile/newBindingWX";
    /**
     * 微信登录 - 绑定已有手机号
     */
    public static final String BINDING_OLD_PHONE = "mobile/bindingWX";
    /**
     * 查看收货地址
     */
    public static final String GET_ADDRESS_LIST = "mobile/PersonalCenter/lookReceiverAddress";
    /**
     * 设为默认
     */
    public static final String SET_ADDRESS_DEFAULT = "mobile/PersonalCenter/defaultSite";
    /**
     * 删除地址
     */
    public static final String DELETE_ADDRESS = "mobile/PersonalCenter/deleteReceiverAddress";
    /**
     * 跑腿下单
     */
    public static final String ERRAND_SERVICE = "mobile/errand/addErrand";
    /**
     * 计算跑腿金额
     */
    public static final String COUNT_MONEY = "mobile/errand/countMoney";
    /**
     * 查看跑腿订单
     */
    public static final String ERRAND_MONEY = "mobile/errand/selectUserList";
    public static final String MY_COUPON = "mobile/PersonalCenter/selectMyCoupon";
}

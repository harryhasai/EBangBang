package com.harry.ebangbang.function.submit_order;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.GeneratePrepaidOrdersEntity;
import com.harry.ebangbang.network.entity.SubmitOrderEntity;
import com.harry.ebangbang.network.service.SubmitOrderService;
import com.harry.ebangbang.utils.RetrofitHelper;
import com.harry.ebangbang.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/11/28.
 */
public class SubmitOrderModel extends BaseModel {

    private final SubmitOrderService service;

    public SubmitOrderModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(SubmitOrderService.class);
    }

    public void getPageDetail(String shopId, String ids, Observer<SubmitOrderEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        params.put("shopId", shopId);
        params.put("ids", ids);

        service.getPageDetail(URLFinal.GET_ORDER_DETAIL, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void submitOrder(String shopId, String addresseeId, String money,
                            String mealBoxMoney, String dispatchingMoney, String meetSubtract,
                            String goodsIds, String remark, Observer<GeneratePrepaidOrdersEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        params.put("shopId", shopId);//商铺id
        params.put("addresseeId", addresseeId);//收件人id
        params.put("money", money);//总金额
        params.put("mealBoxMoney", mealBoxMoney);//餐盒费
        params.put("dispatchingMoney", dispatchingMoney);//配送费
        params.put("meetSubtract", meetSubtract);//满减减去金额
        params.put("goodsIds", goodsIds);//商品ids id 商品id num 商品数量 例如 [ { "id": 1, "num": 2 }, { "id": 2, "num": 3 } ]
        params.put("remark", remark);//备注

        service.submitOrder(URLFinal.SUBMIT_ORDER, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}

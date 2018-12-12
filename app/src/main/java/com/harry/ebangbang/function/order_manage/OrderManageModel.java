package com.harry.ebangbang.function.order_manage;

import android.text.TextUtils;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.OrderManageEntity;
import com.harry.ebangbang.network.service.OrderManageService;
import com.harry.ebangbang.utils.RetrofitHelper;
import com.harry.ebangbang.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/12/11.
 */
public class OrderManageModel extends BaseModel {

    private final OrderManageService service;

    public OrderManageModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(OrderManageService.class);
    }

    public void getOrderList(String orderFormStatus, int pageNum, Observer<OrderManageEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        if (!TextUtils.isEmpty(orderFormStatus)) {
            //订单状态  -1未付款 1 待配送  4待收货  5 订单已完成未评价   6退返货    7完成并已评价
            params.put("orderFormStatus", orderFormStatus);
        }
        params.put("pageNum", String.valueOf(pageNum));
        params.put("pageSize", String.valueOf(10));

        service.getOrderList(URLFinal.GET_ORDER_LIST, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

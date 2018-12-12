package com.harry.ebangbang.function.order_detail;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.OrderDetailEntity;
import com.harry.ebangbang.network.service.OrderDetailService;
import com.harry.ebangbang.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/12/12.
 */
public class OrderDetailModel extends BaseModel {

    private final OrderDetailService service;

    public OrderDetailModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(OrderDetailService.class);
    }

    public void getOrderDetail(String orderFormId, Observer<OrderDetailEntity> observer) {
        Map<String, String> params = new HashMap<>();
//        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        params.put("orderFormId", orderFormId);

        service.getOrderDetail(URLFinal.ORDER_DETAIL, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

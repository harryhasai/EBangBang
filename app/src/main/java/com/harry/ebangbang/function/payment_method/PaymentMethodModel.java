package com.harry.ebangbang.function.payment_method;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.AliPayEntity;
import com.harry.ebangbang.network.entity.WxPayEntity;
import com.harry.ebangbang.network.service.PaymentMethodService;
import com.harry.ebangbang.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/12/1.
 */
public class PaymentMethodModel extends BaseModel {

    private final PaymentMethodService service;

    public PaymentMethodModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(PaymentMethodService.class);
    }

    public void wxPay(String orderFormId, Observer<WxPayEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("orderFormId", orderFormId);//订单id
        params.put("attach", "1");//1 为商品下单 2 为跑腿下单

        service.wxPay(URLFinal.WX_PAY, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void aliPay(String orderFormId, Observer<AliPayEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("orderformId", orderFormId);//订单id
        params.put("attach", "1");//1 为商品下单 2 为跑腿下单

        service.aliPay(URLFinal.ALI_PAY, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

package com.harry.ebangbang.network.service;

import com.harry.ebangbang.network.entity.AliPayEntity;
import com.harry.ebangbang.network.entity.WxPayEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/12/1.
 */
public interface PaymentMethodService {

    @FormUrlEncoded
    @POST
    Observable<WxPayEntity> wxPay(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<AliPayEntity> aliPay(@Url String url, @FieldMap Map<String, String> params);
}

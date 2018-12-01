package com.harry.ebangbang.network.service;

import com.harry.ebangbang.network.entity.GeneratePrepaidOrdersEntity;
import com.harry.ebangbang.network.entity.SubmitOrderEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/11/28.
 */
public interface SubmitOrderService {

    @FormUrlEncoded
    @POST
    Observable<SubmitOrderEntity> getPageDetail(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<GeneratePrepaidOrdersEntity> submitOrder(@Url String url, @FieldMap Map<String, String> params);
}

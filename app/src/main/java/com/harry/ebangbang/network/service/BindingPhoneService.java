package com.harry.ebangbang.network.service;


import com.harry.ebangbang.network.entity.BindingPhoneEntity;
import com.harry.ebangbang.network.entity.CommonEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/8/20.
 */
public interface BindingPhoneService {

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> getVerifyCode(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> checkVerifyCode(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<BindingPhoneEntity> bindingNewPhone(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<BindingPhoneEntity> bindingOldPhone(@Url String url, @FieldMap Map<String, String> params);
}

package com.harry.ebangbang.network.service;

import com.harry.ebangbang.network.entity.CommonEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/11/19.
 */
public interface MerchantEntryService {

    @FormUrlEncoded
    @POST
//    @Headers({"Content-Type: application/x-www-form-urlencoded;charset=UTF-8"})
    Observable<CommonEntity> register(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> getVerifyCode(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> checkVerifyCode(@Url String url, @FieldMap Map<String, String> params);


}

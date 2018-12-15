package com.harry.ebangbang.network.service;


import com.harry.ebangbang.network.entity.ErrandServiceEntity;
import com.harry.ebangbang.network.entity.ErrandServicePriceEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/8/20.
 */
public interface ErrandServiceService {

    @FormUrlEncoded
    @POST
    Observable<ErrandServiceEntity> submit(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<ErrandServicePriceEntity> price(@Url String url, @FieldMap Map<String, String> params);
}

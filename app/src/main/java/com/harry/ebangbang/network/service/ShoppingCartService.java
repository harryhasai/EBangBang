package com.harry.ebangbang.network.service;


import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.network.entity.ShoppingCartEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/8/20.
 */
public interface ShoppingCartService {

    @FormUrlEncoded
    @POST
    Observable<ShoppingCartEntity> getShoppingList(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> delete(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> add(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> reduce(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> check(@Url String url, @FieldMap Map<String, String> params);

}

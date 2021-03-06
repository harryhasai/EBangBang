package com.harry.ebangbang.network.service;


import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.network.entity.CustomerServiceEntity;
import com.harry.ebangbang.network.entity.MineEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/8/20.
 */
public interface MineService {

    @FormUrlEncoded
    @POST
    Observable<MineEntity> getUserInfo(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonEntity> upload(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CustomerServiceEntity> getCustomerService(@Url String url, @FieldMap Map<String, String> params);
}

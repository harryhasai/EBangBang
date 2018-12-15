package com.harry.ebangbang.network.service;


import com.harry.ebangbang.network.entity.ErrandListEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/8/20.
 */
public interface ErrandListService {

    @FormUrlEncoded
    @POST
    Observable<ErrandListEntity> getErrandList(@Url String url, @FieldMap Map<String, String> params);

}

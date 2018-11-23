package com.harry.ebangbang.network.service;


import com.harry.ebangbang.network.entity.HomeBannerEntity;
import com.harry.ebangbang.network.entity.HomeEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/8/20.
 */
public interface HomeService {

    @FormUrlEncoded
    @POST
    Observable<HomeEntity> getList(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<HomeBannerEntity> getBanner(@Url String url, @FieldMap Map<String, String> params);
}

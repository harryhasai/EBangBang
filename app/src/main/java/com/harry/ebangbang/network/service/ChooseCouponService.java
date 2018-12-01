package com.harry.ebangbang.network.service;

import com.harry.ebangbang.network.entity.ChooseCouponEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/11/30.
 */
public interface ChooseCouponService {

    @FormUrlEncoded
    @POST
    Observable<ChooseCouponEntity> getCouponList(@Url String url, @FieldMap Map<String, String> params);
}

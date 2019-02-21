package com.harry.ebangbang.network.service;

import com.harry.ebangbang.network.entity.SelectContentAddressChildEntity;
import com.harry.ebangbang.network.entity.SelectContentAddressEntity;
import com.harry.ebangbang.network.entity.SelectContentCategoryChildEntity;
import com.harry.ebangbang.network.entity.SelectContentCategoryEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Harry on 2018/11/19.
 */
public interface SelectContentService {

    @FormUrlEncoded
    @POST
    Observable<SelectContentCategoryEntity> getCategory(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<SelectContentCategoryChildEntity> getChildCategory(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<SelectContentAddressEntity> getAddress(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<SelectContentAddressChildEntity> getChildAddress(@Url String url, @FieldMap Map<String, String> params);


}

package com.harry.ebangbang.function.home;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.HomeBannerEntity;
import com.harry.ebangbang.network.entity.HomeEntity;
import com.harry.ebangbang.network.service.HomeService;
import com.harry.ebangbang.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/11/20.
 */
public class HomeModel extends BaseModel {

    private final HomeService service;

    public HomeModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(HomeService.class);
    }

    public void getList(Observer<HomeEntity> observer) {
        Map<String, String> params = new HashMap<>();
        service.getList(URLFinal.HOME_GET_LIST, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getBanner(Observer<HomeBannerEntity> observer) {
        Map<String, String> params = new HashMap<>();
        service.getBanner(URLFinal.HOME_BANNER, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

package com.harry.ebangbang.function.my_coupon;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.MyCouponEntity;
import com.harry.ebangbang.network.service.MyCouponService;
import com.harry.ebangbang.utils.RetrofitHelper;
import com.harry.ebangbang.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/12/15.
 */
public class MyCouponModel extends BaseModel {

    private final MyCouponService service;

    public MyCouponModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(MyCouponService.class);
    }

    public void getCouponList(Observer<MyCouponEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));

        service.getCouponList(URLFinal.MY_COUPON, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

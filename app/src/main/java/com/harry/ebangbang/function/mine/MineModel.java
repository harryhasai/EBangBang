package com.harry.ebangbang.function.mine;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.network.entity.CustomerServiceEntity;
import com.harry.ebangbang.network.entity.MineEntity;
import com.harry.ebangbang.network.service.MineService;
import com.harry.ebangbang.utils.RetrofitHelper;
import com.harry.ebangbang.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/11/23.
 */
public class MineModel extends BaseModel {

    private final MineService service;

    public MineModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(MineService.class);
    }

    public void getUserInfo(Observer<MineEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));

        service.getUserInfo(URLFinal.GET_USER_INFO, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void upload(String base64, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        params.put("headAddress", base64);

        service.upload(URLFinal.UPLOAD_HEADER, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getCustomerService(Observer<CustomerServiceEntity> observer) {
        Map<String, String> params = new HashMap<>();
//        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));

        service.getCustomerService(URLFinal.CUSTOMER_SERVICE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

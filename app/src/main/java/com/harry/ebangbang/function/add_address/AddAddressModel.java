package com.harry.ebangbang.function.add_address;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.network.service.AddAddressService;
import com.harry.ebangbang.utils.RetrofitHelper;
import com.harry.ebangbang.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/11/29.
 */
public class AddAddressModel extends BaseModel {

    private final AddAddressService service;

    public AddAddressModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(AddAddressService.class);
    }

    public void putAddress(String name, String detailAddress, String longitude,
                           String latitude, String isDefault, String phone,
                           Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        params.put("name", name);
        params.put("site", detailAddress);//详细地址
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        params.put("isDefault", isDefault);
        params.put("phone", phone);

        service.putAddress(URLFinal.ADD_ADDRESS, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

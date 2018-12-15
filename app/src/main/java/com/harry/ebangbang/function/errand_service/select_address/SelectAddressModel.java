package com.harry.ebangbang.function.errand_service.select_address;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.AddressManagementEntity;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.network.service.AddressManagementService;
import com.harry.ebangbang.utils.RetrofitHelper;
import com.harry.ebangbang.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/12/14.
 */
public class SelectAddressModel extends BaseModel {

    private final AddressManagementService service;

    public SelectAddressModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(AddressManagementService.class);
    }

    public void getAddressList(Observer<AddressManagementEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));

        service.getAddressList(URLFinal.GET_ADDRESS_LIST, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}

package com.harry.ebangbang.function.register;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.network.service.RegisterService;
import com.harry.ebangbang.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/11/19.
 */
public class RegisterModel extends BaseModel {

    private final RegisterService service;

    public RegisterModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(RegisterService.class);
    }

    public void register(String phone, String password, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("phone", phone);
        params.put("passWord", password);

        service.register(URLFinal.REGISTER, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getVerifyCode(String phone, String UUID, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("phone", phone);
        params.put("UUID", UUID);

        service.getVerifyCode(URLFinal.GET_VERIFY_CODE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void checkVerifyCode(String phone, String verifyCode, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("phone", phone);
        params.put("securityCode", verifyCode);

        service.checkVerifyCode(URLFinal.CHECK_VERIFY_CODE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

package com.harry.ebangbang.function.login.binding_phone;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.BindingPhoneEntity;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.network.service.BindingPhoneService;
import com.harry.ebangbang.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/12/14.
 */
public class BindingPhoneModel extends BaseModel {

    private final BindingPhoneService service;

    public BindingPhoneModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(BindingPhoneService.class);
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

    public void bindingNewPhone(String phone, String password, String openId,
                                 String nickName, String headAddress, Observer<BindingPhoneEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("phone", phone);
        params.put("passWord", password);
        params.put("openid", openId);
        params.put("nickname", nickName);
        params.put("headAddress", headAddress);

        service.bindingNewPhone(URLFinal.BINDING_NEW_PHONE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void bindingOldPhone(String phone, String openId,
                                String nickName, String headAddress, Observer<BindingPhoneEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("phone", phone);
        params.put("openid", openId);
        params.put("nickname", nickName);
        params.put("headAddress", headAddress);

        service.bindingOldPhone(URLFinal.BINDING_OLD_PHONE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

package com.harry.ebangbang.function.login;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.LoginEntity;
import com.harry.ebangbang.network.service.LoginService;
import com.harry.ebangbang.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/11/6.
 */
public class LoginModel extends BaseModel {

    private final LoginService service;

    public LoginModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(LoginService.class);
    }

    public void login(String userName, String password, Observer<LoginEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("loginName", userName);
        params.put("passWord", password);

        service.login(URLFinal.LOGIN, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

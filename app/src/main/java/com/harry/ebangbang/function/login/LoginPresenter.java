package com.harry.ebangbang.function.login;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.function.main.MainActivity;
import com.harry.ebangbang.network.entity.LoginEntity;
import com.harry.ebangbang.network.entity.WxLoginEntity;
import com.harry.ebangbang.rx.DisposableManager;
import com.harry.ebangbang.utils.SPUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/6.
 */
public class LoginPresenter extends BasePresenter<LoginActivity> {

    private final LoginModel model;

    public LoginPresenter() {
        model = new LoginModel();
    }

    public void wxLogin(String code) {
        model.wxLogin(code, new Observer<WxLoginEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.LOGIN_ACTIVITY_WX_LOGIN, d);
            }

            @Override
            public void onNext(WxLoginEntity wxLoginEntity) {
                if (wxLoginEntity.code == 1) {
                    view.wxLoginResult(wxLoginEntity);
                } else {
                    ToastUtils.showShort(wxLoginEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void login(String userName, String password) {
        model.login(userName, password, new Observer<LoginEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.LOGIN_ACTIVITY_LOGIN, d);
            }

            @Override
            public void onNext(LoginEntity loginEntity) {
                if (loginEntity.code == 1) {
                    cacheUserInfo(loginEntity);
                    view.startActivity(new Intent(view, MainActivity.class));
                    SPUtils.putBoolean(UserInfo.IS_LOGIN.name(), true);
                    view.finish();
                } else {
                    ToastUtils.showShort(loginEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 缓存用户数据
     *
     * @param loginEntity 用户数据
     */
    private void cacheUserInfo(LoginEntity loginEntity) {
        SPUtils.putString(UserInfo.ID.name(), String.valueOf(loginEntity.data.id));
        SPUtils.putString(UserInfo.LOGIN_NAME.name(), loginEntity.data.loginName);
        SPUtils.putString(UserInfo.PHONE.name(), loginEntity.data.phone);
        SPUtils.putString(UserInfo.TYPE.name(), loginEntity.data.type);
        SPUtils.putString(UserInfo.TOKEN.name(), loginEntity.token);
        SPUtils.putString(UserInfo.REMARK.name(), loginEntity.data.remark);
        SPUtils.putString(UserInfo.HEADER_BASE.name(), loginEntity.headPortraitLink);
        SPUtils.putString(UserInfo.HEADER_URL.name(), loginEntity.data.headAddress);
    }
}

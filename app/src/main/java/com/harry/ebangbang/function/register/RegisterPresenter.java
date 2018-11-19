package com.harry.ebangbang.function.register;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/19.
 */
public class RegisterPresenter extends BasePresenter<RegisterActivity> {

    private final RegisterModel model;

    public RegisterPresenter() {
        model = new RegisterModel();
    }

    public void register(String phone, String password) {
        model.register(phone, password, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.REGISTER_ACTIVITY_REGISTER, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    ToastUtils.showShort("注册成功");
                    view.finish();
                } else {
                    ToastUtils.showShort(commonEntity.msg);
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

    public void getVerifyCode(String phone, String UUID) {
        model.getVerifyCode(phone, UUID, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.REGISTER_ACTIVITY_GET_VERIFY_CODE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    ToastUtils.showShort("正在获取, 请稍后");
                } else {
                    view.finishCountDown();
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
                view.finishCountDown();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void checkVerifyCode(final String phone, final String password, String verifyCode) {
        model.checkVerifyCode(phone, verifyCode, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.REGISTER_ACTIVITY_CHECK_VERIFY_CODE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    register(phone, password);
                } else {
                    ToastUtils.showShort(commonEntity.msg);
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
}

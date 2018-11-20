package com.harry.ebangbang.function.forget_password;

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
public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordActivity> {

    private final ForgetPasswordModel model;

    public ForgetPasswordPresenter() {
        model = new ForgetPasswordModel();
    }

    public void forgetPassword(String phone, String password, String confirmPassword) {
        model.forgetPassword(phone, password, confirmPassword, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.FORGET_PASSWORD_ACTIVITY_FORGET_PASSWORD, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    ToastUtils.showShort("设置成功");
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
                DisposableManager.get().add(DisposableFinal.FORGET_PASSWORD_ACTIVITY_GET_VERIFY_CODE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    ToastUtils.showShort("正在获取, 请稍后");
                    view.countDown();
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
                DisposableManager.get().add(DisposableFinal.FORGET_PASSWORD_ACTIVITY_CHECK_VERIFY_CODE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    forgetPassword(phone, password, password);
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

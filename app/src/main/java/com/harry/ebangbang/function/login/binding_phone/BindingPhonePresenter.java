package com.harry.ebangbang.function.login.binding_phone;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.BindingPhoneEntity;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/14.
 */
public class BindingPhonePresenter extends BasePresenter<BindingPhoneActivity> {

    private final BindingPhoneModel model;

    public BindingPhonePresenter() {
        model = new BindingPhoneModel();
    }

    public void getVerifyCode(String phone, String UUID) {
        model.getVerifyCode(phone, UUID, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.BINDING_PHONE_ACTIVITY_GET_VERIFY_CODE, d);
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

    public void checkVerifyCode(String phone, String verifyCode) {
        model.checkVerifyCode(phone, verifyCode, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.BINDING_PHONE_ACTIVITY_CHECK_VERIFY_CODE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    view.commit();
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

    public void bindingNewPhone(String phone, String password, String openId, String nickName, String headAddress) {
        model.bindingNewPhone(phone, password, openId, nickName, headAddress, new Observer<BindingPhoneEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.BINDING_PHONE_ACTIVITY_BINDING_NEW_PHONE, d);
            }

            @Override
            public void onNext(BindingPhoneEntity bindingPhoneEntity) {
                if (bindingPhoneEntity.code == 1) {
                    view.bindingSuccess(bindingPhoneEntity.data, bindingPhoneEntity.headPortraitLink);
                } else {
                    ToastUtils.showShort(bindingPhoneEntity.msg);
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

    public void bindingOldPhone(String phone, String openId, String nickName, String headAddress) {
        model.bindingOldPhone(phone, openId, nickName, headAddress, new Observer<BindingPhoneEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.BINDING_PHONE_ACTIVITY_BINDING_OLD_PHONE, d);
            }

            @Override
            public void onNext(BindingPhoneEntity bindingPhoneEntity) {
                if (bindingPhoneEntity.code == 1) {
                    view.bindingSuccess(bindingPhoneEntity.data, bindingPhoneEntity.headPortraitLink);
                } else {
                    ToastUtils.showShort(bindingPhoneEntity.msg);
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

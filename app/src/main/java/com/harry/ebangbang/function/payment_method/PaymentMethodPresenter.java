package com.harry.ebangbang.function.payment_method;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.AliPayEntity;
import com.harry.ebangbang.network.entity.WxPayEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/1.
 */
public class PaymentMethodPresenter extends BasePresenter<PaymentMethodActivity> {

    private final PaymentMethodModel model;

    public PaymentMethodPresenter() {
        model = new PaymentMethodModel();
    }

    public void wxPay(String orderFormId, boolean isErrand) {
        model.wxPay(orderFormId, isErrand, new Observer<WxPayEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.PAYMENT_METHOD_ACTIVITY_WX_PAY, d);
            }

            @Override
            public void onNext(WxPayEntity wxPayEntity) {
                if (wxPayEntity.code.equals("100")) {
                    view.wxPay(wxPayEntity.msg);
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

    public void aliPay(String orderFormId, boolean isErrand) {
        model.aliPay(orderFormId, isErrand, new Observer<AliPayEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.PAYMENT_METHOD_ACTIVITY_ALI_PAY, d);
            }

            @Override
            public void onNext(AliPayEntity aliPayEntity) {
                if (aliPayEntity.code == 1) {
                    view.aliPay(aliPayEntity.result);
                } else {
                    ToastUtils.showShort(aliPayEntity.msg);
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

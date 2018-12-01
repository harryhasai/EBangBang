package com.harry.ebangbang.function.choose_coupon;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.ChooseCouponEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/30.
 */
public class ChooseCouponPresenter extends BasePresenter<ChooseCouponActivity> {

    private final ChooseCouponModel model;

    public ChooseCouponPresenter() {
        model = new ChooseCouponModel();
    }

    public void getCouponList(String total) {
        model.getCouponList(total, new Observer<ChooseCouponEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.CHOOSE_COUPON_ACTIVITY_GET_COUPON_LIST, d);
            }

            @Override
            public void onNext(ChooseCouponEntity chooseCouponEntity) {
                if (chooseCouponEntity.code == 1) {
                    if (chooseCouponEntity.data.size() == 0) {
                        ToastUtils.showShort("暂无优惠券可用");
                    } else {
                        view.getCouponList(chooseCouponEntity.data);
                    }
                } else {
                    ToastUtils.showShort(chooseCouponEntity.msg);
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

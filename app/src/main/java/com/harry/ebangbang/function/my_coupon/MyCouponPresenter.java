package com.harry.ebangbang.function.my_coupon;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.MyCouponEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/15.
 */
public class MyCouponPresenter extends BasePresenter<MyCouponActivity> {

    private final MyCouponModel model;

    public MyCouponPresenter() {
        model = new MyCouponModel();
    }

    public void getCouponList() {
        model.getCouponList(new Observer<MyCouponEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MY_COUPON_ACTIVITY_GET_COUPON_LIST, d);
            }

            @Override
            public void onNext(MyCouponEntity myCouponEntity) {
                if (myCouponEntity.code == 1) {
                    view.getCouponList(myCouponEntity.data);
                } else {
                    ToastUtils.showShort(myCouponEntity.msg);
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

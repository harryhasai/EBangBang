package com.harry.ebangbang.function.errand_service;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.ErrandServiceEntity;
import com.harry.ebangbang.network.entity.ErrandServicePriceEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/15.
 */
public class ErrandServicePresenter extends BasePresenter<ErrandServiceActivity> {

    private final ErrandServiceModel model;

    public ErrandServicePresenter() {
        model = new ErrandServiceModel();
    }

    public void submit(String addresseeId, String collectId, String weight, String collectTime,
                       String remark, String practicalMoney) {
        model.submit(addresseeId, collectId, weight, collectTime, remark, practicalMoney, new Observer<ErrandServiceEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ERRAND_SERVICE_ACTIVITY_SUBMIT, d);
            }

            @Override
            public void onNext(ErrandServiceEntity errandServiceEntity) {
                if (errandServiceEntity.code == 1) {
                    view.clearText(errandServiceEntity.data);
                } else {
                    ToastUtils.showShort(errandServiceEntity.msg);
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

    public void price(String addresseeId, String collectId, String weight) {
        model.price(addresseeId, collectId, weight, new Observer<ErrandServicePriceEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ERRAND_SERVICE_ACTIVITY_PRICE, d);
            }

            @Override
            public void onNext(ErrandServicePriceEntity errandServicePriceEntity) {
                if (errandServicePriceEntity.code == 1) {
                    view.price(errandServicePriceEntity);
                } else {
                    ToastUtils.showShort(errandServicePriceEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误, 请重新选择重量来计算配送费");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}

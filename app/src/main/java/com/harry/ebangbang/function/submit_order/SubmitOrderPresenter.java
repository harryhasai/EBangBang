package com.harry.ebangbang.function.submit_order;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.SubmitOrderEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/28.
 */
public class SubmitOrderPresenter extends BasePresenter<SubmitOrderActivity> {

    private final SubmitOrderModel model;

    public SubmitOrderPresenter() {
        model = new SubmitOrderModel();
    }

    public void getPageDetail(String shopId, String ids) {
        model.getPageDetail(shopId, ids, new Observer<SubmitOrderEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SUBMIT_ORDER_ACTIVITY_GET_PAGE_DETAIL, d);
            }

            @Override
            public void onNext(SubmitOrderEntity submitOrderEntity) {
                if (submitOrderEntity.code == 1) {
                    view.initParam(submitOrderEntity.data);
                } else {
                    ToastUtils.showShort(submitOrderEntity.msg);
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

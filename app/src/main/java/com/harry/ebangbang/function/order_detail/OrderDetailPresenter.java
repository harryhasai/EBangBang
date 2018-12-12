package com.harry.ebangbang.function.order_detail;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.OrderDetailEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/12.
 */
public class OrderDetailPresenter extends BasePresenter<OrderDetailActivity> {

    private final OrderDetailModel model;

    public OrderDetailPresenter() {
        model = new OrderDetailModel();
    }

    public void getOrderDetail(String orderFormId) {
        model.getOrderDetail(orderFormId, new Observer<OrderDetailEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ORDER_DETAIL_ACTIVITY_GET_ORDER_DETAIL, d);
            }

            @Override
            public void onNext(OrderDetailEntity orderDetailEntity) {
                if (orderDetailEntity.code == 1) {
                    view.getOrderDetail(orderDetailEntity.data);
                } else {
                    ToastUtils.showShort(orderDetailEntity.msg);
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

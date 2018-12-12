package com.harry.ebangbang.function.order_manage;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.OrderManageEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/11.
 */
public class OrderManagePresenter extends BasePresenter<OrderManageActivity> {

    private final OrderManageModel model;

    public OrderManagePresenter() {
        model = new OrderManageModel();
    }

    /**
     * @param orderFormStatus 订单状态  -1未付款 1 待配送  4待收货  5 订单已完成未评价   6退返货   7完成并已评价
     * @param pageNum 分页
     */
    public void getOrderList(String orderFormStatus, int pageNum) {
        model.getOrderList(orderFormStatus, pageNum, new Observer<OrderManageEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ORDER_MANAGE_ACTIVITY_GET_ORDER_LIST, d);
            }

            @Override
            public void onNext(OrderManageEntity orderManageEntity) {
                if (orderManageEntity.code == 1) {
                    view.getOrderList(orderManageEntity.data);
                } else if (orderManageEntity.code == 3) {
                    view.loadMoreEnd();
                } else {
                    ToastUtils.showShort(orderManageEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
                view.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                view.setRefreshing(false);
            }
        });
    }
}

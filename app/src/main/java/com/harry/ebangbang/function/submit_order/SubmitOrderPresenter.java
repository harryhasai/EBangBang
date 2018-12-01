package com.harry.ebangbang.function.submit_order;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.GeneratePrepaidOrdersEntity;
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

    /**
     * @param shopId 商铺id
     * @param addresseeId 收件人id
     * @param money 总金额
     * @param mealBoxMoney 餐盒费
     * @param dispatchingMoney 配送费
     * @param meetSubtract 满减减去金额
     * @param goodsIds 商品ids id 商品id num 商品数量 例如 [ { "id": 1, "num": 2 }, { "id": 2, "num": 3 } ]
     * @param remark 备注
     */
    public void submitOrder(String shopId, String addresseeId, String money, String mealBoxMoney,
                            String dispatchingMoney, String meetSubtract, String goodsIds, String remark) {
        model.submitOrder(shopId, addresseeId, money, mealBoxMoney, dispatchingMoney, meetSubtract, goodsIds, remark, new Observer<GeneratePrepaidOrdersEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SUBMIT_ORDER_ACTIVITY_SUBMIT_ORDER, d);
            }

            @Override
            public void onNext(GeneratePrepaidOrdersEntity generatePrepaidOrdersEntity) {
                if (generatePrepaidOrdersEntity.code == 1) {
                    //生成订单成功 跳转到选择支付的页面
                    view.goToPay(generatePrepaidOrdersEntity.data);
                } else {
                    ToastUtils.showShort(generatePrepaidOrdersEntity.msg);
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

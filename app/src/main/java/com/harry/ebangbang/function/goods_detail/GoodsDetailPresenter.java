package com.harry.ebangbang.function.goods_detail;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.GoodsDetailEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/10.
 */
public class GoodsDetailPresenter extends BasePresenter<GoodsDetailActivity> {

    private final GoodsDetailModel model;

    public GoodsDetailPresenter() {
        model = new GoodsDetailModel();
    }

    public void getGoodsDetail(String goodsId) {
        model.getGoodsDetail(goodsId, new Observer<GoodsDetailEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.GOODS_DETAIL_ACTIVITY_GET_GOODS_DETAIL, d);
            }

            @Override
            public void onNext(GoodsDetailEntity goodsDetailEntity) {
                if (goodsDetailEntity.code == 1) {
                    view.getGoodsDetail(goodsDetailEntity.data);
                } else {
                    ToastUtils.showShort(goodsDetailEntity.msg);
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

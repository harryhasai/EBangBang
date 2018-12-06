package com.harry.ebangbang.function.shop_detail;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.ShopDetailCategoryEntity;
import com.harry.ebangbang.network.entity.ShopDetailChildEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/4.
 */
public class ShopDetailPresenter extends BasePresenter<ShopDetailActivity> {

    private final ShopDetailModel model;

    public ShopDetailPresenter() {
        model = new ShopDetailModel();
    }

    public void getCategory(String shopId) {
        model.getCategory(shopId, new Observer<ShopDetailCategoryEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SHOP_DETAIL_ACTIVITY_GET_CATEGORY, d);
            }

            @Override
            public void onNext(ShopDetailCategoryEntity shopDetailCategoryEntity) {
                if (shopDetailCategoryEntity.code == 1) {
                    view.getCategory(shopDetailCategoryEntity.data);
                } else {
                    ToastUtils.showShort(shopDetailCategoryEntity.msg);
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

    public void getChild(String shopId) {
        model.getChild(shopId, new Observer<ShopDetailChildEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SHOP_DETAIL_ACTIVITY_GET_CHILD, d);
            }

            @Override
            public void onNext(ShopDetailChildEntity shopDetailChildEntity) {
                if (shopDetailChildEntity.code == 1) {
                    view.getChild(shopDetailChildEntity.data);
                } else {
                    ToastUtils.showShort(shopDetailChildEntity.msg);
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

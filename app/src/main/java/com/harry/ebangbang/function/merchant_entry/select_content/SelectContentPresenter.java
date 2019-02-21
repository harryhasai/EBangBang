package com.harry.ebangbang.function.merchant_entry.select_content;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.SelectContentAddressChildEntity;
import com.harry.ebangbang.network.entity.SelectContentAddressEntity;
import com.harry.ebangbang.network.entity.SelectContentCategoryChildEntity;
import com.harry.ebangbang.network.entity.SelectContentCategoryEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/25.
 */
public class SelectContentPresenter extends BasePresenter<SelectContentActivity> {

    private final SelectContentModel model;

    public SelectContentPresenter() {
        model = new SelectContentModel();
    }

    public void getCategory() {
        model.getCategory(new Observer<SelectContentCategoryEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SELECT_CONTENT_ACTIVITY, d);
            }

            @Override
            public void onNext(SelectContentCategoryEntity selectContentCategoryEntity) {
                if (selectContentCategoryEntity.code == 1) {
                    view.getCategory(selectContentCategoryEntity.data);
                } else {
                    ToastUtils.showShort(selectContentCategoryEntity.msg);
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

    public void getChildCategory(String id) {
        model.getChildCategory(id, new Observer<SelectContentCategoryChildEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SELECT_CONTENT_ACTIVITY, d);
            }

            @Override
            public void onNext(SelectContentCategoryChildEntity selectContentCategoryChildEntity) {
                if (selectContentCategoryChildEntity.code == 1) {
                    view.getChildCategory(selectContentCategoryChildEntity.data);
                } else {
                    ToastUtils.showShort(selectContentCategoryChildEntity.msg);
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

    public void getAddress() {
        model.getAddress(new Observer<SelectContentAddressEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SELECT_CONTENT_ACTIVITY, d);
            }

            @Override
            public void onNext(SelectContentAddressEntity selectContentAddressEntity) {
                if (selectContentAddressEntity.code == 1) {
                    view.getAddress(selectContentAddressEntity.data);
                } else {
                    ToastUtils.showShort(selectContentAddressEntity.msg);
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

    public void getChildAddress(String id) {
        model.getChildAddress(id, new Observer<SelectContentAddressChildEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SELECT_CONTENT_ACTIVITY, d);
            }

            @Override
            public void onNext(SelectContentAddressChildEntity selectContentAddressChildEntity) {
                if (selectContentAddressChildEntity.code == 1) {
                    view.getChildAddress(selectContentAddressChildEntity.data);
                } else {
                    ToastUtils.showShort(selectContentAddressChildEntity.msg);
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

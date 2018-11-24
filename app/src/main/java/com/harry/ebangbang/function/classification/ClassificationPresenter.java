package com.harry.ebangbang.function.classification;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.SecondCategoryEntity;
import com.harry.ebangbang.network.entity.TopCategoryEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/22.
 */
public class ClassificationPresenter extends BasePresenter<ClassificationFragment> {

    private final ClassificationModel model;

    public ClassificationPresenter() {
        model = new ClassificationModel();
    }

    public void getFirstLevel() {
        model.getFirstLevel(new Observer<TopCategoryEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.CLASSIFICATION_FRAGMENT_GET_FIRST_LEVEL, d);
            }

            @Override
            public void onNext(TopCategoryEntity topCategoryEntity) {
                if (topCategoryEntity.code == 1) {
                    view.getFirstLevel(topCategoryEntity.data);
                } else {
                    ToastUtils.showShort(topCategoryEntity.msg);
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

    public void getSecondLevel(String categoryID) {
        model.getSecondLevel(categoryID, new Observer<SecondCategoryEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.CLASSIFICATION_FRAGMENT_GET_SECOND_LEVEL, d);
            }

            @Override
            public void onNext(SecondCategoryEntity secondCategoryEntity) {
                if (secondCategoryEntity.code == 1) {
                    view.getSecondLevel(secondCategoryEntity.data);
                } else {
                    ToastUtils.showShort(secondCategoryEntity.msg);
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

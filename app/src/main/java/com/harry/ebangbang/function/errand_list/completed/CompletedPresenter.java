package com.harry.ebangbang.function.errand_list.completed;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.ErrandListEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/15.
 */
public class CompletedPresenter extends BasePresenter<CompletedFragment> {

    private final CompletedModel model;

    public CompletedPresenter() {
        model = new CompletedModel();
    }

    public void getErrandList() {
        model.getErrandList(new Observer<ErrandListEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.GOING_ON_FRAGMENT_GET_GOING_ON_LIST, d);
            }

            @Override
            public void onNext(ErrandListEntity errandListEntity) {
                if (errandListEntity.code == 1) {
                    view.getErrandList(errandListEntity.data);
                } else {
                    ToastUtils.showShort(errandListEntity.msg);
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

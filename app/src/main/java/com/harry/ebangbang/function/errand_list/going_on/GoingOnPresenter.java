package com.harry.ebangbang.function.errand_list.going_on;

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
public class GoingOnPresenter extends BasePresenter<GoingOnFragment> {

    private final GoingOnModel model;

    public GoingOnPresenter() {
        model = new GoingOnModel();
    }

    public void getGoingOnList() {
        model.getGoingOnList(new Observer<ErrandListEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.GOING_ON_FRAGMENT_GET_GOING_ON_LIST, d);
            }

            @Override
            public void onNext(ErrandListEntity errandListEntity) {
                if (errandListEntity.code == 1) {
                    view.getGoingOnList(errandListEntity.data);
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

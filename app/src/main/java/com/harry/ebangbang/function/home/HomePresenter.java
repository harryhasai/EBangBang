package com.harry.ebangbang.function.home;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.HomeBannerEntity;
import com.harry.ebangbang.network.entity.HomeEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/20.
 */
public class HomePresenter extends BasePresenter<HomeFragment> {

    private final HomeModel model;

    public HomePresenter() {
        model = new HomeModel();
    }

    public void getList() {
        model.getList(new Observer<HomeEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.HOME_FRAGMENT_GET_LIST, d);
            }

            @Override
            public void onNext(HomeEntity homeEntity) {
                if (homeEntity.code == 1) {
                    view.getList(homeEntity.data);
                } else {
                    ToastUtils.showShort(homeEntity.msg);
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

    public void getBanner() {
        model.getBanner(new Observer<HomeBannerEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.HOME_FRAGMENT_GET_BANNER, d);
            }

            @Override
            public void onNext(HomeBannerEntity homeBannerEntity) {
                if (homeBannerEntity.code == 1) {
                    view.getBanner(homeBannerEntity);
                } else {
                    ToastUtils.showShort(homeBannerEntity.msg);
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

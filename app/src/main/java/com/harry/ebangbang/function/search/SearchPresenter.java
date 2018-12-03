package com.harry.ebangbang.function.search;

import android.content.Intent;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.function.main.MainActivity;
import com.harry.ebangbang.network.entity.SearchEntity;
import com.harry.ebangbang.rx.DisposableManager;
import com.harry.ebangbang.utils.SPUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/3.
 */
public class SearchPresenter extends BasePresenter<SearchActivity> {

    private final SearchModel model;

    public SearchPresenter() {
        model = new SearchModel();
    }

    public void search(String searchText) {
        model.search(searchText, new Observer<SearchEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.SEARCH_ACTIVITY_SEARCH, d);
            }

            @Override
            public void onNext(SearchEntity searchEntity) {
                if (searchEntity.code == 1) {
                    view.searchResult(searchEntity.data);
                } else {
                    ToastUtils.showShort(searchEntity.msg);
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

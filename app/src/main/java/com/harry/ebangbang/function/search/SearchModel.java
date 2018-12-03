package com.harry.ebangbang.function.search;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.SearchEntity;
import com.harry.ebangbang.network.service.SearchService;
import com.harry.ebangbang.utils.RetrofitHelper;
import com.harry.ebangbang.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/12/3.
 */
public class SearchModel extends BaseModel {

    private final SearchService service;

    public SearchModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(SearchService.class);
    }

    public void search(String searchText, Observer<SearchEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        params.put("name", searchText);

        service.search(URLFinal.SEARCH, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

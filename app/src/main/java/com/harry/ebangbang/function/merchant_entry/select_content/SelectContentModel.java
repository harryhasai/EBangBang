package com.harry.ebangbang.function.merchant_entry.select_content;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.SelectContentAddressChildEntity;
import com.harry.ebangbang.network.entity.SelectContentAddressEntity;
import com.harry.ebangbang.network.entity.SelectContentCategoryChildEntity;
import com.harry.ebangbang.network.entity.SelectContentCategoryEntity;
import com.harry.ebangbang.network.service.SelectContentService;
import com.harry.ebangbang.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/12/25.
 */
public class SelectContentModel extends BaseModel {

    private final SelectContentService service;

    public SelectContentModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(SelectContentService.class);
    }

    public void getCategory(Observer<SelectContentCategoryEntity> observer) {
        Map<String, String> params = new HashMap<>();

        service.getCategory(URLFinal.SELECT_CONTENT_CATEGORY_FIRST, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getChildCategory(String id, Observer<SelectContentCategoryChildEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);

        service.getChildCategory(URLFinal.SELECT_CONTENT_CATEGORY_SECOND, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getAddress(Observer<SelectContentAddressEntity> observer) {
        Map<String, String> params = new HashMap<>();

        service.getAddress(URLFinal.SELECT_CONTENT_ADDRESS_FIRST, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getChildAddress(String id, Observer<SelectContentAddressChildEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);

        service.getChildAddress(URLFinal.SELECT_CONTENT_ADDRESS_SECOND, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

package com.harry.ebangbang.function.classification;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.SecondCategoryEntity;
import com.harry.ebangbang.network.entity.TopCategoryEntity;
import com.harry.ebangbang.network.service.ClassificationService;
import com.harry.ebangbang.utils.RetrofitHelper;
import com.harry.ebangbang.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/11/22.
 */
public class ClassificationModel extends BaseModel {

    private final ClassificationService service;

    public ClassificationModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(ClassificationService.class);
    }

    public void getFirstLevel(Observer<TopCategoryEntity> observer) {
        Map<String, String> params = new HashMap<>();

        service.getFirstLevel(URLFinal.GET_FIRST_LEVEL, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getSecondLevel(String categoryID, Observer<SecondCategoryEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        params.put("classify", categoryID);

        service.getSecondLevel(URLFinal.GET_SECOND_LEVEL, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

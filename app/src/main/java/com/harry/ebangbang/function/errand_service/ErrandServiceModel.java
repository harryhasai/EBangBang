package com.harry.ebangbang.function.errand_service;

import android.text.TextUtils;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.ErrandServiceEntity;
import com.harry.ebangbang.network.entity.ErrandServicePriceEntity;
import com.harry.ebangbang.network.service.ErrandServiceService;
import com.harry.ebangbang.utils.RetrofitHelper;
import com.harry.ebangbang.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/12/15.
 */
public class ErrandServiceModel extends BaseModel {

    private final ErrandServiceService service;

    public ErrandServiceModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(ErrandServiceService.class);
    }

    public void submit(String addresseeId, String collectId, String weight, String collectTime,
                       String remark, String practicalMoney, Observer<ErrandServiceEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        params.put("addresseeId", addresseeId);
        params.put("collectId", collectId);
        params.put("weight", weight);
        params.put("collectTime", collectTime);
        if (!TextUtils.isEmpty(remark)) {
            params.put("remark", remark);
        }
        params.put("practicalMoney", practicalMoney);

        service.submit(URLFinal.ERRAND_SERVICE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void price(String addresseeId, String collectId, String weight, Observer<ErrandServicePriceEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("addresseeId", addresseeId);
        params.put("collectId", collectId);
        params.put("weight", weight);

        service.price(URLFinal.COUNT_MONEY, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

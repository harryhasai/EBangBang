package com.harry.ebangbang.function.errand_list.completed;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.ErrandListEntity;
import com.harry.ebangbang.network.service.ErrandListService;
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
public class CompletedModel extends BaseModel {

    private final ErrandListService service;

    public CompletedModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(ErrandListService.class);
    }

    public void getErrandList(Observer<ErrandListEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        //订单状态 0未付款 1 已付款 2骑手已接单 3骑手送货中 4完成订单 5退换货
        params.put("orderStatus", "4");//传1, 2和3的列表也出来

        service.getErrandList(URLFinal.ERRAND_MONEY, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

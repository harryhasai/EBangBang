package com.harry.ebangbang.function.goods_detail;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.GoodsDetailEntity;
import com.harry.ebangbang.network.service.GoodsDetailService;
import com.harry.ebangbang.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/12/10.
 */
public class GoodsDetailModel extends BaseModel {

    private final GoodsDetailService service;

    public GoodsDetailModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(GoodsDetailService.class);
    }

    public void getGoodsDetail(String goodsId, Observer<GoodsDetailEntity> observer) {
        Map<String, String> params = new HashMap<>();
//        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        params.put("id", goodsId);

        service.getGoodsDetail(URLFinal.GET_GOODS_DETAIL, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

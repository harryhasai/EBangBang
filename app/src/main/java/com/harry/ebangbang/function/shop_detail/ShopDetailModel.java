package com.harry.ebangbang.function.shop_detail;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.network.entity.ShopDetailCategoryEntity;
import com.harry.ebangbang.network.entity.ShopDetailChildEntity;
import com.harry.ebangbang.network.service.ShopDetailService;
import com.harry.ebangbang.utils.RetrofitHelper;
import com.harry.ebangbang.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/12/4.
 */
public class ShopDetailModel extends BaseModel {

    private final ShopDetailService service;

    public ShopDetailModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(ShopDetailService.class);
    }

    public void getCategory(String shopId, Observer<ShopDetailCategoryEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("shopId", shopId);

        service.getCategory(URLFinal.SHOP_DETAIL_CATEGORY, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getChild(String shopId, Observer<ShopDetailChildEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        params.put("shopId", shopId);

        service.getChild(URLFinal.SHOP_DETAIL_CHILD, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void addGoods(String shopId, String ids, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        params.put("shopId", shopId);
        params.put("ids", ids);// [ { "id": 1, "num": 2 }, { "id": 2, "num": 3 } ]

        service.addGoods(URLFinal.SHOP_DETAIL_ADD_GOODS, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

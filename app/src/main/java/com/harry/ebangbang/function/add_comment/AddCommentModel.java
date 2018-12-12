package com.harry.ebangbang.function.add_comment;

import android.text.TextUtils;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.AddCommentEntity;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.network.service.AddCommentService;
import com.harry.ebangbang.utils.RetrofitHelper;
import com.harry.ebangbang.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/12/12.
 */
public class AddCommentModel extends BaseModel {

    private final AddCommentService service;

    public AddCommentModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(AddCommentService.class);
    }

    public void getCommentInfo(String orderFormId, Observer<AddCommentEntity> observer) {
        Map<String, String> params = new HashMap<>();
//        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        params.put("orderformId", orderFormId);

        service.getCommentInfo(URLFinal.GET_COMMENT_INFO, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * @param score     评分 1-5
     * @param rideScore 骑手 0满意 1不满意
     * @param shopId    商家id
     * @param orderId   订单id
     * @param rideId    骑手id
     * @param content   评价内容
     * @param imgLink   评价图片
     */
    public void addComment(String score, String rideScore, String shopId, String orderId,
                           String rideId, String content, String imgLink, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", SPUtils.getString(UserInfo.ID.name(), ""));
        params.put("score", score);
        params.put("rideScore", rideScore);
        params.put("shopId", shopId);
        params.put("orderId", orderId);
        params.put("rideId", rideId);
        params.put("content", content);
        if (!TextUtils.isEmpty(imgLink)) {
            params.put("imgLink", imgLink);
        }

        service.addComment(URLFinal.ADD_COMMENT, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

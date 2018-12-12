package com.harry.ebangbang.function.add_comment;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.event.AddCommentEvent;
import com.harry.ebangbang.network.entity.AddCommentEntity;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.rx.DisposableManager;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/12.
 */
public class AddCommentPresenter extends BasePresenter<AddCommentActivity> {

    private final AddCommentModel model;

    public AddCommentPresenter() {
        model = new AddCommentModel();
    }

    public void getCommentInfo(String orderFormId) {
        model.getCommentInfo(orderFormId, new Observer<AddCommentEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ADD_COMMENT_ACTIVITY_GET_COMMENT_INFO, d);
            }

            @Override
            public void onNext(AddCommentEntity addCommentEntity) {
                if (addCommentEntity.code == 1) {
                    view.getCommentInfo(addCommentEntity.data);
                } else {
                    ToastUtils.showShort(addCommentEntity.msg);
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
                           String rideId, String content, String imgLink) {
        model.addComment(score, rideScore, shopId, orderId, rideId, content, imgLink, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ADD_COMMENT_ACTIVITY_ADD_COMMENT, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    EventBus.getDefault().postSticky(new AddCommentEvent());
                    view.finish();
                } else {
                    ToastUtils.showShort(commonEntity.msg);
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

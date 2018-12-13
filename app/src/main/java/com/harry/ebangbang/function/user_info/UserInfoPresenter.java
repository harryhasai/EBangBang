package com.harry.ebangbang.function.user_info;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/13.
 */
public class UserInfoPresenter extends BasePresenter<UserInfoActivity> {

    private final UserInfoModel model;

    public UserInfoPresenter() {
        model = new UserInfoModel();
    }

    public void save(String nickName, String birthday) {
        model.save(nickName, birthday, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.USER_INFO_ACTIVITY_SAVE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    ToastUtils.showShort("保存成功");
                    view.setResult(ConstantFinal.COMMON_RESULT_CODE);
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

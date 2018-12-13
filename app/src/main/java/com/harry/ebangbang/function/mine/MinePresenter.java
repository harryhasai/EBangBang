package com.harry.ebangbang.function.mine;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.network.entity.CustomerServiceEntity;
import com.harry.ebangbang.network.entity.MineEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/11/23.
 */
public class MinePresenter extends BasePresenter<MineFragment> {

    private final MineModel model;

    public MinePresenter() {
        model = new MineModel();
    }

    public void getUserInfo() {
        model.getUserInfo(new Observer<MineEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MINE_FRAGMENT_GET_USER_INFO, d);
            }

            @Override
            public void onNext(MineEntity mineEntity) {
                if (mineEntity.code == 1) {
                    view.getUserInfo(mineEntity.userDetails);
                } else {
                    ToastUtils.showShort(mineEntity.msg);
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

    public void upload(String base64) {
        model.upload(base64, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MINE_FRAGMENT_UPLOAD, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    ToastUtils.showShort("修改头像成功");
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

    public void getCustomerService() {
        model.getCustomerService(new Observer<CustomerServiceEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MINE_FRAGMENT_GET_CUSTOMER_SERVICE, d);
            }

            @Override
            public void onNext(CustomerServiceEntity customerServiceEntity) {
                if (customerServiceEntity.code == 1) {
                    view.getCustomerService(customerServiceEntity.data);
                } else {
                    ToastUtils.showShort(customerServiceEntity.msg);
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

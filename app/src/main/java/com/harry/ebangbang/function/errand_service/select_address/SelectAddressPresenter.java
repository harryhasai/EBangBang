package com.harry.ebangbang.function.errand_service.select_address;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.AddressManagementEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/14.
 */
public class SelectAddressPresenter extends BasePresenter<SelectAddressActivity> {

    private final SelectAddressModel model;

    public SelectAddressPresenter() {
        model = new SelectAddressModel();
    }

    public void getAddressList() {
        model.getAddressList(new Observer<AddressManagementEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.ADDRESS_MANAGEMENT_ACTIVITY_GET_ADDRESS_LIST, d);
            }

            @Override
            public void onNext(AddressManagementEntity addressManagementEntity) {
                if (addressManagementEntity.code == 1) {
                    view.getAddressList(addressManagementEntity.data);
                } else {
                    ToastUtils.showShort(addressManagementEntity.msg);
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

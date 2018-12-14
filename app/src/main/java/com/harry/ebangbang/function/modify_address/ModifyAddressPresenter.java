package com.harry.ebangbang.function.modify_address;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/14.
 */
public class ModifyAddressPresenter extends BasePresenter<ModifyAddressActivity> {

    private final ModifyAddressModel model;

    public ModifyAddressPresenter() {
        model = new ModifyAddressModel();
    }

    public void putAddress(String addressId, String name, String detailAddress, String longitude,
                           String latitude, String isDefault, String phone) {
        model.modifyAddress(addressId, name, detailAddress, longitude, latitude, isDefault, phone, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MODIFY_ADDRESS_ACTIVITY_MODIFY_ADDRESS, d);
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

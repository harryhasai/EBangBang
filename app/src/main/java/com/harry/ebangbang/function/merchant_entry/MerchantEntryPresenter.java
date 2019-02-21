package com.harry.ebangbang.function.merchant_entry;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.rx.DisposableManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/12/25.
 */
public class MerchantEntryPresenter extends BasePresenter<MerchantEntryActivity> {

    private final MerchantEntryModel model;

    public MerchantEntryPresenter() {
        model = new MerchantEntryModel();
    }

    /**
     * @param name                 商家名称
     * @param contacts             商家联系人
     * @param contactsPhone        商家联系电话
     * @param category1            三级联动 商品分类1id（请求接口获取id）
     * @param category2            三级联动 商品分类2id（请求接口获取id）
     * @param category3            三级联动 商品分类3id（请求接口获取id）
     * @param siteProvince         三级联动省 id（请求接口获取id）
     * @param siteCity             三级联动市id（请求接口获取id）
     * @param siteDistrict         三级联动区id（请求接口获取id）
     * @param siteDetail           商家详细地址
     * @param longitude            商家经度
     * @param latitude             商家纬度
     * @param distributionWay      配送方式 1 平台配送 2商家配送
     * @param appearanceLink       门头图片
     * @param interiorLink         店内图片
     * @param logo                 商家Logo图片
     * @param businessLicenseLink  营业执照图片
     * @param legalPapersFrontLink 法人手持身份证
     * @param licenceLink          许可证照片地址
     */
    public void register(String name, String contacts, String contactsPhone,
                         String category1, String category2, String category3,
                         String siteProvince, String siteCity, String siteDistrict,
                         String siteDetail, String longitude, String latitude,
                         String distributionWay, String appearanceLink, String interiorLink,
                         String logo, String businessLicenseLink, String legalPapersFrontLink,
                         String licenceLink) {
        model.register(name, contacts, contactsPhone,
                category1, category2, category3,
                siteProvince, siteCity, siteDistrict,
                siteDetail, longitude, latitude,
                distributionWay, appearanceLink, interiorLink,
                logo, businessLicenseLink, legalPapersFrontLink, licenceLink, new Observer<CommonEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        DisposableManager.get().add(DisposableFinal.MERCHANT_ENTRY_ACTIVITY_REGISTER, d);
                    }

                    @Override
                    public void onNext(CommonEntity commonEntity) {
                        if (commonEntity.code == 1) {
                            ToastUtils.showShort("注册商家成功");
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

    public void getVerifyCode(String phone, String UUID) {
        model.getVerifyCode(phone, UUID, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MERCHANT_ENTRY_ACTIVITY_GET_VERIFY_CODE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    ToastUtils.showShort("正在获取, 请稍后");
                    view.countDown();
                } else {
                    view.finishCountDown();
                    ToastUtils.showShort(commonEntity.msg);
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("网络连接错误");
                view.finishCountDown();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void checkVerifyCode(String phone, String verifyCode) {
        model.checkVerifyCode(phone, verifyCode, new Observer<CommonEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                DisposableManager.get().add(DisposableFinal.MERCHANT_ENTRY_ACTIVITY_CHECK_VERIFY_CODE, d);
            }

            @Override
            public void onNext(CommonEntity commonEntity) {
                if (commonEntity.code == 1) {
                    view.checkVerifyCodeSuccess();
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

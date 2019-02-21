package com.harry.ebangbang.function.merchant_entry;

import android.text.TextUtils;

import com.harry.ebangbang.app_final.URLFinal;
import com.harry.ebangbang.base.model.BaseModel;
import com.harry.ebangbang.network.entity.CommonEntity;
import com.harry.ebangbang.network.service.MerchantEntryService;
import com.harry.ebangbang.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Harry on 2018/12/25.
 */
public class MerchantEntryModel extends BaseModel {

    private final MerchantEntryService service;

    public MerchantEntryModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(MerchantEntryService.class);
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
                         String licenceLink, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("name", name);
        params.put("contacts", contacts);
        params.put("contactsPhone", contactsPhone);
        params.put("category1", category1);
        if (!TextUtils.isEmpty(category2) && !category2.equals("0")) {
            params.put("category2", category2);
        }
        if (!TextUtils.isEmpty(category3) && !category3.equals("0")) {
            params.put("category3", category3);
        }
        params.put("siteProvince", siteProvince);
        params.put("siteCity", siteCity);
        if (!TextUtils.isEmpty(siteDistrict) && !siteDistrict.equals("0")) {
            params.put("siteDistrict", siteDistrict);
        }
        params.put("siteDetail", siteDetail);
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        params.put("distributionWay", distributionWay);
        params.put("appearanceLink", appearanceLink);
        params.put("interiorLink", interiorLink);
        params.put("logo", logo);
        params.put("businessLicenseLink", businessLicenseLink);
        params.put("legalPapersFrontLink", legalPapersFrontLink);
        params.put("licenceLink", licenceLink);

        service.register(URLFinal.MERCHANT_ENTRY, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getVerifyCode(String phone, String UUID, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("phone", phone);
        params.put("UUID", UUID);

        service.getVerifyCode(URLFinal.MERCHANT_ENTRY_GET_VERIFICATION_CODE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void checkVerifyCode(String phone, String verifyCode, Observer<CommonEntity> observer) {
        Map<String, String> params = new HashMap<>();

        params.put("phone", phone);
        params.put("securityCode", verifyCode);

        service.checkVerifyCode(URLFinal.MERCHANT_ENTRY_CHECK_VERIFICATION_CODE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

package com.harry.ebangbang.function.merchant_entry;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.function.add_address.receiving_address.ReceivingAddressActivity;
import com.harry.ebangbang.function.merchant_entry.select_content.SelectContentActivity;
import com.harry.ebangbang.utils.DeviceUtils;
import com.harry.ebangbang.utils.ImageUtil;
import com.harry.ebangbang.utils.PictureSelectorUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by Harry on 2018/12/24.
 * 商家入驻
 */
public class MerchantEntryActivity extends BaseActivity<MerchantEntryPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.tv_verification_code)
    TextView tvVerificationCode;
    @BindView(R.id.et_shop_name)
    EditText etShopName;
    @BindView(R.id.tv_shop_category)
    TextView tvShopCategory;
    @BindView(R.id.ll_shop_category)
    LinearLayout llShopCategory;
    @BindView(R.id.tv_shop_city)
    TextView tvShopCity;
    @BindView(R.id.ll_shop_city)
    LinearLayout llShopCity;
    @BindView(R.id.et_shop_address)
    EditText etShopAddress;
    @BindView(R.id.iv_shop_address)
    ImageView ivShopAddress;
    @BindView(R.id.tv_method)
    TextView tvMethod;
    @BindView(R.id.ll_method)
    LinearLayout llMethod;
    @BindView(R.id.iv_shop_img1)
    ImageView ivShopImg1;
    @BindView(R.id.iv_shop_img2)
    ImageView ivShopImg2;
    @BindView(R.id.iv_shop_img3)
    ImageView ivShopImg3;
    @BindView(R.id.iv_shop_img4)
    ImageView ivShopImg4;
    @BindView(R.id.iv_shop_img5)
    ImageView ivShopImg5;
    @BindView(R.id.iv_shop_img6)
    ImageView ivShopImg6;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    /**
     * 门头照片
     */
    public static final int REQUEST_CODE1 = 1001;
    /**
     * 店内图片
     */
    public static final int REQUEST_CODE2 = 1002;
    /**
     * 店铺LOGO
     */
    public static final int REQUEST_CODE3 = 1003;
    /**
     * 法人身份证
     */
    public static final int REQUEST_CODE4 = 1004;
    /**
     * 营业执照
     */
    public static final int REQUEST_CODE5 = 1005;
    /**
     * 许可证
     */
    public static final int REQUEST_CODE6 = 1006;
    public static final int REQUEST_CATEGORY = 1007;
    public static final int REQUEST_ADDRESS = 1008;
    public static final int REQUEST_LOCATION = 1009;
    private CountDownTimer countDownTimer;

    private int categoryId1;//一级分类ID
    private int categoryId2;//二级分类ID
    private int categoryId3;//三级分类ID
    private int addressId1;//一级地址ID
    private int addressId2;//二级地址ID
    private int addressId3;//三级地址ID
    private String latitude;
    private String longitude;
    private String base64_image1;
    private String base64_image2;
    private String base64_image3;
    private String base64_image4;
    private String base64_image5;
    private String base64_image6;

    @Override
    protected int setupView() {
        return R.layout.activity_merchant_entry;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("商家入驻");
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.MERCHANT_ENTRY_ACTIVITY_REGISTER);
        tags.add(DisposableFinal.MERCHANT_ENTRY_ACTIVITY_GET_VERIFY_CODE);
        tags.add(DisposableFinal.MERCHANT_ENTRY_ACTIVITY_CHECK_VERIFY_CODE);
        return tags;
    }

    @Override
    protected MerchantEntryPresenter bindPresenter() {
        return new MerchantEntryPresenter();
    }

    @OnClick({R.id.ll_back, R.id.tv_verification_code, R.id.ll_shop_category, R.id.ll_shop_city, R.id.iv_shop_address, R.id.ll_method, R.id.iv_shop_img1, R.id.iv_shop_img2, R.id.iv_shop_img3, R.id.iv_shop_img4, R.id.iv_shop_img5, R.id.iv_shop_img6, R.id.btn_submit})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this, SelectContentActivity.class);
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_verification_code://验证码
                String phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showShort("电话号码不能为空");
                    return;
                }
                mPresenter.getVerifyCode(phone, DeviceUtils.getUUID(this));
                break;
            case R.id.ll_shop_category://经营品类
                intent.putExtra("name", "经营品类");
                startActivityForResult(intent, REQUEST_CATEGORY);
                break;
            case R.id.ll_shop_city://所在城市
                intent.putExtra("name", "所在城市");
                startActivityForResult(intent, REQUEST_ADDRESS);
                break;
            case R.id.iv_shop_address://店铺地址
                Intent locationIntent = new Intent(this, ReceivingAddressActivity.class);
                startActivityForResult(locationIntent, REQUEST_LOCATION);
                break;
            case R.id.ll_method://配送方法
                showMethodDialog();
                break;
            case R.id.iv_shop_img1://门头照片
                PictureSelectorUtils.selectPicture(this, REQUEST_CODE1);
                break;
            case R.id.iv_shop_img2://店内图片
                PictureSelectorUtils.selectPicture(this, REQUEST_CODE2);
                break;
            case R.id.iv_shop_img3://店铺LOGO
                PictureSelectorUtils.selectPicture(this, REQUEST_CODE3);
                break;
            case R.id.iv_shop_img4://法人身份证
                PictureSelectorUtils.selectPicture(this, REQUEST_CODE4);
                break;
            case R.id.iv_shop_img5://营业执照
                PictureSelectorUtils.selectPicture(this, REQUEST_CODE5);
                break;
            case R.id.iv_shop_img6://许可证
                PictureSelectorUtils.selectPicture(this, REQUEST_CODE6);
                break;
            case R.id.btn_submit:
                String phone1 = etPhone.getText().toString().trim();
                String verificationCode = etVerificationCode.getText().toString().trim();
                if (TextUtils.isEmpty(phone1)) {
                    ToastUtils.showShort("电话号码不能为空");
                    return;
                } else if (TextUtils.isEmpty(verificationCode)) {
                    ToastUtils.showShort("验证码不能为空");
                    return;
                }
                mPresenter.checkVerifyCode(phone1, verificationCode);
                break;
        }
    }

    /**
     * 选择配送方法的Dialog
     */
    private void showMethodDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.item_merchant_entry_method, null);
        dialog.setView(view);
        dialog.show();

        FrameLayout flPlatform = view.findViewById(R.id.fl_platform);
        final CheckBox cbPlatform = view.findViewById(R.id.cb_platform);
        FrameLayout flBusiness = view.findViewById(R.id.fl_business);
        final CheckBox cbBusiness = view.findViewById(R.id.cb_business);
        Button btnConfirm = view.findViewById(R.id.btn_confirm);
        flPlatform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbPlatform.setChecked(true);
                cbBusiness.setChecked(false);
            }
        });
        flBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbPlatform.setChecked(false);
                cbBusiness.setChecked(true);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbPlatform.isChecked()) {
                    tvMethod.setText("平台配送");
                } else if (cbBusiness.isChecked()) {
                    tvMethod.setText("商家配送");
                }
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return; //什么都不选择, 直接点击返回或者取消按钮的时候return掉
        }
        if (requestCode == REQUEST_CODE1 || requestCode == REQUEST_CODE2
                || requestCode == REQUEST_CODE3 || requestCode == REQUEST_CODE4
                || requestCode == REQUEST_CODE5 || requestCode == REQUEST_CODE6) {

            final List<LocalMedia> medias = PictureSelector.obtainMultipleResult(data);
            // 1.media.getPath(); 为原图path
            // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
            // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
            // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
            String path = medias.get(0).getPath();
            Luban.with(this)
                    .load(path)
                    .setTargetDir(getPath())
                    .setFocusAlpha(false)
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {
                            // 压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            // 压缩成功后调用，返回压缩后的图片文件
                            switch (requestCode) {
                                case REQUEST_CODE1:
                                    Glide.with(MerchantEntryActivity.this).load(file.getPath()).into(ivShopImg1);
                                    base64_image1 = ImageUtil.image2Base64(file.getPath());
                                    break;
                                case REQUEST_CODE2:
                                    Glide.with(MerchantEntryActivity.this).load(file.getPath()).into(ivShopImg2);
                                    base64_image2 = ImageUtil.image2Base64(file.getPath());
                                    break;
                                case REQUEST_CODE3:
                                    Glide.with(MerchantEntryActivity.this).load(file.getPath()).into(ivShopImg3);
                                    base64_image3 = ImageUtil.image2Base64(file.getPath());
                                    break;
                                case REQUEST_CODE4:
                                    Glide.with(MerchantEntryActivity.this).load(file.getPath()).into(ivShopImg4);
                                    base64_image4 = ImageUtil.image2Base64(file.getPath());
                                    break;
                                case REQUEST_CODE5:
                                    Glide.with(MerchantEntryActivity.this).load(file.getPath()).into(ivShopImg5);
                                    base64_image5 = ImageUtil.image2Base64(file.getPath());
                                    break;
                                case REQUEST_CODE6:
                                    Glide.with(MerchantEntryActivity.this).load(file.getPath()).into(ivShopImg6);
                                    base64_image6 = ImageUtil.image2Base64(file.getPath());
                                    break;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            // 当压缩过程出现问题时调用

                        }
                    }).launch();

        }
        if (requestCode == REQUEST_CATEGORY && resultCode == ConstantFinal.COMMON_RESULT_CODE) {
            categoryId1 = data.getIntExtra("categoryId1", 0);
            categoryId2 = data.getIntExtra("categoryId2", 0);
            categoryId3 = data.getIntExtra("categoryId3", 0);
            String categoryName1 = data.getStringExtra("categoryName1");
            String categoryName2 = data.getStringExtra("categoryName2");
            String categoryName3 = data.getStringExtra("categoryName3");
            StringBuilder builder = new StringBuilder();
            if (!TextUtils.isEmpty(categoryName1)) {
                builder.append(categoryName1);
                if (!TextUtils.isEmpty(categoryName2)) {
                    builder.append("-");
                    builder.append(categoryName2);
                    if (!TextUtils.isEmpty(categoryName3)) {
                        builder.append("-");
                        builder.append(categoryName3);
                    }
                }
            }
            tvShopCategory.setText(builder);
        } else if (requestCode == REQUEST_ADDRESS && resultCode == ConstantFinal.COMMON_RESULT_CODE) {
            addressId1 = data.getIntExtra("addressId1", 0);
            addressId2 = data.getIntExtra("addressId2", 0);
            addressId3 = data.getIntExtra("addressId3", 0);
            String addressName1 = data.getStringExtra("addressName1");
            String addressName2 = data.getStringExtra("addressName2");
            String addressName3 = data.getStringExtra("addressName3");
            StringBuilder builder = new StringBuilder();
            if (!TextUtils.isEmpty(addressName1)) {
                builder.append(addressName1);
                if (!TextUtils.isEmpty(addressName2)) {
                    builder.append("-");
                    builder.append(addressName2);
                    if (!TextUtils.isEmpty(addressName3)) {
                        builder.append("-");
                        builder.append(addressName3);
                    }
                }
            }
            tvShopCity.setText(builder);
        } else if (requestCode == REQUEST_LOCATION && resultCode == ConstantFinal.COMMON_RESULT_CODE) {
            latitude = data.getStringExtra("latitude");
            longitude = data.getStringExtra("longitude");
        }

    }

    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/LuBan/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    /**
     * 倒计时
     */
    public void countDown() {
        countDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvVerificationCode.setText(millisUntilFinished / 1000 + "秒后重新获取");
                tvVerificationCode.setClickable(false);
                tvVerificationCode.setFocusable(false);
            }

            @Override
            public void onFinish() {
                tvVerificationCode.setText("获取验证码");
                tvVerificationCode.setClickable(true);
                tvVerificationCode.setFocusable(true);
            }
        };
        countDownTimer.start();
    }

    /**
     * 如果出现异常, 则直接完成倒计时
     */
    public void finishCountDown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer.onFinish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        PictureFileUtils.deleteCacheDirFile(this);
    }

    /**
     * 验证验证码成功, 去注册
     */
    public void checkVerifyCodeSuccess() {
        String shopName = etShopName.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String shopAddress = etShopAddress.getText().toString().trim();
        String method = tvMethod.getText().toString().trim();
        if (TextUtils.isEmpty(shopName)) {
            ToastUtils.showShort("请填写店铺名称");
            return;
        } else if (TextUtils.isEmpty(name)) {
            ToastUtils.showShort("请填写申请人");
            return;
        } else if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("请填写联系电话");
            return;
        } else if (TextUtils.isEmpty(shopAddress)) {
            ToastUtils.showShort("请填写详细地址");
            return;
        } else if (TextUtils.isEmpty(method)) {
            ToastUtils.showShort("请选择配送方法");
            return;
        } else if (categoryId1 == 0) {
            ToastUtils.showShort("请选择经营品类");
            return;
        } else if (addressId1 == 0) {
            ToastUtils.showShort("请选择所在城市");
            return;
        } else if (addressId2 == 0) {
            ToastUtils.showShort("请选择所在城市");
            return;
        } else if (TextUtils.isEmpty(longitude) || TextUtils.isEmpty(latitude)) {
            ToastUtils.showShort("请点击店铺地址的定位按钮进行定位");
            return;
        } else if (TextUtils.isEmpty(base64_image1)) {
            ToastUtils.showShort("请选择门头图片");
            return;
        } else if (TextUtils.isEmpty(base64_image2)) {
            ToastUtils.showShort("请选择店内图片");
            return;
        } else if (TextUtils.isEmpty(base64_image3)) {
            ToastUtils.showShort("请选择店铺Logo");
            return;
        } else if (TextUtils.isEmpty(base64_image4)) {
            ToastUtils.showShort("请选择法人身份证");
            return;
        } else if (TextUtils.isEmpty(base64_image5)) {
            ToastUtils.showShort("请选择营业执照");
            return;
        } else if (TextUtils.isEmpty(base64_image6)) {
            ToastUtils.showShort("请选择许可证");
            return;
        }
        if (method.equals("平台配送")) {
            mPresenter.register(shopName, name, phone,
                    String.valueOf(categoryId1), String.valueOf(categoryId2), String.valueOf(categoryId3),
                    String.valueOf(addressId1), String.valueOf(addressId2), String.valueOf(addressId3),
                    shopAddress, longitude, latitude, "1", base64_image1, base64_image2,
                    base64_image3, base64_image5, base64_image4, base64_image6);
        } else {
            mPresenter.register(shopName, name, phone,
                    String.valueOf(categoryId1), String.valueOf(categoryId2), String.valueOf(categoryId3),
                    String.valueOf(addressId1), String.valueOf(addressId2), String.valueOf(addressId3),
                    shopAddress, longitude, latitude, "2", base64_image1, base64_image2,
                    base64_image3, base64_image5, base64_image4, base64_image6);
        }

    }
}

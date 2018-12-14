package com.harry.ebangbang.function.login.binding_phone;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.application.EBangBangApplication;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.function.login.LoginActivity;
import com.harry.ebangbang.function.main.MainActivity;
import com.harry.ebangbang.network.entity.BindingPhoneEntity;
import com.harry.ebangbang.utils.DeviceUtils;
import com.harry.ebangbang.utils.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/14.
 * 微信登录 - 绑定手机号页面
 */
public class BindingPhoneActivity extends BaseActivity<BindingPhonePresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_verify_code)
    TextView tvVerifyCode;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    private CountDownTimer mCountDownTimer;
    private String openId;
    private String nickName;
    private String headAddress;
    private int mode;

    @Override
    protected int setupView() {
        return R.layout.activity_binding_phone;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mode = getIntent().getIntExtra("mode", 0);
        openId = getIntent().getStringExtra("openId");
        nickName = getIntent().getStringExtra("nickName");
        headAddress = getIntent().getStringExtra("headAddress");

        if (mode == 1) {//已有手机号
            etPassword.setVisibility(View.GONE);
            etConfirmPassword.setVisibility(View.GONE);
        }

        tvTitle.setText("绑定手机");
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tag = new ArrayList<>();
        tag.add(DisposableFinal.BINDING_PHONE_ACTIVITY_GET_VERIFY_CODE);
        tag.add(DisposableFinal.BINDING_PHONE_ACTIVITY_CHECK_VERIFY_CODE);
        tag.add(DisposableFinal.BINDING_PHONE_ACTIVITY_BINDING_NEW_PHONE);
        tag.add(DisposableFinal.BINDING_PHONE_ACTIVITY_BINDING_OLD_PHONE);
        return tag;
    }

    @Override
    protected BindingPhonePresenter bindPresenter() {
        return new BindingPhonePresenter();
    }

    @OnClick({R.id.ll_back, R.id.tv_verify_code, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_verify_code:
                String phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showShort("电话号码不能为空");
                    return;
                }
                mPresenter.getVerifyCode(phone, DeviceUtils.getUUID(this));
                break;
            case R.id.btn_commit:
                String phone1 = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone1)) {
                    ToastUtils.showShort("电话号码不能为空");
                    return;
                }
                String verifyCode = etVerifyCode.getText().toString().trim();
                if (TextUtils.isEmpty(verifyCode)) {
                    ToastUtils.showShort("验证码不能为空");
                    return;
                }
                mPresenter.checkVerifyCode(phone1, verifyCode);
                break;
        }
    }

    public void commit() {
        String phone = etPhone.getText().toString().trim();
        String verifyCode = etVerifyCode.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (mode == 1) {//已有机号绑定
            mPresenter.bindingOldPhone(phone, openId, nickName, headAddress);
        } else {//新手机号绑定
            if (TextUtils.isEmpty(password)) {
                ToastUtils.showShort("密码不能为空");
                return;
            } else if (TextUtils.isEmpty(confirmPassword)) {
                ToastUtils.showShort("确认密码不能为空");
                return;
            }
            if (!password.equals(confirmPassword)) {
                ToastUtils.showShort("两次输入的密码不一致");
                return;
            }
            mPresenter.bindingNewPhone(phone, password, openId, nickName, headAddress);
        }

    }


    public void countDown() {
        mCountDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvVerifyCode.setText(millisUntilFinished / 1000 + "秒后重新获取");
                tvVerifyCode.setClickable(false);
            }

            @Override
            public void onFinish() {
                tvVerifyCode.setText("获取验证码");
                tvVerifyCode.setClickable(true);
            }
        };
        mCountDownTimer.start();
    }

    /**
     * 如果出现异常, 则直接完成倒计时
     */
    public void finishCountDown() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer.onFinish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    public void bindingSuccess(BindingPhoneEntity.DataBean data, String headPortraitLink) {
        SPUtils.putString(UserInfo.ID.name(), String.valueOf(data.id));
        SPUtils.putString(UserInfo.LOGIN_NAME.name(), data.loginName);
        SPUtils.putString(UserInfo.PHONE.name(), data.phone);
        SPUtils.putString(UserInfo.NICK_NAME.name(), data.nickname);
        SPUtils.putString(UserInfo.HEADER_BASE.name(), headPortraitLink);
        EBangBangApplication.getAppContext().finishActivity(LoginActivity.class);//关闭登录页面
        startActivity(new Intent(this, MainActivity.class));
        SPUtils.putBoolean(UserInfo.IS_LOGIN.name(), true);
        finish();
    }
}

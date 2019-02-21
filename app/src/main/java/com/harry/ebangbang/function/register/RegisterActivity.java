package com.harry.ebangbang.function.register;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.utils.DeviceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/11/7.
 * 注册
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_identifying_code)
    EditText etIdentifyingCode;
    @BindView(R.id.tv_get_identifying_code)
    TextView tvGetIdentifyingCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.et_invite_code)
    EditText etInviteCode;
    @BindView(R.id.btn_register)
    Button btnRegister;

    /**
     * 倒计时的类
     */
    private CountDownTimer countDownTimer;

    @Override
    protected int setupView() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.REGISTER_ACTIVITY_REGISTER);
        tags.add(DisposableFinal.REGISTER_ACTIVITY_GET_VERIFY_CODE);
        tags.add(DisposableFinal.REGISTER_ACTIVITY_CHECK_VERIFY_CODE);
        return tags;
    }

    @Override
    protected RegisterPresenter bindPresenter() {
        return new RegisterPresenter();
    }

    @OnClick({R.id.tv_get_identifying_code, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_identifying_code:
                String phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showShort("电话号码不能为空");
                    return;
                }
                mPresenter.getVerifyCode(phone, DeviceUtils.getUUID(this));
                break;
            case R.id.btn_register:
                register();
                break;
        }
    }

    private void register() {
        String phone = etPhone.getText().toString().trim();
        String identifyingCode = etIdentifyingCode.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("电话号码不能为空");
            return;
        } else if (TextUtils.isEmpty(identifyingCode)) {
            ToastUtils.showShort("验证码不能为空");
            return;
        } else if (TextUtils.isEmpty(password)) {
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
        mPresenter.checkVerifyCode(phone, password, identifyingCode);
    }

    public void countDown() {
        countDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvGetIdentifyingCode.setText(millisUntilFinished / 1000 + "秒后重新获取");
                tvGetIdentifyingCode.setClickable(false);
            }

            @Override
            public void onFinish() {
                tvGetIdentifyingCode.setText("获取验证码");
                tvGetIdentifyingCode.setClickable(true);
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
    }
}

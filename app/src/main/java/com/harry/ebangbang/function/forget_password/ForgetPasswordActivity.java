package com.harry.ebangbang.function.forget_password;

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
import com.harry.ebangbang.utils.DeviceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/11/19.
 * 忘记密码
 */
public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordPresenter> {

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

    @Override
    protected int setupView() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("找回密码");
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tag = new ArrayList<>();
        tag.add(DisposableFinal.FORGET_PASSWORD_ACTIVITY_FORGET_PASSWORD);
        tag.add(DisposableFinal.FORGET_PASSWORD_ACTIVITY_GET_VERIFY_CODE);
        tag.add(DisposableFinal.FORGET_PASSWORD_ACTIVITY_CHECK_VERIFY_CODE);
        return tag;
    }

    @Override
    protected ForgetPasswordPresenter bindPresenter() {
        return new ForgetPasswordPresenter();
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
                commit();
                break;
        }
    }

    private void commit() {
        String phone = etPhone.getText().toString().trim();
        String verifyCode = etVerifyCode.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("电话号码不能为空");
            return;
        } else if (TextUtils.isEmpty(verifyCode)) {
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
        mPresenter.checkVerifyCode(phone, password, verifyCode);
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
}

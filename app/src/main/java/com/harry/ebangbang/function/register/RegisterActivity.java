package com.harry.ebangbang.function.register;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.harry.ebangbang.R;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.base.presenter.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/11/7.
 * 注册
 */
public class RegisterActivity extends BaseActivity {

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
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @OnClick({R.id.tv_get_identifying_code, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_identifying_code:
                countDown();
                break;
            case R.id.btn_register:

                break;
        }
    }

    private void countDown() {
        countDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvGetIdentifyingCode.setText(millisUntilFinished / 1000 + "后重新获取");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}

package com.harry.ebangbang.function.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.application.EBangBangApplication;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.event.WXLoginEvent;
import com.harry.ebangbang.function.forget_password.ForgetPasswordActivity;
import com.harry.ebangbang.function.login.binding_phone.BindingPhoneActivity;
import com.harry.ebangbang.function.main.MainActivity;
import com.harry.ebangbang.function.register.RegisterActivity;
import com.harry.ebangbang.network.entity.WxLoginEntity;
import com.harry.ebangbang.utils.RxPermissionsUtils;
import com.harry.ebangbang.utils.SPUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/11/6.
 * 登录页面
 */
public class LoginActivity extends BaseActivity<LoginPresenter> {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register_now)
    TextView tvRegisterNow;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.iv_we_chat)
    ImageView ivWeChat;
    @BindView(R.id.iv_ali_pay)
    ImageView ivAliPay;

    @Override
    protected int setupView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        for (String aPermissionArray : RxPermissionsUtils.permissionArray) {
            boolean isRegister = RxPermissionsUtils.checkPermissions(LoginActivity.this, aPermissionArray);
            if (!isRegister) {
                registerPermission();
                break;
            }
        }
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.LOGIN_ACTIVITY_LOGIN);
        return tags;
    }

    @Override
    protected LoginPresenter bindPresenter() {
        return new LoginPresenter();
    }

    /**
     * 提示用户注册app相关的权限
     */
    private void registerPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("系统需要注册相关用户权限来提高您的用户体验")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        RxPermissionsUtils.registerPermissions(LoginActivity.this);
                    }
                }).show();
    }

    @OnClick({R.id.btn_login, R.id.tv_register_now, R.id.tv_forget_password, R.id.iv_we_chat, R.id.iv_ali_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:    //登录
                login();
                break;
            case R.id.tv_register_now:  //注册
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_forget_password:   //忘记密码
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.iv_we_chat:   //微信登录
                wxLogin();
                break;
            case R.id.iv_ali_pay:   //支付宝登录
                //暂无支付宝登录功能
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        String userName = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            ToastUtils.showShort("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("密码不能为空");
            return;
        }
        mPresenter.login(userName, password);
    }

    /**
     * 微信登录
     */
    public void wxLogin() {
        if (!EBangBangApplication.getAppContext().mWXAPI.isWXAppInstalled()) {
            ToastUtils.showShort("您还未安装微信客户端");
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo";
        EBangBangApplication.getAppContext().mWXAPI.sendReq(req);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(new WXLoginEvent(null));
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void loginEvent(WXLoginEvent wxLoginEvent) {
        String wxCode = wxLoginEvent.getWxCode();
        mPresenter.wxLogin(wxCode);
    }

    /**
     * @param wxLoginEntity 微信登录成功返回
     */
    public void wxLoginResult(final WxLoginEntity wxLoginEntity) {
        SPUtils.putString(UserInfo.HEADER_URL.name(), wxLoginEntity.headAddress);
        SPUtils.putString(UserInfo.NICK_NAME.name(), wxLoginEntity.nickname);

        if (wxLoginEntity.Isbinding == 0) {
            //为绑定手机号
            View view = View.inflate(this, R.layout.dialog_select_login_mode, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final AlertDialog dialog = builder.create();
            dialog.setView(view);
            dialog.setCancelable(true);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    ToastUtils.showShort("您取消了绑定手机, 暂无法使用微信登录功能");
                }
            });
            dialog.show();
            TextView tvOld = view.findViewById(R.id.tv_old);
            TextView tvNew = view.findViewById(R.id.tv_new);
            tvOld.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, BindingPhoneActivity.class);
                    intent.putExtra("mode", 1);
                    intent.putExtra("openId", wxLoginEntity.openid);
                    intent.putExtra("nickName", wxLoginEntity.nickname);
                    intent.putExtra("headAddress", wxLoginEntity.headAddress);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });
            tvNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, BindingPhoneActivity.class);
                    intent.putExtra("mode", 2);
                    intent.putExtra("openId", wxLoginEntity.openid);
                    intent.putExtra("nickName", wxLoginEntity.nickname);
                    intent.putExtra("headAddress", wxLoginEntity.headAddress);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });
        } else {
            //已绑定手机号 直接登录
            SPUtils.putString(UserInfo.ID.name(), String.valueOf(wxLoginEntity.data.id));
            SPUtils.putString(UserInfo.LOGIN_NAME.name(), wxLoginEntity.data.loginName);
            SPUtils.putString(UserInfo.PHONE.name(), wxLoginEntity.data.phone);
            SPUtils.putString(UserInfo.TYPE.name(), wxLoginEntity.data.type);
            SPUtils.putString(UserInfo.NICK_NAME.name(), wxLoginEntity.data.nickname);
            SPUtils.putString(UserInfo.HEADER_BASE.name(), wxLoginEntity.headPortraitLink);
            SPUtils.putString(UserInfo.HEADER_URL.name(), wxLoginEntity.data.headAddress);
            startActivity(new Intent(this, MainActivity.class));
            SPUtils.putBoolean(UserInfo.IS_LOGIN.name(), true);
            finish();
        }
    }
}

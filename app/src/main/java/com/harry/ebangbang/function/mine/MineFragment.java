package com.harry.ebangbang.function.mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.UserInfo;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.function.login.LoginActivity;
import com.harry.ebangbang.function.order_manage.OrderManageActivity;
import com.harry.ebangbang.utils.SPUtils;
import com.ruffian.library.RTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Harry on 2018/11/6.
 * 我的
 */
public class MineFragment extends BaseFragment<MinePresenter> {

    @BindView(R.id.iv_user_header)
    ImageView ivUserHeader;
    @BindView(R.id.tv_sign_out)
    TextView tvSignOut;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_wallet)
    RTextView tvWallet;
    @BindView(R.id.tv_red_wallet)
    RTextView tvRedWallet;
    @BindView(R.id.tv_cash_coupon)
    RTextView tvCashCoupon;
    @BindView(R.id.fl_order_manager)
    FrameLayout flOrderManager;
    Unbinder unbinder;

    @Override
    protected int setupView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected MinePresenter bindPresenter() {
        return new MinePresenter();
    }

    /**
     * 退出登录
     */
    private void signOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setMessage("确认退出登录吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(mActivity, LoginActivity.class));
                        SPUtils.putBoolean(UserInfo.IS_LOGIN.name(), false);
                        mActivity.finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_user_header, R.id.tv_sign_out, R.id.fl_order_manager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_header:
                break;
            case R.id.tv_sign_out:
                signOut();
                break;
            case R.id.fl_order_manager:
                startActivity(new Intent(mActivity, OrderManageActivity.class));
                break;
        }
    }
}

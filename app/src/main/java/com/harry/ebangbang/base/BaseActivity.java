package com.harry.ebangbang.base;

import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.harry.ebangbang.R;
import com.harry.ebangbang.application.EBangBangApplication;
import com.harry.ebangbang.base.presenter.BasePresenter;
import com.harry.ebangbang.base.view.BaseActivityImpl;
import com.harry.ebangbang.rx.DisposableManager;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

/**
 * Created by Harry on 2018/8/13.
 */
public abstract class BaseActivity<P extends BasePresenter> extends BaseActivityImpl<P> {

    private EBangBangApplication application;
    private AlertDialog dialog;
    protected Bundle savedInstanceState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.savedInstanceState = savedInstanceState;

        setContentView(setupView());
        application = (EBangBangApplication) getApplication();
        application.addActivity(this);

        //只是手机竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置状态栏颜色
        StatusBarUtil.setColor(this, getResources().getColor(R.color.app_status_bar_color), 0);

        initView();

    }

    /**
     * @return 布局文件的ID
     */
    protected abstract int setupView();

    /**
     * 初始化布局(例如findViewById)
     */
    protected abstract void initView();

    /**
     * @return RxJava中的Disposable方法
     */
    protected abstract ArrayList<Object> cancelNetWork();

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.LoadingDialog);
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setView(View.inflate(this, R.layout.dialog_base, null));
    }

    /**
     * 显示LoadingDialog
     */
    public void showDialog() {
        initDialog();
        dialog.show();
    }

    /**
     * 关闭LoadingDialog
     */
    public void dismissDialog() {
        if (dialog != null) {
            dialog.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        application.finishActivity(this);   //清除栈中的Activity

        ArrayList<Object> tags = cancelNetWork();
        if (tags != null && tags.size() != 0) {
            for (Object tag : tags) {
                DisposableManager.get().cancel(tag);
            }
        }
    }

}

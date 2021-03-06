package com.harry.ebangbang.application;

import android.app.Activity;
import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Stack;

/**
 * Created by Harry on 2018/11/6.
 */
public class EBangBangApplication extends Application {

    private Stack<Activity> activityStack;
    private static EBangBangApplication appContext;
    public IWXAPI mWXAPI;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;

        //初始化AndroidUtils
        Utils.init(this);

        //注册微信登录
        registerToWX();
    }

    /**
     * 注册微信登录
     */
    private void registerToWX() {
        String WE_CHAT_APP_ID = "wx76be99258cfaf85a";
        mWXAPI = WXAPIFactory.createWXAPI(this, WE_CHAT_APP_ID, false);
        // 将该app注册到微信
        mWXAPI.registerApp(WE_CHAT_APP_ID);
    }

    public static EBangBangApplication getAppContext() {
        return appContext;
    }

    /**
     * 返回栈中Activity的个数
     */
    public int getActivityCount() {
        if (activityStack == null) return 0;
        return activityStack.size();
    }

    /**
     * 添加Activity到栈中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * @return 返回最后一个压入栈中的Activity(当前Activity)
     */
    public Activity getCurrentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束掉指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束掉指定类名的Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                return;
            }
        }
    }

    /**
     * 获取指定类名的Activity
     *
     * @param cls
     */
    public Activity getActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 结束掉最后一个压入栈中的Activity(当前Activity)
     */
    public void finishCurrentActivity() {
        finishActivity(activityStack.lastElement());
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
}

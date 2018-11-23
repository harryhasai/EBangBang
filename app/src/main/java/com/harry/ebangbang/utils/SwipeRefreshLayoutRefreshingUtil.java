package com.harry.ebangbang.utils;

import android.support.v4.widget.SwipeRefreshLayout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Harry on 2018/11/20.
 */
public class SwipeRefreshLayoutRefreshingUtil {

    /**
     * 由于setRefreshing(boolean)方法将mNotify置为false，所以必然不会执行到mListener.onRefresh()方法。
     * 如果想通过手动设置刷新并且触发监听事件则需要调用
     * private void setRefreshing(boolean refreshing, final boolean notify)
     */
    public static void setRefreshing(SwipeRefreshLayout swipeRefreshLayout) {
        try {
            Class clazz = swipeRefreshLayout.getClass();
            Method method = clazz.getDeclaredMethod("setRefreshing", boolean.class, boolean.class);
            method.setAccessible(true);
            method.invoke(swipeRefreshLayout, true, true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

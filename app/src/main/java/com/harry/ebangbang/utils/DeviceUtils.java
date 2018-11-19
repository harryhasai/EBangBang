package com.harry.ebangbang.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by Harry on 2018/11/19.
 * 获取设备UUID
 */
public class DeviceUtils {

    @SuppressLint("MissingPermission")
    public static String getUUID(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
         String imsi = mTelephonyMgr.getSubscriberId(); //获取IMSI号
         String imei = mTelephonyMgr.getDeviceId(); //获取IMEI号
        return imsi;
    }
}

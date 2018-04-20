package com.zhd.admin.androldlrden;

import android.util.Log;

import com.zhd.admin.androldlrden.IFace.Api;

public class L implements Api {
    private static String TAG = "zhd";

    public static void d(String message) {
        if (!isDebug) {
            return;
        } else {
            Log.d(TAG, message);
        }
    }
    public static void d(String describe, String message) {
        if (!isDebug) {
            return;
        } else {
            Log.d(TAG, describe +":"+ message);
        }
    }
}

package com.zhd.admin.androldlrden.model;

public class LoginModel {

    public void login(String name, String pwd, CallBack callBack1) {
        if (callBack1 != null) {
            callBack1.callBack("");
        }
    }

    public interface CallBack {
        void callBack(String result);
    }
}

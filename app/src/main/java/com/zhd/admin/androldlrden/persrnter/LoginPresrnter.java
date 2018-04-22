package com.zhd.admin.androldlrden.persrnter;

import com.zhd.admin.androldlrden.activity.LoginActivity;
import com.zhd.admin.androldlrden.util.L;

public class LoginPresrnter extends BasePersenter<LoginActivity> {

    public LoginPresrnter(LoginActivity loginActivity) {
        super(loginActivity);
    }

    public void test() {
        L.d("test", "测试");
    }
}

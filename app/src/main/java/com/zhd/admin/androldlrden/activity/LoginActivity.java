package com.zhd.admin.androldlrden.activity;

import com.zhd.admin.androldlrden.R;
import com.zhd.admin.androldlrden.mvp.BaseMvpActivity;
import com.zhd.admin.androldlrden.persrnter.LoginPresrnter;

public class LoginActivity extends BaseMvpActivity<LoginPresrnter> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresrnter getPersenter() {
        return new LoginPresrnter(this);
    }

    @Override
    protected void initData() {
        test();
    }

    @Override
    protected void initListener() {

    }

    private void test() {
        mPersenter.test();
    }
}

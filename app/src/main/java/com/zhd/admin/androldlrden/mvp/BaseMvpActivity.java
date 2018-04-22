package com.zhd.admin.androldlrden.mvp;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhd.admin.androldlrden.IFace.BseActivityFace;
import com.zhd.admin.androldlrden.R;
import com.zhd.admin.androldlrden.base.BaseActivity;
import com.zhd.admin.androldlrden.persrnter.BasePersenter;

public abstract class BaseMvpActivity<P extends BasePersenter> extends BaseActivity implements BseActivityFace {
    protected abstract int getLayoutId();
    protected P mPersenter;
    protected abstract P getPersenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mPersenter=getPersenter();
        initListener();
        initData();
    }

    protected abstract   void initData();
    protected abstract   void initListener();
}

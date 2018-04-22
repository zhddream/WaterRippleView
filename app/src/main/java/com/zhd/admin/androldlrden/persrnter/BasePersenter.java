package com.zhd.admin.androldlrden.persrnter;

import com.zhd.admin.androldlrden.IFace.BseActivityFace;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class BasePersenter<MContext extends BseActivityFace> {
    protected Reference<MContext> mContext;

    protected MContext getMContext() {
        return mContext.get();
    }


    protected BasePersenter(MContext mContext) {
        this.mContext = new WeakReference<>(mContext);
    }
}

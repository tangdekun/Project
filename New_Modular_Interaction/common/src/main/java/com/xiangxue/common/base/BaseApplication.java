package com.xiangxue.common.base;

import android.app.Application;
import android.util.Log;

import com.xiangxue.common.utils.Config;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Config.TAG, "common/BaseApplication onCreate run");
    }
}

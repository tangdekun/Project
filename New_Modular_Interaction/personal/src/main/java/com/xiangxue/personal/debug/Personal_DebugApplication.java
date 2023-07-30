package com.xiangxue.personal.debug;

import android.app.Application;
import android.util.Log;

import com.xiangxue.common.utils.Config;

public class Personal_DebugApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Config.TAG, "personal/debug/Personal_DebugApplication");
    }
}

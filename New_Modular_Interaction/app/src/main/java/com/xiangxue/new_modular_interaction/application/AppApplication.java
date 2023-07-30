package com.xiangxue.new_modular_interaction.application;

import com.xiangxue.common.RecordPathManager;
import com.xiangxue.common.base.BaseApplication;
import com.xiangxue.new_modular_interaction.MainActivity;
import com.xiangxue.order.Order_MainActivity;
import com.xiangxue.personal.Personal_MainActivity;

public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        // 如果项目有100个Activity，这种加法会不会太那个？  缺点
        RecordPathManager.addGroupInfo("app", "MainActivity", MainActivity.class);
        RecordPathManager.addGroupInfo("order", "Order_MainActivity", Order_MainActivity.class);
        RecordPathManager.addGroupInfo("personal", "Personal_MainActivity", Personal_MainActivity.class);
    }
}

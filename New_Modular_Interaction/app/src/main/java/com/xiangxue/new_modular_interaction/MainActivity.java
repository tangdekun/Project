package com.xiangxue.new_modular_interaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xiangxue.common.utils.Config;
import com.xiangxue.order.Order_MainActivity;
import com.xiangxue.personal.Personal_MainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BuildConfig.isRelease) {
            Log.e(Config.TAG, "当前为：集成化模式，除app可运行，其他子模块都是Android Library");
        } else {
            Log.e(Config.TAG, "当前为：组件化模式，app/order/personal子模块都可独立运行");
        }
    }

    // app ---> Order订单
    public void jumpOrder(View view) {
        Intent intent = new Intent(this, Order_MainActivity.class);
        intent.putExtra("name", "Derry");
        startActivity(intent);
    }

    // app ---> Personal我的
    public void jumpPersonal(View view) {
        Intent intent = new Intent(this, Personal_MainActivity.class);
        intent.putExtra("name", "Derry");
        startActivity(intent);
    }
}

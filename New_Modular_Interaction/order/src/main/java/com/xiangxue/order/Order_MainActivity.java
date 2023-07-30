package com.xiangxue.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xiangxue.common.RecordPathManager;

// order订单
public class Order_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_main);

        // order订单 ---> Personal我的
        // Personal_Main  没有任何依赖
    }

    public void jumpPersonal(View view) {
        // todo 方式一 类加载
        // 类加载跳转，可以成功。维护成本较高且容易出现人为失误
        try {
            Class targetClass = Class.forName("com.xiangxue.personal.Personal_MainActivity");
            Intent intent = new Intent(this, targetClass);
            intent.putExtra("name", "derry");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // personal/Personal_MainActivity getMap
        // todo 方式二 全局Map
        Class<?> targetActivity =
                RecordPathManager.startTargetActivity("personal", "Personal_MainActivity");
        startActivity(new Intent(this, targetActivity));
    }
}

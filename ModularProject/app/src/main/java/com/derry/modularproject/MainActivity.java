package com.derry.modularproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.derry.login.Login_MainActivity;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 以前
        String serverURL = NetworkConfig.DEBUG;

        // 现在
        serverURL = BuildConfig.debug;
        Toast.makeText(this, "serverURL:" + serverURL, Toast.LENGTH_SHORT).show();

        if (BuildConfig.isRelease) {
            Log.d(TAG, "onCreate: 当前是：集成化 线上环境，以app壳为主导运行的方式");
            Toast.makeText(this, "当前是：集成化 线上环境，以app壳为主导运行的方式", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "onCreate: 当前是：组件化 测试环境，所有的子模块都可以独立运行");
            Toast.makeText(this, "当前是：组件化 测试环境，所有的子模块都可以独立运行", Toast.LENGTH_SHORT).show();
        }
    }

    // 从App壳 到 Login模块
    public void startLoginModel(View view) {
        startActivity(new Intent(MainActivity.this, Login_MainActivity.class));
    }
}
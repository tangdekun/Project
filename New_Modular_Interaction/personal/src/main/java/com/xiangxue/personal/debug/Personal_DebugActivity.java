package com.xiangxue.personal.debug;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xiangxue.common.utils.Config;
import com.xiangxue.personal.Personal_MainActivity;
import com.xiangxue.personal.R;

import static androidx.core.content.ContextCompat.startActivity;

public class Personal_DebugActivity extends Personal_DebugBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity_debug);

        Log.e(Config.TAG, "personal/debug/Personal_DebugActivity");
    }

    public void jump(View view) {
        startActivity(new Intent(this, Personal_MainActivity.class));
    }
}

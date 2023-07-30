package com.xiangxue.personal.debug;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.xiangxue.common.utils.Config;
import javax.annotation.Nullable;

public class Personal_DebugBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(Config.TAG, "personal/debug/Personal_DebugBaseActivity");
    }
}

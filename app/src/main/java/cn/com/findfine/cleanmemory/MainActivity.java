package cn.com.findfine.cleanmemory;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnOpen;
    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        btnOpen = (Button) findViewById(R.id.btn_open);

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, MyAccessibilityService.class);
//                startService(intent);
//                getRunningApp();

                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", "com.UCMobile", null);
                intent.setData(uri);
                startActivity(intent);
            }
        });

        btnTest = (Button) findViewById(R.id.btn_test);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("TAG", "Auto Click hahaha");
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
            }
        });
    }

    private void getRunningApp() {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        // 通过调用ActivityManager的getRunningAppProcesses()方法获得系统里所有正在运行的进程
        List<ActivityManager.RunningAppProcessInfo> appProcessList = mActivityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : appProcessList) {
            for (String pkgName : runningAppProcessInfo.pkgList) {
                Log.i("TAG", pkgName);
            }
            Log.i("TAG", runningAppProcessInfo.processName);
        }
    }
}

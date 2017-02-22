package cn.com.findfine.cleanmemory;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

//                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                Uri uri = Uri.fromParts("package", "com.UCMobile", null);
//                intent.setData(uri);
//                startActivity(intent);

//                getInstalledPackages();

//                executeCmd("am force-stop com.taobao.taobao");
//                executeCmd(new String[]{"am", "force-stop" ,"com.taobao.taobao"});
//                executeCmd(new String[]{"ls"});
                execCommand("ls");
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

    private void getInstalledPackages() {
        PackageManager packageManager = this.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager .getInstalledPackages(0);

        for (PackageInfo packageInfo : packageInfoList) {
            Log.i("TAG", packageInfo.packageName);
        }
    }

    private void executeCmd(String[] cmd) {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();

                Runtime runtime = Runtime.getRuntime();
                try {
                    Process exec = runtime.exec(cmd);
                    BufferedReader in = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                    String line = in.readLine();
                    Log.i("TAG", " ==== " + line);
//                    while ((line = in.readLine()) != null) {
//                        Log.i("TAG", line);
//                    }
                    in.close();
                    exec.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            }
//        }.start();
    }

    public void execCommand(String command) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process proc = runtime.exec(command);
            if (proc.waitFor() != 0) {
                System.err.println("exit value = " + proc.exitValue());
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line+"-");
            }
            System.out.println(stringBuffer.toString());

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

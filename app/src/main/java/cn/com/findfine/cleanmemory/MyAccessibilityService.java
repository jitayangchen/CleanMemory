package cn.com.findfine.cleanmemory;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by yangchen on 2017/2/21.
 */

public class MyAccessibilityService extends AccessibilityService {

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
//        Log.i("TAG", "------------onServiceConnected---------");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
//        Log.i("TAG", "------------onAccessibilityEvent--------- " + accessibilityEvent.getSource().toString());
        List<AccessibilityNodeInfo> text1 = accessibilityEvent.getSource().findAccessibilityNodeInfosByText("TEST");
        if (text1 != null && text1.size() > 0) {
            AccessibilityNodeInfo text = text1.get(0);
            text.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }

    @Override
    public void onInterrupt() {
//        Log.i("TAG", "------------onInterrupt---------");
    }

    @Override
    public boolean onUnbind(Intent intent) {
//        Log.i("TAG", "------------onUnbind---------");
        return super.onUnbind(intent);
    }
}

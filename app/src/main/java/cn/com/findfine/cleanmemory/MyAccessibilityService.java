package cn.com.findfine.cleanmemory;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.util.Log;
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
        Log.i("TAG", "------------onServiceConnected---------");
        AccessibilityServiceInfo serviceInfo = new AccessibilityServiceInfo();
        serviceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        serviceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
//        serviceInfo.packageNames = new String[]{""};
        serviceInfo.notificationTimeout=100;
        setServiceInfo(serviceInfo);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Log.i("TAG", "------------onAccessibilityEvent--------- ");
        AccessibilityNodeInfo source = accessibilityEvent.getSource();
        if (source != null) {
            List<AccessibilityNodeInfo> forceStops = accessibilityEvent.getSource().findAccessibilityNodeInfosByText("FORCE STOP");
            for (AccessibilityNodeInfo forceStop : forceStops) {
                if (forceStop != null && "android.widget.Button".equals(forceStop.getClassName())) {
                    if (forceStop.isEnabled()) {
                        forceStop.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    }
                }
            }

            List<AccessibilityNodeInfo> dialogOks = accessibilityEvent.getSource().findAccessibilityNodeInfosByText("OK");
            for (AccessibilityNodeInfo dialogOk : dialogOks) {
                if (dialogOk != null && "android.widget.Button".equals(dialogOk.getClassName())) {
                    if (dialogOk.isEnabled()) {
                        dialogOk.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    }
                }
            }
        }
    }

    @Override
    public void onInterrupt() {
        Log.i("TAG", "------------onInterrupt---------");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("TAG", "------------onUnbind---------");
        return super.onUnbind(intent);
    }
}

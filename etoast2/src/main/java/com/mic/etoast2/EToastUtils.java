package com.mic.etoast2;

import android.app.Activity;
import android.app.AppOpsManager;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 如果需要使用EToastUtils，需要在Application中注册，以便获取当前的Activity
 * */
public class EToastUtils implements Application.ActivityLifecycleCallbacks {
    private static EToastUtils instance;
    private EToastUtils(){}
    public static EToastUtils init(Application application){
        if(instance == null){
            instance = new EToastUtils();
            application.registerActivityLifecycleCallbacks(instance);
        }
        return instance;
    }
    public static EToastUtils getInstance(){
        return instance;
    }
    private Activity mActivity;
    public Activity getActivity(){
        if(instance != null)
            return instance.mActivity;
        else
            return null;
    }
    public static void show(CharSequence text){
        if(instance.mActivity != null&&instance != null)
            Toast.makeText(instance.mActivity,text,EToast2.LENGTH_SHORT).show();
    }

    public static void show(int resId){
        if(instance.mActivity != null&&instance != null)
            Toast.makeText(instance.mActivity,resId,EToast2.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if(instance != null)
            instance.mActivity = activity;
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if(instance != null)
            instance.mActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if(instance != null)
            instance.mActivity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if(activity == mActivity)
            mActivity = null;
    }

    /**
     * 检查通知栏权限有没有开启
     */
    public static boolean isNotificationEnabled(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).areNotificationsEnabled();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;
            try {
                Class<?> appOpsClass = Class.forName(AppOpsManager.class.getName());
                Method checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
                Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
                int value = (Integer) opPostNotificationValue.get(Integer.class);
                return (Integer) checkOpNoThrowMethod.invoke(appOps, value, uid, pkg) == 0;
            } catch (NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException | RuntimeException | ClassNotFoundException ignored) {
                return true;
            }
        } else {
            return true;
        }
    }
}

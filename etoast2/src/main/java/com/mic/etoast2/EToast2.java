package com.mic.etoast2;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.mic.permission.OpPermissionUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Author: Blincheng.
 * Date: 2017/6/30.
 * Description:EToast2.0
 */

public class EToast2 {

    private WindowManager manger;
    private Long time = 2000L;
    private static View contentView;
    private WindowManager.LayoutParams params;
    private static Timer timer;
    private Toast toast;
    private static Toast oldToast;
    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;
    private static Handler handler;
    private CharSequence text;

    private EToast2(Context context, CharSequence text, int HIDE_DELAY){
        manger = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        this.text = text;

        if(HIDE_DELAY == EToast2.LENGTH_SHORT)
            this.time = 2000L;
        else if(HIDE_DELAY == EToast2.LENGTH_LONG)
            this.time = 3500L;

        if(oldToast == null){
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            contentView = toast.getView();

            params = new WindowManager.LayoutParams();
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.format = PixelFormat.TRANSLUCENT;
            params.windowAnimations = -1;
            params.setTitle("EToast2");
            params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
            params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
            params.y = 200;
            //M的用户不需要悬浮窗权限即可实现全局弹窗
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                if(OpPermissionUtils.checkPermission(context)){
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                    }else {
                        params.type = WindowManager.LayoutParams.TYPE_PHONE;
                    }
                }
            }else{
                params.type = WindowManager.LayoutParams.TYPE_TOAST;
            }
        }
        if(handler == null){
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    EToast2.this.cancel();
                }
            };
        }
    }

    public static EToast2 makeText(Context context, CharSequence text, int HIDE_DELAY){
        return new EToast2(context, text, HIDE_DELAY);
    }

    public static EToast2 makeText(Context context, int resId, int HIDE_DELAY) {
        return makeText(context,context.getText(resId).toString(),HIDE_DELAY);
    }

    public void show(){
        if(oldToast == null){
            oldToast = toast;
            manger.addView(contentView, params);
            timer = new Timer();
        }else{
            timer.cancel();
            oldToast.setText(text);
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, time);
    }

    public void cancel(){
        try {
            manger.removeView(contentView);
        } catch (IllegalArgumentException e) {
            //这边由于上下文被销毁后removeView可能会抛出IllegalArgumentException
            //暂时这么处理，因为EToast2是轻量级的，不想和Context上下文的生命周期绑定在一块儿
            //其实如果真的想这么做，可以参考博文2的第一种实现方式，添加一个空的fragment来做生命周期绑定
        }
        timer.cancel();
        oldToast.cancel();
        timer = null;
        toast = null;
        oldToast = null;
        contentView = null;
        handler = null;
    }
    public void setText(CharSequence s){
        toast.setText(s);
    }
}
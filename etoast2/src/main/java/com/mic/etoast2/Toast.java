package com.mic.etoast2;

import android.app.Activity;
import android.content.Context;

/**
 * Author: Blincheng.
 * Date: 2017/6/30.
 * Description:
 */

public class Toast {
    private Object mToast;
    private Toast(Context context, CharSequence message, int duration) {
        if (EToastUtils.isNotificationEnabled(context)) {
            mToast = android.widget.Toast.makeText(context, message, duration);
        } else {
            if(context instanceof Activity){
                mToast = EToast2.makeText(context, message, duration);
            }else{
                //当没有通知权限，并且context是Application时，调用EToastUtils获取当前Activity
                if(EToastUtils.init().getActivity() != null)
                    mToast = EToast2.makeText(EToastUtils.init().getActivity(), message, duration);
            }
        }
    }
    private Toast(Context context, int resId, int duration) {
        if (EToastUtils.isNotificationEnabled(context)) {
            mToast = android.widget.Toast.makeText(context, resId, duration);
        } else {
            if(context instanceof Activity){
                mToast = EToast2.makeText(context, resId, duration);
            }else{
                //当没有通知权限，并且context是Application时，调用EToastUtils获取当前Activity
                if(EToastUtils.init().getActivity() != null)
                    mToast = EToast2.makeText(EToastUtils.init().getActivity(), resId, duration);
            }
        }
    }

    public static Toast makeText(Context context, CharSequence message, int duration) {
        return new Toast(context,message,duration);
    }


    public static Toast makeText(Context context, int resId, int duration) {
        return new Toast(context,resId,duration);
    }

    public void show() {
        if(mToast instanceof EToast2){
            ((EToast2) mToast).show();
        }else if(mToast instanceof android.widget.Toast){
            ((android.widget.Toast) mToast).show();
        }
    }
    public void cancel(){
        if(mToast instanceof EToast2){
            ((EToast2) mToast).cancel();
        }else if(mToast instanceof android.widget.Toast){
            ((android.widget.Toast) mToast).cancel();
        }
    }
    public void setText(CharSequence s){
        if(mToast instanceof EToast2){
            ((EToast2) mToast).setText(s);
        }else if(mToast instanceof android.widget.Toast){
            ((android.widget.Toast) mToast).setText(s);
        }
    }
}
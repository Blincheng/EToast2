package com.mic.etoast2;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Author: Blincheng.
 * Date: 2017/6/30.
 * Description:
 */

public class Toast {
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static int checkNotification = 0;
    private Object mToast;

    private Toast(Context context, String message, int duration) {
        if (context instanceof Application)
            checkNotification = 0;
        else
            checkNotification = isNotificationEnabled(context) ? 0 : 1;
        if (checkNotification == 1) {
            mToast = EToast2.makeText(context, message, duration);
        } else {
            mToast = android.widget.Toast.makeText(context, message, duration);
        }
    }

    private Toast(Context context, int resId, int duration) {
        if (context instanceof Application)
            checkNotification = 0;
        else
            checkNotification = isNotificationEnabled(context) ? 0 : 1;
        if (checkNotification == 1 && context instanceof Activity) {
            mToast = EToast2.makeText(context, resId, duration);
        } else {
            mToast = android.widget.Toast.makeText(context, resId, duration);
        }
    }

    public static Toast makeText(Context context, String message, int duration) {
        return new Toast(context, message, duration);
    }

    public static Toast makeText(Context context, int resId, int duration) {
        return new Toast(context, resId, duration);
    }

    public void show() {
        if (mToast instanceof EToast2) {
            ((EToast2) mToast).show();
        } else if (mToast instanceof android.widget.Toast) {
            ((android.widget.Toast) mToast).show();
        }
    }

    public void cancel() {
        if (mToast instanceof EToast2) {
            ((EToast2) mToast).cancel();
        } else if (mToast instanceof android.widget.Toast) {
            ((android.widget.Toast) mToast).cancel();
        }
    }

    public void setText(CharSequence s) {
        if (mToast instanceof EToast2) {
            ((EToast2) mToast).setText(s);
        } else if (mToast instanceof android.widget.Toast) {
            ((android.widget.Toast) mToast).setText(s);
        }
    }

    /**
     * 用来判断是否开启通知权限
     */
    private static boolean isNotificationEnabled(Context context) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        return notificationManagerCompat.areNotificationsEnabled();
    }
}
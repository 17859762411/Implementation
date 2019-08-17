package com.android.lvtong.implementationlibary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * @author 22939
 */
public class ToastUtils {

    /**
     * 全局吐司
     */
    private static Toast mToast;

    /**
     * 短时间弹出吐司（不会出现多次点击导致Toast不断弹出）
     *
     * @param context 上下文
     * @param text    需要显示的文本
     */
    public static void showShort(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    /**
     * 长时间弹出吐司（不会出现多次点击导致Toast不断弹出）
     *
     * @param context 上下文
     * @param text    需要显示的文本
     */
    public static void showLong(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_LONG);
    }

    /**
     * 根据传入的显示时长类型弹出吐丝（不会出现多次点击导致Toast不断弹出）
     *
     * @param context  上下文
     * @param text     需要显示的文本
     * @param duration 显示时长类型
     */
    @SuppressLint("ShowToast")
    private static void show(Context context, CharSequence text, int duration) {
        if (mToast != null) {
            mToast.setText(text);
            mToast.setDuration(duration);
        } else {
            mToast = Toast.makeText(context, text, duration);
        }
        mToast.show();
    }
}

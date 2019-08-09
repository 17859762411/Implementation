package com.android.lvtong.implementationlibary;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;

/**
 * @author 22939
 */
public class DialogUtil {
    /**all*/
    public static Dialog showDialog(Context context, Boolean canceledOnTouchOutside, Boolean cancelable, String title,
            String message, CustomDialog.Gravity_Message gravity, String positiveButtonText, String negativeButtonText,
            CustomDialog.OnCustomClickedListener onCustomClickedListener) {
        if (TextUtils.isEmpty(title)){
            title = "";
        }
        if (TextUtils.isEmpty(message)){
            message = "";
        }
        CustomDialog.Builder builder = new CustomDialog.Builder(context, canceledOnTouchOutside, cancelable, title, message, gravity, positiveButtonText,
                                                                negativeButtonText, onCustomClickedListener);
        CustomDialog customDialog = builder.create();
        customDialog.show();
        return customDialog;
    }
    /**不设置canceledOnTouchOutside cancelable*/
    public static Dialog showDialog(Context context,String title, String message, CustomDialog.Gravity_Message gravity,
            String positiveButtonText, String negativeButtonText,
            CustomDialog.OnCustomClickedListener onCustomClickedListener) {
        return showDialog(context, false, false, title, message, gravity, positiveButtonText,
                          negativeButtonText, onCustomClickedListener);
    }
    /**只需要title message*/
    public static Dialog showDialog(Context context, String title, String message,
            CustomDialog.OnCustomClickedListener onCustomClickedListener) {
        return showDialog(context, false, false,
                          title, message, CustomDialog.Gravity_Message.CENTER,
                          null, null, onCustomClickedListener);
    }
    /**只需要title message gravity*/
    public static Dialog showDialog(Context context, String title, String message,
            CustomDialog.Gravity_Message gravity,
            CustomDialog.OnCustomClickedListener onCustomClickedListener) {
        return showDialog(context, false, false,
                          title, message, gravity,
                          null, null, onCustomClickedListener);
    }
}

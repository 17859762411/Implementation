package com.android.lvtong.implementationlibary;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

/**
 * @author 22939 自定义dialog 显示title、message和两个button
 */
public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {

        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private Gravity_Message gravity;
        private Boolean canceledOnTouchOutside;
        private Boolean cancelable;
        private OnCustomClickedListener onCustomClickedListener;

        /**
         * @param context                 上下文
         * @param title                   标题
         * @param message                 内容
         * @param gravity                 内容的对齐方式
         * @param canceledOnTouchOutside  点击外部是否可取消
         * @param cancelable              是否可以取消
         * @param positiveButtonText      确定按钮文本
         * @param negativeButtonText      取消按钮文本
         * @param onCustomClickedListener 监听器
         */
        public Builder(Context context, Boolean canceledOnTouchOutside, Boolean cancelable, String title,
                String message,@NonNull Gravity_Message gravity, String positiveButtonText, String negativeButtonText,
                OnCustomClickedListener onCustomClickedListener) {
            super();
            this.context = context;

            this.canceledOnTouchOutside = canceledOnTouchOutside;
            this.cancelable = cancelable;

            this.title = title;
            this.message = message;
            this.gravity = gravity;
            this.positiveButtonText = positiveButtonText;
            this.negativeButtonText = negativeButtonText;
            this.onCustomClickedListener = onCustomClickedListener;
        }

        public CustomDialog create() {
            final CustomDialog dialog = new CustomDialog(context, R.style.TwoButtonDialog);
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            dialog.setCancelable(cancelable);

            View layout = View.inflate(context, R.layout.dialog_util, null);
            //设置title
            TextView tvTitle = layout.findViewById(R.id.tv_title);
            if (TextUtils.isEmpty(title)) {
                tvTitle.setVisibility(View.GONE);
            } else {
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText(title);
            }

            //设置message
            TextView tvMessage = layout.findViewById(R.id.tv_message);
            if (TextUtils.isEmpty(message)) {
                tvMessage.setVisibility(View.GONE);
            } else {
                tvMessage.setVisibility(View.VISIBLE);
                tvMessage.setText(message);
            }
            try {
                switch (gravity) {
                    case LEFT:
                        tvMessage.setGravity(Gravity.START);
                        break;
                    case CENTER:
                        tvMessage.setGravity(Gravity.CENTER);
                        break;
                    case RIGHT:
                        tvMessage.setGravity(Gravity.END);
                        break;
                    default:
                }
            }catch (NullPointerException npe){
                tvMessage.setGravity(Gravity.CENTER);
            }


            //设置dialog大小和对齐方式
            Window window = dialog.getWindow();
            assert window != null;
            window.setGravity(Gravity.CENTER);
            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //填充dialog的view
            dialog.setContentView(layout);

            // 设置确定按钮的文字以及点击事件
            Button btnRight = layout.findViewById(R.id.btn_right);
            if (TextUtils.isEmpty(positiveButtonText)) {
                btnRight.setText("确定");
            } else {
                btnRight.setText(positiveButtonText);
            }
            btnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onCustomClickedListener.onPositiveButtonClicked(dialog);
                    dialog.dismiss();
                }
            });

            // 设置取消按钮
            Button btnCancel = layout.findViewById(R.id.btn_cancel);
            if (TextUtils.isEmpty(negativeButtonText)) {
                btnCancel.setText("取消");
            } else {
                btnCancel.setText(negativeButtonText);
            }
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCustomClickedListener.onNegativeButtonClicked(dialog);
                    dialog.dismiss();
                }
            });

            return dialog;
        }
    }

    /** message对齐方式枚举 */
    public enum Gravity_Message {
        /*居中*/
        CENTER,
        /*左对齐*/
        LEFT,
        /*右对齐*/
        RIGHT
    }

    /** 点击事件接口 */
    public interface OnCustomClickedListener {

        void onPositiveButtonClicked(CustomDialog dialog);

        void onNegativeButtonClicked(CustomDialog dialog);
    }
}

package com.android.lvtong.implementationlibary;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author 22939
 * @date 2019/8/15 16:33
 */
public class DialogOption extends Dialog {

    @Override
    public void show() {
        if (noEmpty) {
            super.show();
        }
    }

    private static ConcreteBuilder builder;
    private TextView tvTitle;
    private ImageView ivIcon;
    private TextView tvMessage;
    private TextView tvButtonLeft;
    private TextView tvButtonRight;
    private ImageView viewLine;
    private LinearLayout llButton;
    private LinearLayout llText;
    public Boolean noEmpty = false;

    private DialogOption(DialogParams p) {
        //设置没有标题的Dialog风格
        super(p.context, R.style.NoTitleDialog);

        View contentView = LayoutInflater.from(p.context)
                                         .inflate(R.layout.dialog_build, null);
        setContentView(contentView);

        tvTitle = contentView.findViewById(R.id.tv_title);
        ivIcon = contentView.findViewById(R.id.iv_icon);
        tvMessage = contentView.findViewById(R.id.tv_message);
        tvButtonLeft = contentView.findViewById(R.id.tv_button_left);
        tvButtonRight = contentView.findViewById(R.id.tv_button_right);
        viewLine = contentView.findViewById(R.id.view_line);
        llButton = contentView.findViewById(R.id.ll_button);
        llText = contentView.findViewById(R.id.ll_text);

        //控件默认隐藏
        tvTitle.setVisibility(View.GONE);
        viewLine.setVisibility(View.GONE);
        ivIcon.setVisibility(View.GONE);
        tvMessage.setVisibility(View.GONE);
        tvButtonLeft.setVisibility(View.GONE);
        tvButtonRight.setVisibility(View.GONE);
        llButton.setVisibility(View.GONE);
        llText.setVisibility(View.GONE);
        //构建Dialog
        setTitleText(p.title);
        setTitleTextSize(p.titleSizeSp);
        setImageResource(p.imageResource);
        setImageWidth(p.imageWidth);
        setImageHeight(p.imageHeight);
        setTvMessage(p.message1);
        setTvMessageGravity(p.message1Gravity);
        setCancelableFlag(p.isCanCancel);
        setLeftText(p.leftButtonText, p.leftListener);
        setLeftBtColor(p.leftBtColor);
        setRightText(p.rightButtonText, p.rightListener);
        setRightBtColor(p.rightBtColor);
    }

    /**
     * 设置标题
     *
     * @param title 标题文字
     */
    private void setTitleText(String title) {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
        llText.setVisibility(View.VISIBLE);
        noEmpty = true;
    }

    /**
     * 设置标题大小
     *
     * @param sp 字体大小
     */
    private void setTitleTextSize(int sp) {
        if (sp <= 0) {
            return;
        }
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
    }

    /**
     * 设置图片资源
     *
     * @param imageResource 图片资源
     */
    private void setImageResource(int imageResource) {
        if (imageResource == 0) {
            return;
        }
        ivIcon.setVisibility(View.VISIBLE);
        llText.setVisibility(View.VISIBLE);
        ivIcon.setImageResource(imageResource);
        noEmpty = true;
    }

    /**
     * 设置图片宽度,单位dp
     *
     * @param imageWidth 图片宽度，单位dp
     */
    private void setImageWidth(int imageWidth) {
        if (imageWidth == 0) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = ivIcon.getLayoutParams();
        layoutParams.width = ScreenUtil.dp2Px(imageWidth);
        ivIcon.setLayoutParams(layoutParams);
    }

    /**
     * 设置图片高度,单位dp
     *
     * @param imageHeight 图片高度，单位dp
     */
    private void setImageHeight(int imageHeight) {
        if (imageHeight == 0) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = ivIcon.getLayoutParams();
        layoutParams.height = ScreenUtil.dp2Px(imageHeight);
        ivIcon.setLayoutParams(layoutParams);
    }

    /**
     * 设置Message的内容
     *
     * @param text 提示文字内容
     */
    private void setTvMessage(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        tvMessage.setVisibility(View.VISIBLE);
        llText.setVisibility(View.VISIBLE);
        noEmpty = true;
        tvMessage.setText(text);
    }

    private void setTvMessageGravity(int gravity) {
        tvMessage.setGravity(gravity);
    }

    private void setCancelableFlag(boolean cancelFlag) {
        this.setCancelable(cancelFlag);
    }

    /**
     * 设置左边Button内容
     *
     * @param text        文字内容
     * @param clickLister 点击监听
     */
    private void setLeftText(String text, final ConcreteBuilder.ButtonClickLister clickLister) {
        if (TextUtils.isEmpty(text) || clickLister == null) {
            return;
        }
        //有Button时显示底部Button布局
        tvButtonLeft.setVisibility(View.VISIBLE);
        llButton.setVisibility(View.VISIBLE);
        noEmpty = true;
        tvButtonLeft.setText(text);
        tvButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLister.onClick(DialogOption.this);
            }
        });
    }

    /**
     * 设置左边按钮字体颜色
     *
     * @param color 颜色的属性id
     */
    private void setLeftBtColor(int color) {
        if (color == 0) {
            return;
        }
        tvButtonLeft.setTextColor(color);
    }

    /**
     * 设置右边Button内容
     *
     * @param text        文字内容
     * @param clickLister 点击监听
     */
    private void setRightText(String text, final ConcreteBuilder.ButtonClickLister clickLister) {
        if (TextUtils.isEmpty(text) || clickLister == null) {
            return;
        }
        //分割线和右边Button绑定
        //有Button时显示底部Button布局
        viewLine.setVisibility(View.VISIBLE);
        tvButtonRight.setVisibility(View.VISIBLE);
        llButton.setVisibility(View.VISIBLE);
        noEmpty = true;
        tvButtonRight.setText(text);
        tvButtonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLister.onClick(DialogOption.this);
            }
        });
    }

    /**
     * 设置右Button字体颜色
     *
     * @param color 颜色的属性id
     */
    private void setRightBtColor(int color) {
        if (color == 0) {
            return;
        }
        tvButtonRight.setTextColor(color);
    }

    /**
     * 初始化Builder
     *
     * @param context 上下文
     */
    public static ConcreteBuilder with(Context context) {
        if (builder == null) {
            builder = new ConcreteBuilder(context);
        }
        return builder;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (builder != null) {
            builder.clear();
            builder = null;
        }
    }

    /**
     * ConcreteBuilder
     */
    public static class ConcreteBuilder {

        private DialogParams p;

        ConcreteBuilder(Context context) {
            p = new DialogParams();
            p.context = context;
        }

        public ConcreteBuilder title(String text) {
            p.title = text;
            return builder;
        }

        public ConcreteBuilder titleSize(int spSize) {
            p.titleSizeSp = spSize;
            return builder;
        }

        public ConcreteBuilder imageResource(int imageResource) {
            p.imageResource = imageResource;
            return builder;
        }

        public ConcreteBuilder imageWidth(int imageWidth) {
            p.imageWidth = imageWidth;
            return builder;
        }

        public ConcreteBuilder imageHeight(int imageHeight) {
            p.imageHeight = imageHeight;
            return builder;
        }

        public ConcreteBuilder message(String text) {
            p.message1 = text;
            return builder;
        }

        public ConcreteBuilder messageGravity(int gravity) {
            p.message1Gravity = gravity;
            return builder;
        }

        public ConcreteBuilder canCancel(boolean isCanCancel) {
            p.isCanCancel = isCanCancel;
            return builder;
        }

        public ConcreteBuilder leftBt(String text, ButtonClickLister lister) {
            p.leftButtonText = text;
            p.leftListener = lister;
            return builder;
        }

        public ConcreteBuilder leftBtColor(int color) {
            p.leftBtColor = color;
            return builder;
        }

        public ConcreteBuilder rightBtColor(int color) {
            p.rightBtColor = color;
            return builder;
        }

        public ConcreteBuilder rightBt(String text, ButtonClickLister lister) {
            p.rightButtonText = text;
            p.rightListener = lister;
            return builder;
        }

        void clear() {
            p = null;
        }

        public DialogOption create() {
            if (!p.isCanCancel) {
                if (TextUtils.isEmpty(p.leftButtonText) && TextUtils.isEmpty(p.rightButtonText)) {
                    p.isCanCancel = true;
                }
            }
            return new DialogOption(p);
        }

        //按钮点击回调
        public interface ButtonClickLister {

            /**
             * 点击事件
             *
             * @param dialog dialog
             */
            void onClick(DialogOption dialog);
        }
    }

    /**
     * 属性封装
     */
    private static class DialogParams {

        private Context context;
        //标题
        private String title;
        //标题字体大小
        private int titleSizeSp;
        //图标资源
        private int imageResource;
        //图标宽
        private int imageWidth;
        //图标高
        private int imageHeight;
        //消息内容
        private String message1;
        //消息内容文字位置
        private int message1Gravity = Gravity.CENTER;
        //点击外部是否可以取消
        private boolean isCanCancel = true;
        //左边按钮内容
        private String leftButtonText;
        //左边按钮颜色
        private int leftBtColor;
        //左边点击事件
        private ConcreteBuilder.ButtonClickLister leftListener;
        //右边按钮内容
        private String rightButtonText;
        //右边边按钮颜色
        private int rightBtColor;
        //右边按钮点击事件
        private ConcreteBuilder.ButtonClickLister rightListener;
    }
}

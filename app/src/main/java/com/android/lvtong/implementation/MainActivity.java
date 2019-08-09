package com.android.lvtong.implementation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.lvtong.implementationlibary.CustomDialog;
import com.android.lvtong.implementationlibary.DialogUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        CustomDialog.OnCustomClickedListener onCustomClickedListener = new CustomDialog.OnCustomClickedListener() {
            @Override
            public void onPositiveButtonClicked(CustomDialog dialog) {
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT)
                     .show();
            }

            @Override
            public void onNegativeButtonClicked(CustomDialog dialog) {
                dialog.dismiss();
            }
        };
        DialogUtil.showDialog(this,
                              false, false,
                              "12313", "23123123", CustomDialog.Gravity_Message.LEFT,
                              "11111","222222",onCustomClickedListener);
        DialogUtil.showDialog(this,
                              null, "000000",null, onCustomClickedListener);
    }
}

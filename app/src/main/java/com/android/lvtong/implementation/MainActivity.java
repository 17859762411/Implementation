package com.android.lvtong.implementation;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.lvtong.implementationlibary.CustomDialog;
import com.android.lvtong.implementationlibary.DialogOption;
import com.android.lvtong.implementationlibary.DialogUtil;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 22939
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog.OnCustomClickedListener onCustomClickedListener =
                        new CustomDialog.OnCustomClickedListener() {
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

                DialogUtil.showDialog(MainActivity.this, null, "000000", null, onCustomClickedListener);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogOption.with(MainActivity.this)
                            .title("标题")
                            .canCancel(false)
                            .create()
                            .show();
            }
        });
    }

}

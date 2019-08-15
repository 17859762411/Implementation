package com.android.lvtong.implementationlibary;

import android.app.Application;
import android.content.Context;

/**
 * @author 22939
 * @date 2019/8/15 16:41
 */
public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
    }
}

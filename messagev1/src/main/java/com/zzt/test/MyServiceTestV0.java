package com.zzt.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class MyServiceTestV0 extends Service {
    public MyServiceTestV0() {
    }

    MyAidlTestImplV0 testImplV0;

    @Override
    public void onCreate() {
        super.onCreate();
        testImplV0 = new MyAidlTestImplV0();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return testImplV0;
    }
}
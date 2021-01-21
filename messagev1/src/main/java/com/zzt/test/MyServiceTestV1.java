package com.zzt.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyServiceTestV1 extends Service {
    public MyServiceTestV1() {
    }

    MyAidlTestImplV1 testImplV1;

    @Override
    public void onCreate() {
        super.onCreate();
        testImplV1 = new MyAidlTestImplV1();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return testImplV1;
    }
}
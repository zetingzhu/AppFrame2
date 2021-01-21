package com.zzt.optbinder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author: zeting
 * @date: 2021/1/4
 */
public class MyBinderService extends Service {
    private MyBinder myBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        //创建实例
        myBinder = new MyBinder();
    }

    @Override
    public IBinder onBind(Intent intent) {
        //返回自定义的binder对象
        return myBinder;
    }
}
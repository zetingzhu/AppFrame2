package com.zzt.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author: zeting
 * @date: 2021/1/4
 */
public class MyAidlService extends Service {

    private MyAidlImpl myAidlImpl;

    @Override
    public void onCreate() {
        super.onCreate();
        myAidlImpl = new MyAidlImpl();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("TAG", "AIDL  绑定成功：");
        return myAidlImpl;
    }
}

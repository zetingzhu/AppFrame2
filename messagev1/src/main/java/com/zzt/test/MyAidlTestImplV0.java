package com.zzt.test;

import android.os.RemoteException;
import android.util.Log;

/**
 * @author: zeting
 * @date: 2021/1/8
 */
public class MyAidlTestImplV0 extends IMyAidlTest.Stub {
    String TAG = MyAidlTestImplV0.class.getSimpleName();

    @Override
    public int add(int i1, int i2, int i3) throws RemoteException {
        Log.d(TAG, "调用数据 i1:" + i1 + " i2:" + i2 + " i3:" + i3 + " add:" + (i1 + i2 + i3));
        return i1 + i2 + i3;
    }
}

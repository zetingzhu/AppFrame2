package com.zzt.test;

import android.os.RemoteException;
import android.util.Log;

/**
 * @author: zeting
 * @date: 2021/1/8
 */
public class MyAidlTestImplV1 extends IMyAidlTestV1.Stub {
    String TAG = MyAidlTestImplV1.class.getSimpleName();

    @Override
    public int add(int i1, int i2) throws RemoteException {
        Log.d(TAG, "调用数据 i1:" + i1 + " i2:" + i2 + " add:" + (i1 + i2));
        return i1 + i2;
    }
}

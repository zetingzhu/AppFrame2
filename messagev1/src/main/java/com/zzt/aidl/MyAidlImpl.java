package com.zzt.aidl;

import android.os.RemoteException;
import android.util.Log;

import com.zzt.messagev1.IMyAidlV1;

/**
 * @author: zeting
 * @date: 2021/1/4
 */
public class MyAidlImpl extends IMyAidlV1.Stub {
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public void setDate(String s1, String s2) throws RemoteException {

    }

    @Override
    public int add(int i1, int i2) throws RemoteException {
        Log.d("TAG", "这里是处理另外一个应用add方法：i1:" + i1 + " i2:" + i2 + " 结果：" + (i1 + i2));
        return i1 + i2;
    }
}

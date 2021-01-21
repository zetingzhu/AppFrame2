package com.zzt.optbinder;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

/**
 * @author: zeting
 * @date: 2021/1/4
 */
public class MyBinder extends Binder {
    //标记方法的
    private static final int METHOD_ADD_CODE = 1001;
    //标识binder对象的
    private static final String DESCRIPTION = "not use aidl";

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        if (code == METHOD_ADD_CODE) {
            //验证一下binder
            data.enforceInterface(DESCRIPTION);
            //从parcel对象中读取参数
            int arg0 = data.readInt();
            int arg1 = data.readInt();
            //写入结果
            reply.writeInt(add(arg0, arg1));
            Log.d("TAG", "这里写入数据： 1:" + arg0 + " 2:" + arg1 + " 结果：" + (arg0 + arg1));
            return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    private int add(int arg0, int arg1) {
        return arg0 + arg1;
    }
}
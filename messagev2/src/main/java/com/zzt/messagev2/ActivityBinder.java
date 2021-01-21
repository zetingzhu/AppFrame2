package com.zzt.messagev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityBinder extends AppCompatActivity {
    TextView textView;
    Button button;

    private ServiceConnection serviceConnection;
    private IBinder binder;

    //以下两个参数要和server端保持一致
    //标记方法的（告知server端调用哪个方法）
    private static final int METHOD_ADD_CODE = 1001;
    //标识binder对象的
    private static final String DESCRIPTION = "not use aidl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里就代替aidl中的proxy来直接调用transact方法
                //先准备参数
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                data.writeInterfaceToken(DESCRIPTION);
                data.writeInt((int) (Math.random() * 1000));
                data.writeInt((int) (Math.random() * 1000));
                try {
                    //调用transact方法
                    binder.transact(METHOD_ADD_CODE, data, reply, 0);
                    //获得结果
                    int result = reply.readInt();
                    Log.d("onServiceConnected", "result = " + result);
                    textView.setText("返回结果：" + result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } finally {
                    data.recycle();
                    reply.recycle();
                }
            }
        });

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d("onServiceConnected", "onServiceConnected: connected success！");
                binder = service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                binder = null;
            }
        };
        Intent intentAidl = new Intent();
        intentAidl.setComponent(new ComponentName("com.zzt.messagev1", "com.zzt.optbinder.MyBinderService"));
        bindService(intentAidl, serviceConnection, BIND_AUTO_CREATE);
    }

}
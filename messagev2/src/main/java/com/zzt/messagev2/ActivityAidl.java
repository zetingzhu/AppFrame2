package com.zzt.messagev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zzt.messagev1.IMyAidlV1;

public class ActivityAidl extends AppCompatActivity {
    String TAG = ActivityAidl.class.getSimpleName();

    private ServiceConnection serviceConnection;
    private IMyAidlV1 myAidl;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        textView = findViewById(R.id.textView);
        findViewById(R.id.bind2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        myAidl = IMyAidlV1.Stub.asInterface(service);
                        Log.d(TAG, "AIDL 启动服务成功");
                        try {
                            int add = myAidl.add((int) (Math.random() * 1000), (int) (Math.random() * 1000));
                            Log.d(TAG, "这里是通过aidl 方法获取到的数据： " + add);
                            textView.setText(add);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        myAidl = null;
                    }
                };
                Intent intentAidl = new Intent();
                intentAidl.setComponent(new ComponentName("com.zzt.messagev1", "com.zzt.aidl.MyAidlService"));
                bindService(intentAidl, serviceConnection, BIND_AUTO_CREATE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
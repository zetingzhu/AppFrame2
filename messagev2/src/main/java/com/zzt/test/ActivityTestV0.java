package com.zzt.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zzt.messagev2.ActivityMessenger;
import com.zzt.messagev2.R;

public class ActivityTestV0 extends AppCompatActivity {
    String TAG = ActivityTestV0.class.getSimpleName();


    Button btn_test, btn_start, btn_end;
    TextView tv_test;
    private IMyAidlTest myAidlTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_v0);

        btn_test = findViewById(R.id.btn_test);
        btn_start = findViewById(R.id.btn_start);
        btn_end = findViewById(R.id.btn_end);
        tv_test = findViewById(R.id.tv_test);

        btn_start.setOnClickListener(v -> {
            Intent intentAidl = new Intent();
            intentAidl.setComponent(new ComponentName("com.zzt.messagev1", "com.zzt.test.MyServiceTestV0"));
            bindService(intentAidl, mConnection, BIND_AUTO_CREATE);
        });
        btn_end.setOnClickListener(v -> {
            unbindService(mConnection);
        });

        btn_test.setOnClickListener(v -> {
            try {
                int add = myAidlTest.add((int) (Math.random() * 1000), 2, 3);
                Log.d(TAG, "跨进程传递消息 V2 计算值 add:" + add);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

    }


    /**
     * 服务端Messenger对象，建立连接时获取，用来给服务端发消息
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 获取服务端Messenger对象
            Log.d(TAG, "跨进程传递消息 V2 service = " + service.getClass().getName());
            myAidlTest = IMyAidlTest.Stub.asInterface(service);
            Log.d(TAG, "跨进程传递消息 V2 myAidlTest = " + myAidlTest.getClass().getName());
            Log.d(TAG, "跨进程传递消息 V2 启动服务成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAidlTest = null;
            Log.d(TAG, "跨进程传递消息 V2 启动服务失败");
        }
    };

}

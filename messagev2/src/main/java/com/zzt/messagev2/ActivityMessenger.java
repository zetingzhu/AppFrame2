package com.zzt.messagev2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ActivityMessenger extends AppCompatActivity {
    String TAG = ActivityMessenger.class.getSimpleName();


    private TextView mNameTxt;
    /**
     * 客户端Messenger对象，用来接收服务端数据
     */
    private Messenger mClientMessenger = new Messenger(new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1000:
                    // 接收服务端数据
                    Bundle bundle = (Bundle) msg.obj;
                    mNameTxt.setText(bundle.getString("name"));
                    Log.d(TAG, "跨进程传递消息 接收到另外一个进程的消息：" + bundle.getString("name"));
                    break;
            }
            return false;
        }
    }));

    /**
     * 服务端Messenger对象，建立连接时获取，用来给服务端发消息
     */
    private Messenger mServerMessenger;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 获取服务端Messenger对象
            mServerMessenger = new Messenger(service);
            Log.d(TAG, "跨进程传递消息 启动服务成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServerMessenger = null;
            Log.d(TAG, "跨进程传递消息 启动服务失败");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        mNameTxt = (TextView) findViewById(R.id.name);

        // 绑定远端服务
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.zzt.messagev1", "com.zzt.messagev1.MsgerService"));
        bindService(intent, mConnection, BIND_AUTO_CREATE);

        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int number = (int) (Math.random() * 100);
                Bundle bundle = new Bundle();
                bundle.putString("vm2", String.format("进程MessageV2 发送消息：%s1", number));
                Message msg = Message.obtain();
                msg.what = 1000;
                msg.arg1 = number;
                msg.setData(bundle);
                msg.replyTo = mClientMessenger;

                // 给服务端发送消息
                if (mServerMessenger != null) {
                    try {
                        mServerMessenger.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindService(mConnection);
    }
}
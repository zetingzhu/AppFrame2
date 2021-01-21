package com.zzt.messagev1;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * @author: zeting
 * @date: 2021/1/4
 */
public class MsgerService extends Service {
    String TAG = MsgerService.class.getSimpleName();
    private Messenger mServerMessenger = new Messenger(new Handler(msg -> {
        // 接收客户端发过来的消息
        switch (msg.what) {
            case 1000:
                Log.e(TAG, "跨进程传递消息  接收消息：" + msg.arg1);
                Bundle data = msg.getData();
                Toast.makeText(getBaseContext(), "" + msg.arg1 + " \n " + data.getString("vm2"), Toast.LENGTH_SHORT).show();

                Message cMsg = Message.obtain();
                cMsg.what = msg.what;
                Bundle bundle = new Bundle();
                bundle.putString("name", String.format("进程MessageV1 发送消息：%s1", (int) (Math.random() * 100)));
                cMsg.obj = bundle;

                // 获取客户端的Messenger对象，需要客户端在发送消息时设置
                Messenger cMsger = msg.replyTo;
                try {
                    // 给客户端发送消息
                    cMsger.send(cMsg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
        return false;
    }));

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mServerMessenger.getBinder();
    }
}

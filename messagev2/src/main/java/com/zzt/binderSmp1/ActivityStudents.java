package com.zzt.binderSmp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zzt.messagev2.R;

public class ActivityStudents extends AppCompatActivity {
    String TAG = ActivityStudents.class.getSimpleName();
    TextView textView;
    Button button;
    private IMyAidlStudents mStub;

    private boolean isBind;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mStub = IMyAidlStudents.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "the onServiceDisconnected");
            mStub = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info1 = null;
                String info2 = null;
                String info3 = null;
                try {
                    info1 = mStub.inStudentInfo(new StudentInfo(1, "Jim", "090415", 18));
                    info2 = mStub.outStudentInfo(new StudentInfo(2, "Lida", "090416", 17));
                    info3 = mStub.inOutStudentInfo(new StudentInfo(3, "Tom", "090417", 16));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                textView.setText(info1 + "\n" + "===========line=========" + "\n" + info2 + "\n" +
                        "===========line=========" + "\n" + info3);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intentAidl = new Intent();
        intentAidl.setComponent(new ComponentName("com.zzt.messagev1", "com.zzt.binderSmp1.MyStudentService"));
        bindService(intentAidl, serviceConnection, BIND_AUTO_CREATE);

    }

}
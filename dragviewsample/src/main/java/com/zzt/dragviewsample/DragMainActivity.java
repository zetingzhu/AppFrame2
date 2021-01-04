package com.zzt.dragviewsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.zzt.dragviewsample.contentview.SqAddFloatView;
import com.zzt.dragviewsample.windows.SqWindowManagerFloatView;

public class DragMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_main);
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            new SqWindowManagerFloatView(DragMainActivity.this, R.mipmap.settings, R.mipmap.option).show();

            new SqAddFloatView(DragMainActivity.this, R.mipmap.cloud).show(DragMainActivity.this);
            return false;
        }
    });

    public void onClickView(View view) {

        startActivity(new Intent(DragMainActivity.this, ActivityV2.class));

    }
}
package com.zzt.messagev2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zzt.binderSmp1.ActivityStudents;
import com.zzt.binderbook.ActivityBook;
import com.zzt.mylibrary2.ActDemo;
import com.zzt.mylibrary2.DemosAdapter;
import com.zzt.test.ActivityTestV0;
import com.zzt.test.ActivityTestV1;

import java.util.Arrays;

public class ActivityMessageV2 extends AppCompatActivity {
    String TAG = ActivityMessageV2.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_v2);
        RecyclerView rv_list = findViewById(R.id.rv_list);

        ActDemo[] demoList = new ActDemo[]{
                new ActDemo("Messenger 多进程通信", "Messenger 使用", ActivityMessenger.class),
                new ActDemo("AIDL 多进程通信", "AIDL 使用", ActivityAidl.class),
                new ActDemo("Binder 多进程通信", "Binder 使用", ActivityBinder.class),
                new ActDemo("Binder 传递Student", "Binder 传递对象使用", ActivityStudents.class),
                new ActDemo("Binder 传递Book", "Binder 传递对象使用", ActivityBook.class),
                new ActDemo("AIDL 测试方法", "AIDL 测试方法", ActivityTestV0.class),
                new ActDemo("AIDL 测试复制接口", "AIDL 测试复制接口", ActivityTestV1.class),
        };

        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(new DemosAdapter(Arrays.asList(demoList)));

    }
}
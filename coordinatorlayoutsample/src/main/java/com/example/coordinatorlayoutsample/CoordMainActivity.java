package com.example.coordinatorlayoutsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zzt.mylibrary2.StartListView;

import java.util.ArrayList;
import java.util.List;

public class CoordMainActivity extends AppCompatActivity {
    StartListView start_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coord_main);

        start_list = findViewById(R.id.start_list);

        List<StartListView.DemoInfo> demoList = new ArrayList<>();
        demoList.add(start_list.new DemoInfo("带有recycleview darw", "带有recycleview darw", MainCorrdinatorActivity.class));
        demoList.add(start_list.new DemoInfo("上下联动", "", ActivityCoordV1.class));
        start_list.setDemos(demoList);

    }
}
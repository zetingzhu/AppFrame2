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
        demoList.add(start_list.new DemoInfo("测试1", "", ActCoordinatorD1.class));
        demoList.add(start_list.new DemoInfo("第三个view 绑定第一个一起滑动", "", ActCoordinatorD2.class));
        demoList.add(start_list.new DemoInfo("头部滚动折叠", "", ActCoordinatorD3.class));
        start_list.setDemos(demoList);

    }
}
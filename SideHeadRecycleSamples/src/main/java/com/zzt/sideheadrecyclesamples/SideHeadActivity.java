package com.zzt.sideheadrecyclesamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzt.sideheadrecyclesamples.adapter.CityListAdapter;
import com.zzt.sideheadrecyclesamples.adapter.InnerListener;
import com.zzt.sideheadrecyclesamples.adapter.decoration.DividerItemDecoration;
import com.zzt.sideheadrecyclesamples.adapter.decoration.SectionItemDecoration;
import com.zzt.sideheadrecyclesamples.entity.CityV1;
import com.zzt.sideheadrecyclesamples.view.SideIndexBar;

import java.util.List;

public class SideHeadActivity extends AppCompatActivity {

    private static final String TAG = SideHeadActivity.class.getSimpleName();

    RecyclerView mRecyclerView;
    Gson gson;
    private CityListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private SideIndexBar mIndexBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_head);

        mRecyclerView = findViewById(R.id.recyclerview);
        gson = new Gson();
        initData();
    }

    private void initData() {
        String data = GetJsonDataUtil.getJson(SideHeadActivity.this , "City.txt") ;
        List<CityV1> mList = gson.fromJson(data, new TypeToken<List<CityV1>>() {
        }.getType());

        mLayoutManager = new LinearLayoutManager(SideHeadActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new SectionItemDecoration(SideHeadActivity.this, mList), 0);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(SideHeadActivity.this), 1);
        mAdapter = new CityListAdapter(SideHeadActivity.this, mList);
        mAdapter.autoLocate(true);
        mAdapter.setInnerListener((position, data1) -> {

        });
        mAdapter.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mIndexBar = findViewById(R.id.cp_side_index_bar);
        mIndexBar.setOnIndexChangedListener((index, position) -> {
            Log.w(TAG, "点击：" + index + " , pos:" + position);
            //滚动RecyclerView到索引位置
            mAdapter.scrollToSection(index);
        });
    }
}
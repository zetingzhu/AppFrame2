package com.example.coordinatorlayoutsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityCoordV1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coord_v1);
        /**
         scroll：所有想滚动出屏幕的view都需要设置这个flag， 没有设置这个flag的view将被固定在屏幕顶部。
         enterAlways：这个flag让任意向下的滚动都会导致该view变为可见，启用快速“返回模式”。
         enterAlwaysCollapsed：假设你定义了一个最小高度（minHeight）同时enterAlways也定义了，
         那么view将在到达这个最小高度的时候开始显示，并且从这个时候开始慢慢展开，当滚动到顶部的时候展开完。
         exitUntilCollapsed：当你定义了一个minHeight，此布局将在滚动到达这个最小高度的时候折叠。
         snap：当一个滚动事件结束，如果视图是部分可见的，那么它将被滚动到收缩或展开。
         例如，如果视图只有底部25%显示，它将折叠。相反，如果它的底部75%可见，那么它将完全展开。
         */
    }
}
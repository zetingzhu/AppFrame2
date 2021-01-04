package com.zzt.motionlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zzt.mylibrary2.StartListView
import kotlinx.android.synthetic.main.activity_notion_main.*

class NotionMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notion_main)

        var demoList: MutableList<StartListView.DemoInfo> = mutableListOf()
        demoList.add(start_list.DemoInfo("移动1", "", ActivityMotionV0::class.java))
        demoList.add(
            start_list.DemoInfo(
                "ImageFilterView  位移渐变",
                "位移过程中切换两张图片",
                ActivityMotionV1::class.java
            )
        )
        demoList.add(
            start_list.DemoInfo(
                "ImageFilterView  位移灰度",
                "位移到下边图片变灰",
                ActivityMotionV2::class.java
            )
        )
        demoList.add(start_list.DemoInfo("Keyframe 使用", "位移加上中间的转换", ActivityMotionV3::class.java))
        demoList.add(
            start_list.DemoInfo(
                "Keyframe 使用2",
                "位移到中间旋转坊大安",
                ActivityMotionV4::class.java
            )
        )
        demoList.add(
            start_list.DemoInfo(
                "CoordinatorLayout",
                "MotionLayout 结合 CoordinatorLayout",
                ActivityMotionV5::class.java
            )
        )
        demoList.add(
            start_list.DemoInfo(
                "DrawaerLayout",
                "MotionLayout 结合 DrawaerLayout ",
                ActivityMotionV6::class.java
            )
        )
        start_list.setDemos(demoList)
    }
}
package com.zzt.motionlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActivityMotionV2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_v2)

        /**
        saturation : 0 = grayscale, 1 = original, 2 = hyper saturated (饱和度 0:灰度 1：原始 2: 超饱和)

        contrast : 1 = unchanged, 0 = gray, 2 = high contrast (对比度 1:无改变 0：灰色 2:高对比度)

        warmth : 1 = neutral, 2 = warm (red tint), 0.5 = cold (blue tint) (暖色 1:正常的 2：暖色调(偏红色的) 0.5: 冷色调（偏蓝色的）)

        crossfade (with app:altSrc) 渐入渐出
         */
    }
}
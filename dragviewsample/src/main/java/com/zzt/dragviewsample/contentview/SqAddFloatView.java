package com.zzt.dragviewsample.contentview;

/**
 * @author: zeting
 * @date: 2020/12/29
 */

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * @author zhuxiaoxin
 * 37悬浮窗基础view
 */
public class SqAddFloatView extends DragViewLayout {

    private RelativeLayout mFloatContainer;

    public SqAddFloatView(final Context context, final int floatImgId) {
        super(context);
        setClickable(true);
        final ImageView floatView = new ImageView(context);
        floatView.setImageResource(floatImgId);
        floatView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了悬浮球", Toast.LENGTH_SHORT).show();
            }
        });
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(floatView, params);
    }

    public void show(Activity activity) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        if(mFloatContainer == null) {
            mFloatContainer = new RelativeLayout(activity);
        }
        RelativeLayout.LayoutParams floatViewParams = new RelativeLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        floatViewParams.setMargins(0, (int) (mScreenHeight * 0.4), 0, 0);
        mFloatContainer.addView(this, floatViewParams);
        activity.addContentView(mFloatContainer, params);

    }
}

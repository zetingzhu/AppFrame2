package com.zzt.dragviewsample.windows;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author: zeting
 * @date: 2020/12/29
 * <p>
 * 悬浮布局
 */
public class SqWindowManagerFloatView extends DragViewLayout {


    public SqWindowManagerFloatView(final Context context, final int floatImgId , int ic1) {
        super(context);
        setClickable(true);

        final ImageView floatViewIc1 = new ImageView(context);
        floatViewIc1.setImageResource(ic1);
        floatViewIc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了悬浮球", Toast.LENGTH_SHORT).show();
            }
        });
        LayoutParams paramsIc1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(floatViewIc1, paramsIc1);

        final ImageView floatView = new ImageView(context);
        floatView.setImageResource(floatImgId);
        floatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了悬浮球", Toast.LENGTH_SHORT).show();
            }
        });
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(floatView, params);

    }
}

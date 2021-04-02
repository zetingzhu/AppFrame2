package com.example.coordinatorlayoutsample;

import android.content.Context;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.appbar.AppBarLayout;

/**
 * @author: zeting
 * @date: 2020/11/30
 * 任务中心新手任务改版气泡
 */
public class NoviceBehavior22 extends AppBarLayout.ScrollingViewBehavior {
    private static final String TAG = NoviceBehavior22.class.getSimpleName();
    private int mTempTopBottomOffset;
    private View ll_vv1;

    public NoviceBehavior22(Context context) {
        init(context);
    }


    public NoviceBehavior22(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        View view = dependency.findViewById(R.id.ll_vv1);
        if (view != null) {
            ll_vv1 = view;
            return true;
        }
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        offsetChildAsNeeded(parent, child, dependency);
        return false;
    }

    private void offsetChildAsNeeded(CoordinatorLayout parent, View child, View dependency) {
        final CoordinatorLayout.Behavior behavior =
                ((CoordinatorLayout.LayoutParams) dependency.getLayoutParams()).getBehavior();
        if (behavior instanceof MyBehavior) {
            Log.d(TAG, " -- 底部信息： " + ll_vv1.getBottom() + " d:" + dependency.getTop() + "  c:" + child.getTop());

            if ((Math.abs(dependency.getTop()) + dependency.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_50dp)) >= ll_vv1.getBottom()) {
                child.setAlpha(1);
            } else {
                child.setAlpha(0);
            }
        }
    }
}

package com.example.coordinatorlayoutsample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.appbar.AppBarLayout;

/**
 * @author: zeting
 * @date: 2020/11/30
 * 任务中心新手任务改版气泡
 */
public class NoviceBehavior extends AppBarLayout.ScrollingViewBehavior {

    private int mTempTopBottomOffset;
    private View ll_task_tab;

    public NoviceBehavior(Context context) {
        init(context);
    }


    public NoviceBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        layoutChild(parent, child, layoutDirection);
        ViewCompat.offsetTopAndBottom(child, mTempTopBottomOffset);
        return true;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        View view = dependency.findViewById(R.id.ll_task_tab);
        if (view != null) {
            ll_task_tab = view;
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
            ViewCompat.offsetTopAndBottom(child, dependency.getTop()   + ll_task_tab.getBottom() - child.getTop()
            );
            mTempTopBottomOffset = child.getTop();
        }
    }
}

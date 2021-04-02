package com.example.coordinatorlayoutsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.widget.LinearLayout;

public class ActCoordinatorD2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_d2);
        LinearLayout ll_top = findViewById(R.id.ll_top);

        MyBehavior behavior = (MyBehavior) ((CoordinatorLayout.LayoutParams) ll_top.getLayoutParams()).getBehavior();
        behavior.setExtraOffset(getResources().getDimensionPixelOffset(R.dimen.margin_50dp));
    }
}
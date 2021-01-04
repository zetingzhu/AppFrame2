package com.zzt.drawerlayoutsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

public class DrawerLayoutActivity extends AppCompatActivity {
    String TAG = DrawerLayoutActivity.class.getSimpleName();
    DrawerLayout drawer_ly;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        drawer_ly = findViewById(R.id.drawer_ly);

        drawer_ly.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                Log.d(TAG, " -- onDrawerSlide -- " + slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                Log.d(TAG, " -- onDrawerOpened -- " + drawerView.getClass().getSimpleName());
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                Log.d(TAG, " -- onDrawerClosed -- " + drawerView.getClass().getSimpleName());
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Log.d(TAG, " -- onDrawerStateChanged -- " + newState);
            }
        });
    }

    public void onClikcView(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                drawer_ly.openDrawer(GravityCompat.START);
                break;
            case R.id.btn_2:
                drawer_ly.openDrawer(GravityCompat.END);
                break;
        }
    }
}
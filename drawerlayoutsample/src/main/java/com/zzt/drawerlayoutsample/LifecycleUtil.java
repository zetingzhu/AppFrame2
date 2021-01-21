package com.zzt.drawerlayoutsample;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author: zeting
 * @date: 2021/1/4
 */
public class LifecycleUtil implements LifecycleObserver {
    private static final String TAG = LifecycleUtil.class.getSimpleName();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.w(TAG, "Lifecycle onCreate: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.w(TAG, "Lifecycle onStart: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.w(TAG, "Lifecycle onResume: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.w(TAG, "Lifecycle onPause: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.w(TAG, "Lifecycle onStop: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.w(TAG, "Lifecycle onDestroy: ");
    }
}

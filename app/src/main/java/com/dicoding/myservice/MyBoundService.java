package com.dicoding.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyBoundService extends Service {

    private final String TAG = MyBoundService.class.getSimpleName();
    private final MyBinder mBinder = new MyBinder();
    private final long startTime = System.currentTimeMillis();



    CountDownTimer mTimer = new CountDownTimer(100000, 1000) {
        @Override
        public void onTick(long l) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            Log.d(TAG, "onTick : "+ elapsedTime);
        }

        @Override
        public void onFinish() {

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate :");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy :");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind :");
        mTimer.start();
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind :");
        mTimer.cancel();
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind :");
        super.onRebind(intent);
    }
    //class MyBinder
    class MyBinder extends Binder{
        MyBoundService getService(){
            return MyBoundService.this;
        }
    }
}

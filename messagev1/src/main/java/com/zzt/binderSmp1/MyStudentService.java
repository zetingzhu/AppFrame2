package com.zzt.binderSmp1;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.zzt.messagev1.MsgerService;

/**
 * @author: zeting
 * @date: 2021/1/5
 */
public class MyStudentService extends Service {
    String TAG = MyStudentService.class.getSimpleName();

    IMyAidlStudents.Stub mStub = new IMyAidlStudents.Stub() {
        @Override
        public int add(int arg1, int arg2) throws RemoteException {
            return arg1 + arg2;
        }

        @Override
        public String inStudentInfo(StudentInfo student) throws RemoteException {
            String msg = "table1" + "\n" + "----------------------------------------------" + "\n" + "|" +
                    " id " + "|" + " " +
                    "age " +
                    "|" + " name " + "|" + " className " + "|" + "\n" +
                    "----------------------------------------------" + "\n" + "|  " + student.getId() + " " +
                    "|  " + student
                    .getAge() + "  |  " + student.getName() + "   |     " + student.getClassName() + "   | " +
                    "\n" + "----------------------------------------------";
            return msg;
        }

        @Override
        public String outStudentInfo(StudentInfo student) throws RemoteException {
//            student.setClassName("090412");
//            student.setName("Tom2");

//            String msg = "Id = " + student.getId() + " age = " + student.getAge() + " ClassName = " +
//                    student.getClassName() + " Name = " + student.getName();
            String msg = "table2" + "\n" + "----------------------------------------------" + "\n" + "|" +
                    " id " + "|" + " " +
                    "age " +
                    "|" + " name " + "|" + " className " + "|" + "\n" +
                    "----------------------------------------------" + "\n" + "|  " + student.getId() + " " +
                    "|  " + student
                    .getAge() + "  |  " + student.getName() + "   |     " + student.getClassName() + "   | " +
                    "\n" + "----------------------------------------------";
            return msg;
        }

        public String inOutStudentInfo(StudentInfo student) throws RemoteException {
            student.setClassName("090411");
            student.setAge(22);
            String msg = "table3" + "\n" + "----------------------------------------------" + "\n" + "|" +
                    " id " + "|" + " " +
                    "age " +
                    "|" + " name " + "|" + " className " + "|" + "\n" +
                    "----------------------------------------------" + "\n" + "|  " + student.getId() + " " +
                    "|  " + student
                    .getAge() + "  |  " + student.getName() + "   |     " + student.getClassName() + "   | " +
                    "\n" + "----------------------------------------------";

            return msg;
        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "the remote Process Name is ==>" + getCurProcessName(MyStudentService.this));
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "the remote onBind......");
        return mStub;
    }


    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG, "the remote onRebind......");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "the remote onUnbind......");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "the remote onDestroy......");
    }

    /**
     * get current process name
     *
     * @param context
     * @return
     */
    private String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
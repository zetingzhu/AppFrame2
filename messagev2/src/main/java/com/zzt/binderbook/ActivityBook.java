package com.zzt.binderbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zzt.messagev2.R;

import java.util.List;

public class ActivityBook extends AppCompatActivity {

    //由AIDL文件生成的Java类
    private BookManager mBookManager = null;

    //标志当前与服务端连接状况的布尔值，false为未连接，true为连接中
    private boolean mBound = false;

    //包含Book对象的list
    private List<Book> mBooks;
    private TextView tv_book;
    private int addCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        tv_book = findViewById(R.id.tv_book);
        findViewById(R.id.btn_book_add_in).setOnClickListener(v -> {
            addBookIn();
        });
        findViewById(R.id.btn_book_add_out).setOnClickListener(this::addBookOut);
        findViewById(R.id.btn_book_add_inout).setOnClickListener(this::addBookInout);

        findViewById(R.id.btn_query).setOnClickListener(v -> {
            queryList();
        });
    }

    private void addBookIn() {
        //如果与服务端的连接处于未连接状态，则尝试连接
        if (!mBound) {
            attemptToBindService();
            Toast.makeText(ActivityBook.this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mBookManager == null) return;

        Book book = new Book();
        book.setName("In APP " + addCount++);
        book.setPrice(1111);
        try {
            Log.i(getLocalClassName(), "addBookIn book source:" + book.toString());
            Book returnBook = mBookManager.addBookIn(book);
            Log.w(getLocalClassName(), "addBookIn book:" + book.toString());
            Log.e(getLocalClassName(), "addBookIn return Book:" + returnBook.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addBookOut(View view) {
        if (!mBound) {
            attemptToBindService();
            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mBookManager == null) return;

        Book book = new Book();
        book.setName("Out APP " + addCount++);
        book.setPrice(2222);
        try {
            Log.i(getLocalClassName(), "addBookIn book source:" + book.toString());
            Book returnBook = mBookManager.addBookOut(book);
            Log.w(getLocalClassName(), "addBookIn book:" + book.toString());
            Log.e(getLocalClassName(), "addBookIn return Book:" + returnBook.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addBookInout(View view) {
        if (!mBound) {
            attemptToBindService();
            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mBookManager == null) return;

        Book book = new Book();
        book.setName("Inout APP " + addCount++);
        book.setPrice(3333);
        try {
            Log.i(getLocalClassName(), "addBookIn book source:" + book.toString());
            Book returnBook = mBookManager.addBookInout(book);
            Log.w(getLocalClassName(), "addBookIn book:" + book.toString());
            Log.e(getLocalClassName(), "addBookIn return Book:" + returnBook.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 尝试与服务端建立连接
     */
    private void attemptToBindService() {
        Intent intent = new Intent();
        intent.setAction("com.zzt.book");
        intent.setComponent(new ComponentName("com.zzt.messagev1", "com.zzt.binderbook.BookService"));
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBound) {
            attemptToBindService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

    private void queryList() {
        if (mBookManager != null) {
            try {
                Log.e(getLocalClassName(), "总数" + mBookManager.getBookCount());
                mBooks = mBookManager.getBooks();
                Log.e(getLocalClassName(), mBooks.toString());
                runOnUiThread(() -> tv_book.setText(mBooks.toString()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(getLocalClassName(), "service connected");
            mBookManager = BookManager.Stub.asInterface(service);
            mBound = true;
            queryList();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(getLocalClassName(), "service disconnected");
            mBound = false;
        }
    };
}
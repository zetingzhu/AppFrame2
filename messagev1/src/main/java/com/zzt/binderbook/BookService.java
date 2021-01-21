package com.zzt.binderbook;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zeting
 * @date: 2021/1/5
 */
public class BookService extends Service {
    public final String TAG = this.getClass().getSimpleName();

    //包含Book对象的list
    private List<Book> mBooks = new ArrayList<>();

    BookManager.Stub bookManager = new BookManager.Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (this) {
                Log.e(TAG, "invoking getBooks() method , now the list is : " + mBooks.toString());
                if (mBooks != null) {
                    return mBooks;
                }
                return new ArrayList<>();
            }
        }

        @Override
        public Book getBook() throws RemoteException {
            return null;
        }

        @Override
        public int getBookCount() throws RemoteException {
            return mBooks != null ? mBooks.size() : 0;
        }

        @Override
        public void setBookPrice(Book book, int price) throws RemoteException {

        }

        @Override
        public void setBookName(Book book, String name) throws RemoteException {

        }

        @Override
        public Book addBookIn(Book book) throws RemoteException {
            synchronized (this) {
                if (mBooks == null) {
                    mBooks = new ArrayList<>();
                }
                if (book == null) {
                    Log.e(TAG, "Book is null in In");
                    book = new Book();
                }
                //尝试修改book的参数，主要是为了观察其到客户端的反馈
                book.setPrice(99999);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                //打印mBooks列表，观察客户端传过来的值
                Log.e(TAG, "invoking addBooks() method , now the list is : " + mBooks.toString());
            }
            return  book ;
        }

        @Override
        public Book addBookOut(Book book) throws RemoteException {
            synchronized (this) {
                if (mBooks == null) {
                    mBooks = new ArrayList<>();
                }
                if(book == null){
                    Log.e(TAG , "Book is null in Out");
                    book = new Book();
                }
                book.setPrice(88888);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                Log.e(TAG, "invoking addBooks() method , now the list is : " + mBooks.toString());
                return book;
            }
        }

        @Override
        public Book addBookInout(Book book) throws RemoteException {
            synchronized (this) {
                if (mBooks == null) {
                    mBooks = new ArrayList<>();
                }
                if(book == null){
                    Log.e(TAG , "Book is null in Inout");
                    book = new Book();
                }
                book.setPrice(77777);
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
                Log.e(TAG, "invoking addBooks() method , now the list is : " + mBooks.toString());
                return book;
            }
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book();
        book.setName("Android 开发");
        book.setPrice(100);
        mBooks.add(book);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return bookManager;
    }
}

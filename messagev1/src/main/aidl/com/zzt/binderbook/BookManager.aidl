// BookManager.aidl
//第二类AIDL文件的例子
// BookManager.aidl
package com.zzt.binderbook;
//导入所需要使用的非默认支持数据类型的包
import com.zzt.binderbook.Book;

interface BookManager {

    //所有的返回值前都不需要加任何东西，不管是什么数据类型
    List<Book> getBooks();
    Book getBook();
    int getBookCount();

    //传参时除了Java基本类型以及String，CharSequence之外的类型
    //都需要在前面加上定向tag，具体加什么量需而定
    void setBookPrice(in Book book , int price);
    void setBookName(in Book book , String name);
    Book  addBookIn(in Book book);
    Book  addBookOut(out Book book);
    Book  addBookInout(inout Book book);
}
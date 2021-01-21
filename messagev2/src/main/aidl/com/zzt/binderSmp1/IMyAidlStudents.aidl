package com.zzt.binderSmp1;

import com.zzt.binderSmp1.StudentInfo;
interface IMyAidlStudents {
    /**
     in：参数由客户端设置，或者理解成客户端传入参数值。
       out：参数由服务端设置，或者理解成由服务端返回值。
      inout：客户端输入端都可以设置，或者理解成可以双向通信。
    */
    int add(int arg1, int arg2);

    String inStudentInfo(in StudentInfo student);

    String outStudentInfo(out StudentInfo student);

    String inOutStudentInfo(inout StudentInfo student);

}
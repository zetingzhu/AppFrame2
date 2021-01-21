// IMyAidlV1.aidl
package com.zzt.messagev1;

// Declare any non-default types here with import statements

interface IMyAidlV1 {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,   double aDouble, String aString);



    void setDate(String s1 , String s2) ;


    int add(int i1 , int i2) ;

}
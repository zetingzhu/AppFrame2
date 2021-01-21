package com.zzt.binderSmp1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: zeting
 * @date: 2021/1/4
 */

public class StudentInfo implements Parcelable {
    private int id;
    private String name;
    private String className;
    private int age;

    public StudentInfo() {
    }

    public StudentInfo(int id, String name, String className, int age) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.age = age;
    }

    protected StudentInfo(Parcel in) {
        id = in.readInt();
        name = in.readString();
        className = in.readString();
        age = in.readInt();
    }

    public static final Creator<StudentInfo> CREATOR = new Creator<StudentInfo>() {
        @Override
        public StudentInfo createFromParcel(Parcel in) {
            return new StudentInfo(in);
        }

        @Override
        public StudentInfo[] newArray(int size) {
            return new StudentInfo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(className);
        dest.writeInt(age);
    }

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        className = in.readString();
        age = in.readInt();
    }

}
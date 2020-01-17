package com.example.androiddemo.activity_demo.bean

import android.os.Parcel
import android.os.Parcelable

class Person() : Parcelable {
    var username: String? = null
    var nickname: String? = null

    /**
     * 这里的读取顺序必须和下面的写入顺序一致，否则读取会错位
     */
    constructor(parcel: Parcel) : this() {
        username = parcel.readString()
        nickname = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(nickname)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Person(username=$username, nickname=$nickname)"
    }

    companion object CREATOR : Parcelable.Creator<Person> {

        /**
         * 从 parcel 中读取数据
         */
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        /**
         * 供外部类反序列化本类数组使用
         */
        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }


}
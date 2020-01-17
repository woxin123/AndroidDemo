package com.example.androiddemo.ipc_demo.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class User(var userId: Int = 0,
                var userName: String? = null,
                var isMale: Boolean = false): Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userId)
        parcel.writeString(userName)
        parcel.writeByte(if (isMale) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
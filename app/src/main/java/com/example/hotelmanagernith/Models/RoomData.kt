package com.example.hotelmanagernith.Models

import android.os.Parcel
import android.os.Parcelable

data class RoomData(
    var capacity:String?=null,
    var roomNo:String?=null,
//    var count: String?=null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
//        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(capacity)
        parcel.writeString(roomNo)
//        parcel.writeString(count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RoomData> {
        override fun createFromParcel(parcel: Parcel): RoomData {
            return RoomData(parcel)
        }

        override fun newArray(size: Int): Array<RoomData?> {
            return arrayOfNulls(size)
        }
    }
}
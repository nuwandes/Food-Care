package org.southasia.foodcare.vo.request

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.southasia.foodcare.vo.Meta
import org.southasia.foodcare.vo.MetaFoodCare
import java.io.Serializable

@Entity(tableName = "sign_up_request")

data class SignUpRequest(
    @Expose @field:SerializedName("meta")
    @Embedded(prefix = "meta")
    var meta: MetaFoodCare,
    @Expose @field:SerializedName("body")
    @Embedded(prefix = "body")
    var body: SignUpData

) : Serializable, Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    @ColumnInfo(name = "timestamp")
    var timestamp: Long = System.currentTimeMillis()

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(MetaFoodCare::class.java.classLoader)!!,
        parcel.readParcelable(SignUpData::class.java.classLoader)!!
    ) {
        id = parcel.readLong()
        timestamp = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(meta, flags)
        parcel.writeParcelable(body, flags)
        parcel.writeLong(id)
        parcel.writeLong(timestamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SignUpRequest> {
        override fun createFromParcel(parcel: Parcel): SignUpRequest {
            return SignUpRequest(parcel)
        }

        override fun newArray(size: Int): Array<SignUpRequest?> {
            return arrayOfNulls(size)
        }
    }
}

data class SignUpData(
    @Expose @field:SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String,
    @Expose @field:SerializedName("phone")
    @ColumnInfo(name = "phone")
    var phone: String,
    @Expose @field:SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String,
    @Expose @field:SerializedName("password")
    @ColumnInfo(name = "password")
    var password: String,
    @Expose @field:SerializedName("user_type")
    @ColumnInfo(name = "user_type")
    var user_type: String

) : Serializable, Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
        id = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SignUpData> {
        override fun createFromParcel(parcel: Parcel): SignUpData {
            return SignUpData(parcel)
        }

        override fun newArray(size: Int): Array<SignUpData?> {
            return arrayOfNulls(size)
        }
    }
}




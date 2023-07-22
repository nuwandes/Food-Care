package org.southasia.foodcare.vo

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "meta_new_food")
data class MetaFoodCare(
    @Expose @field:SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    var startTime: String?
) : Serializable, Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(startTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MetaFoodCare> {
        override fun createFromParcel(parcel: Parcel): MetaFoodCare {
            return MetaFoodCare(parcel)
        }

        override fun newArray(size: Int): Array<MetaFoodCare?> {
            return arrayOfNulls(size)
        }
    }


}

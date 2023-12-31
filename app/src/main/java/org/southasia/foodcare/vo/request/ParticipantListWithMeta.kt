package org.southasia.foodcare.vo.request

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.southasia.foodcare.vo.ParticipantListItem
import org.southasia.foodcare.vo.ParticipantListMeta
import java.io.Serializable


class ParticipantListWithMeta (
    @Expose @SerializedName("meta") val meta: ParticipantListMeta?,
    @Expose @SerializedName("body") val listRequest: ArrayList<ParticipantListItem?>?= null
) : Serializable, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(ParticipantListMeta::class.java.classLoader),
        parcel.createTypedArrayList(ParticipantListItem)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(meta, flags)
        parcel.writeTypedList(listRequest)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParticipantListWithMeta> {
        override fun createFromParcel(parcel: Parcel): ParticipantListWithMeta {
            return ParticipantListWithMeta(parcel)
        }

        override fun newArray(size: Int): Array<ParticipantListWithMeta?> {
            return arrayOfNulls(size)
        }
    }

}
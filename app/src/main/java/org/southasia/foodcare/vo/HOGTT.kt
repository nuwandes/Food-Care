package org.southasia.foodcare.vo

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import org.southasia.foodcare.BR
import java.io.Serializable


class HOGTT : BaseObservable(), Serializable {

    companion object {
        fun build(): HOGTT {
            val hb1Ac = HOGTT()
            hb1Ac.probeId = String()
            hb1Ac.value = String()
            hb1Ac.comment = String()
            hb1Ac.deviceId = String()
            return hb1Ac
        }
    }


    var value: String = String()
        set(value) {
            field = value
            notifyPropertyChanged(BR.value)
        }
        @Bindable get() = field


    var probeId: String = String()
        set(value) {
            field = value
            notifyPropertyChanged(BR.probeId)
        }
        @Bindable get() = field


    var comment: String = String()
        set(value) {
            field = value
            notifyPropertyChanged(BR.comment)
        }
        @Bindable get() = field


    var deviceId: String = String()
        set(value) {
            field = value
        }
        @Bindable get() = field
}

package org.southasia.foodcare.event

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import org.southasia.foodcare.vo.BloodTestData

class FBGRxBus private constructor() {
    private val relay: PublishRelay<BloodTestData>

    init {
        relay = PublishRelay.create()
    }

    fun post(fBgDto: BloodTestData) {
        relay.accept(fBgDto)
    }

    fun toObservable(): Observable<BloodTestData> {
        return relay
    }

    companion object {

        private var instance: FBGRxBus? = null

        @Synchronized
        fun getInstance(): FBGRxBus {
            if (instance == null) {
                instance = FBGRxBus()
            }
            return instance as FBGRxBus
        }
    }
}
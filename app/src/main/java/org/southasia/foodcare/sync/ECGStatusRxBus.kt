package org.southasia.foodcare.sync

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import org.southasia.foodcare.vo.ECGStatus


class ECGStatusRxBus {

    private val relay: PublishRelay<ECGStatusRequestResponse>

    init {
        relay = PublishRelay.create()
    }

    fun post(eventType: SyncResponseEventType, ecgStatus: ECGStatus) {
        relay.accept(ECGStatusRequestResponse(eventType, ecgStatus))
    }

    fun toObservable(): Observable<ECGStatusRequestResponse> {
        return relay
    }

    companion object {

        private var instance: ECGStatusRxBus? = null

        @Synchronized
        fun getInstance(): ECGStatusRxBus {
            if (instance == null) {
                instance = ECGStatusRxBus()
            }
            return instance as ECGStatusRxBus
        }
    }
}
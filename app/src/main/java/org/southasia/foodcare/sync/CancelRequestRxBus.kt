package org.southasia.foodcare.sync

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import org.southasia.foodcare.vo.request.CancelRequest

class CancelRequestRxBus {

    private val relay: PublishRelay<CancelRequestResponse>

    init {
        relay = PublishRelay.create()
    }

    fun post(eventType: SyncResponseEventType,cancelRequest: CancelRequest) {
        relay.accept(CancelRequestResponse(eventType, cancelRequest))
    }

    fun toObservable(): Observable<CancelRequestResponse> {
        return relay
    }

    companion object {

        private var instance: CancelRequestRxBus? = null

        @Synchronized
        fun getInstance(): CancelRequestRxBus {
            if (instance == null) {
                instance = CancelRequestRxBus()
            }
            return instance as CancelRequestRxBus
        }
    }
}

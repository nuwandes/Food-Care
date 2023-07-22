package org.southasia.foodcare.event

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import org.southasia.foodcare.vo.request.HouseholdRequest


class HouseholdRequestRxBus private constructor() {
    private val relay: PublishRelay<HouseholdRequest>

    init {
        relay = PublishRelay.create()
    }

    fun post(bitmap: HouseholdRequest) {
        relay.accept(bitmap)
    }

    fun toObservable(): Observable<HouseholdRequest> {
        return relay
    }

    companion object {

        private var instance: HouseholdRequestRxBus? = null

        @Synchronized
        fun getInstance(): HouseholdRequestRxBus {
            if (instance == null) {
                instance = HouseholdRequestRxBus()
            }
            return instance as HouseholdRequestRxBus
        }
    }
}
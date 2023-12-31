package org.southasia.foodcare.event

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import org.southasia.foodcare.vo.TriglyceridesDto

class TriglyceridesRxBus private constructor() {
    private val relay: PublishRelay<TriglyceridesDto>

    init {
        relay = PublishRelay.create()
    }

    fun post(triglyceridesDto: TriglyceridesDto) {
        relay.accept(triglyceridesDto)
    }

    fun toObservable(): Observable<TriglyceridesDto> {
        return relay
    }

    companion object {

        private var instance: TriglyceridesRxBus? = null

        @Synchronized
        fun getInstance(): TriglyceridesRxBus {
            if (instance == null) {
                instance = TriglyceridesRxBus()
            }
            return instance as TriglyceridesRxBus
        }
    }
}
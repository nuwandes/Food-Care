package org.southasia.foodcare.sync

import org.southasia.foodcare.vo.ECGStatus


class ECGStatusRequestResponse(
    val eventType: SyncResponseEventType,
    val ecgStatus: ECGStatus) {

}
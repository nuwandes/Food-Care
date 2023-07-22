package org.southasia.foodcare.sync

import org.southasia.foodcare.vo.request.SampleStorageRequest

class SyncSampleStorageRequestResponse(
    val eventType: SyncResponseEventType,
    val sampleStorageRequest: SampleStorageRequest
)
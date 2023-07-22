package org.southasia.foodcare.sync

import org.southasia.foodcare.vo.request.BloodPressureMetaRequest


class BloodPresureMetaRequestResponse(
    val eventType: SyncResponseEventType,
    val bloodPressureMetaRequest: BloodPressureMetaRequest
)
package org.southasia.foodcare.sync

import org.southasia.foodcare.vo.request.HouseholdRequestMeta

class SyncHouseholdrequestmetaResponse(
    val eventType: SyncResponseEventType,
    val householdRequestMeta: HouseholdRequestMeta
)
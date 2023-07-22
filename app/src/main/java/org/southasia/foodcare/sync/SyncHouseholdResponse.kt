package org.southasia.foodcare.sync

import org.southasia.foodcare.vo.request.HouseholdRequest

class SyncHouseholdResponse(val eventType: SyncResponseEventType, val household: HouseholdRequest)
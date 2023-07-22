package org.southasia.foodcare.sync

import org.southasia.foodcare.vo.request.HouseholdRequest

class SyncHouseholdRequestResponse(val eventType: SyncResponseEventType, val householdRequest: HouseholdRequest)
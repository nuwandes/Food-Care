package org.southasia.foodcare.sync

import org.southasia.foodcare.vo.request.Member

class SyncHouseholdMemberResponse(val eventType: SyncResponseEventType, val member: Member)
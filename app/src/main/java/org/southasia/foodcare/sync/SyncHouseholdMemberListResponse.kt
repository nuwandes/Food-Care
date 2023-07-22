package org.southasia.foodcare.sync

import org.southasia.foodcare.vo.request.Member

class SyncHouseholdMemberListResponse(val eventType: SyncResponseEventType, val member: List<Member>)
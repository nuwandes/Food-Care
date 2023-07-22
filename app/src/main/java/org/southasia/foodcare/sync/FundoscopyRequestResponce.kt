package org.southasia.foodcare.sync

import org.southasia.foodcare.vo.FundoscopyRequest

class FundoscopyRequestResponce(
    val eventType: SyncResponseEventType,
    val fundoscopyRequest: FundoscopyRequest
)
{
}
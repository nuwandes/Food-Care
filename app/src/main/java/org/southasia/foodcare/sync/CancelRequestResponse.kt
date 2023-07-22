package org.southasia.foodcare.sync

import org.southasia.foodcare.vo.request.CancelRequest

class CancelRequestResponse( val eventType: SyncResponseEventType,
                             val cancelRequest: CancelRequest
) {
}
package org.southasia.foodcare.sync

import org.southasia.foodcare.vo.request.SampleRequest

class SyncSampleRequestResponse(val eventType: SyncResponseEventType, val sampleRequest: SampleRequest)
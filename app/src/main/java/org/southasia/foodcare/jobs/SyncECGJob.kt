package org.southasia.foodcare.jobs

import com.birbit.android.jobqueue.Job
import com.birbit.android.jobqueue.Params
import com.birbit.android.jobqueue.RetryConstraint
import org.southasia.foodcare.sync.ECGStatusRxBus
import org.southasia.foodcare.sync.SyncResponseEventType
import org.southasia.foodcare.vo.ECGStatus
import org.southasia.foodcare.vo.request.ParticipantRequest
import timber.log.Timber

class SyncECGJob(private val participantRequest: ParticipantRequest?, private val eCGStatus: ECGStatus) : Job(
    Params(JobPriority.ECG)
        .setRequiresNetwork(true)
        .groupBy("ECG")
        .persist()
) {


    override fun onAdded() {
        Timber.d("Executing onAdded() for comment $participantRequest")
    }

    override fun shouldReRunOnThrowable(throwable: Throwable, runCount: Int, maxRunCount: Int): RetryConstraint {
        if (throwable is RemoteException) {

            val statusCode = throwable.response.code()
            if (statusCode >= 422 && statusCode < 500) {
                return RetryConstraint.CANCEL
            }
        }
        // if we are here, most likely the connection was lost during job execution
        return RetryConstraint.createExponentialBackoff(runCount, 1000);
    }

    override fun onRun() {
        Timber.d("Executing onRun() for household $participantRequest")
        RemoteHouseholdService().getInstance().addECG(participantRequest!!, eCGStatus)
        //   SyncSampleStorageRequestRxBus.getInstance().post(SyncResponseEventType.SUCCESS, sampleStorageRequest)
        ECGStatusRxBus.getInstance().post(SyncResponseEventType.SUCCESS,eCGStatus)
    }

    override fun onCancel(cancelReason: Int, throwable: Throwable?) {
        Timber.d("canceling job. reason: %d, throwable: %s", cancelReason, throwable)
        ECGStatusRxBus.getInstance().post(SyncResponseEventType.FAILED,eCGStatus)
        //Crashlytics.logException(throwable)

        //Crashlytics.log("SyncSpirometryJob " + participantRequest.toString())
        // sync to remote failed
        //    SyncSampleStorageRequestRxBus.getInstance().post(SyncResponseEventType.FAILED, sampleStorageRequest)
    }
    companion object {

        private val TAG = SyncECGJob::class.java.getCanonicalName()
    }
}
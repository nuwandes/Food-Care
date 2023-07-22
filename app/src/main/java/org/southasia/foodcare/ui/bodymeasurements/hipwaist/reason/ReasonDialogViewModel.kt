package org.southasia.foodcare.ui.bodymeasurements.hipwaist.reason

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.CancelRequestRepository
import org.southasia.foodcare.util.AbsentLiveData
import org.southasia.foodcare.vo.MessageCancel
import org.southasia.foodcare.vo.Resource
import org.southasia.foodcare.vo.request.CancelRequest
import org.southasia.foodcare.vo.request.ParticipantRequest
import javax.inject.Inject


class ReasonDialogViewModel
@Inject constructor(cancelRequestRepository: CancelRequestRepository) : ViewModel() {

    private val _cancelId: MutableLiveData<CancelId> = MutableLiveData()


    var cancelId: LiveData<Resource<MessageCancel>>? = Transformations
        .switchMap(_cancelId) { input ->
            input.ifExists { participantRequest, cancelRequest ->
                cancelRequestRepository.syncCancelRequest(participantRequest, cancelRequest)
            }
        }

    fun setLogin(participantRequest: ParticipantRequest?, cancelRequest: CancelRequest?) {
        val update = CancelId(participantRequest, cancelRequest)
        if (_cancelId.value == update) {
            return
        }
        _cancelId.value = update
    }

    data class CancelId(val participantRequest: ParticipantRequest?, val cancelRequest: CancelRequest?) {
        fun <T> ifExists(f: (ParticipantRequest, CancelRequest) -> LiveData<T>): LiveData<T> {
            return if (participantRequest == null || cancelRequest == null) {
                AbsentLiveData.create()
            } else {
                f(participantRequest, cancelRequest)
            }
        }
    }
}

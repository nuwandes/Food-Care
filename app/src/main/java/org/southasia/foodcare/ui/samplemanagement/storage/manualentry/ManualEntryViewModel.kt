package org.southasia.foodcare.ui.samplemanagement.storage.manualentry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.ParticipantRepository
import org.southasia.foodcare.repository.SampleRequestRepository
import org.southasia.foodcare.util.AbsentLiveData
import org.southasia.foodcare.vo.Participant
import org.southasia.foodcare.vo.Resource
import org.southasia.foodcare.vo.ResourceData
import org.southasia.foodcare.vo.request.SampleRequest
import javax.inject.Inject

class ManualEntryViewModel @Inject constructor(participantRepository: ParticipantRepository, sampleRequestRepository: SampleRequestRepository) : ViewModel() {

    private val _screeningId: MutableLiveData<String> = MutableLiveData()
    val screeningId: LiveData<String>
        get() = _screeningId

    var participant: LiveData<Resource<ResourceData<Participant>>> = Transformations
            .switchMap(_screeningId) { screeningId ->
                if (screeningId == null) {
                    AbsentLiveData.create()
                } else {
                    participantRepository.getParticipant(screeningId)
                }
            }

    fun setScreeningId(screeningId: String?) {
        if (_screeningId.value == screeningId) {
            return
        }
        _screeningId.value = screeningId
    }


    private val _storageId: MutableLiveData<String> = MutableLiveData()

    var storageIdCheck: LiveData<Resource<SampleRequest>>? = Transformations
            .switchMap(_storageId) { storageId ->
                if (storageId == null) {
                    AbsentLiveData.create()
                } else {
                    sampleRequestRepository.getStorageId(storageId)
                }
            }


    fun setStorageId(storageId: String?) {
        _storageId.value = storageId
    }
}

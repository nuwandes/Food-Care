package org.southasia.foodcare.ui.questionnaire.scanbarcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.ParticipantRepository
import org.southasia.foodcare.repository.UserRepository
import org.southasia.foodcare.util.AbsentLiveData
import org.southasia.foodcare.vo.Resource
import org.southasia.foodcare.vo.ResourceData
import org.southasia.foodcare.vo.User
import org.southasia.foodcare.vo.request.ParticipantRequest
import javax.inject.Inject


class ScanBarcodeViewModel
@Inject constructor(participantRepository: ParticipantRepository, userRepository: UserRepository) : ViewModel() {

    private val _screeningId: MutableLiveData<String> = MutableLiveData()
    private val _screeningIdOffline: MutableLiveData<String> = MutableLiveData()

    var participantOffline: LiveData<Resource<ParticipantRequest>>? = Transformations
        .switchMap(_screeningIdOffline) { screeningIdOffline ->
            if (screeningIdOffline == null) {
                AbsentLiveData.create()
            } else {
                participantRepository.getParticipantOffline(screeningIdOffline)
            }
        }

    fun setScreeningIdOffline(screeningIdOffline: String?) {
        if (_screeningIdOffline.value == screeningIdOffline) {
            return
        }
        _screeningIdOffline.value = screeningIdOffline
    }

    var participant: LiveData<Resource<ResourceData<ParticipantRequest>>> = Transformations
        .switchMap(_screeningId) { screeningId ->
            if (screeningId == null) {
                AbsentLiveData.create()
            } else {
                participantRepository.getParticipantRequest(screeningId, "questionnaire")
            }
        }

    fun setScreeningId(screeningId: String?) {
        if (_screeningId.value == screeningId) {
            return
        }
        _screeningId.value = screeningId
    }

    private val _email = MutableLiveData<String>()

    val user: LiveData<Resource<User>>? = Transformations
        .switchMap(_email) { emailx ->
            if (emailx == null) {
                AbsentLiveData.create()
            } else {
                userRepository.loadUserDB()
            }
        }
    fun setUser(email: String?) {
        if (_email.value != email) {
            _email.value = email
        }
    }
}

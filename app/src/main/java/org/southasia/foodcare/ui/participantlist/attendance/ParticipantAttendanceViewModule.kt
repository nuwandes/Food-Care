package org.southasia.foodcare.ui.participantlist.attendance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.ParticipantListRepository
import org.southasia.foodcare.repository.UserRepository
import org.southasia.foodcare.util.AbsentLiveData
import org.southasia.foodcare.vo.*
import org.southasia.foodcare.vo.request.Gender
import javax.inject.Inject

class ParticipantAttendanceViewModule
@Inject constructor(userRepository: UserRepository, repository: ParticipantListRepository) : ViewModel() {

    var gender: MutableLiveData<String> = MutableLiveData<String>()

    var birthYear: Int = 1998

    var birthDate: MutableLiveData<String> = MutableLiveData<String>()

    var birthDateVal: MutableLiveData<Date> = MutableLiveData<Date>()

    var contactNo: MutableLiveData<String> = MutableLiveData<String>()

    var age: MutableLiveData<String> = MutableLiveData<String>()


    fun setGender(g: Gender) {
        gender.postValue(g.gender)
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

    //    update single participant ------------------------------------------------------------------------------

    private val _participantUpdateRequest: MutableLiveData<ParticipantListItem> = MutableLiveData()
    private var _participantId: String? = null
    private val _participantRequest: MutableLiveData<ParticipantListItem> = MutableLiveData()

    var participantUpdateComplete:LiveData<Resource<ResourceData<ParticipantListItem>>>? = Transformations
        .switchMap(_participantUpdateRequest) { participantRequest ->
            if (participantRequest == null) {
                AbsentLiveData.create()
            } else {
                repository.updateParticipantItem(participantRequest,_participantId!!)
            }
        }

    fun setParticipant(participantItem: ParticipantListItem, participantId: String?) {
        _participantId = participantId
        if (_participantRequest.value == participantItem) {
            return
        }
        _participantRequest.value = participantItem
    }
    fun updateParticipant(participantItem: ParticipantListItem, participantId: String?) {
        _participantId = participantId
        if (_participantUpdateRequest.value == participantItem) {
            return
        }
        _participantUpdateRequest.value = participantItem
    }

//    --------------------------------------------------------------------------------------------------------
}

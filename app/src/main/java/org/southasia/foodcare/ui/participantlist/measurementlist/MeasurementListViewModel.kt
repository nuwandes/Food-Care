package org.southasia.foodcare.ui.participantlist.measurementlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.ParticipantListRepository
import org.southasia.foodcare.util.AbsentLiveData
import org.southasia.foodcare.vo.*
import javax.inject.Inject

class MeasurementListViewModel
@Inject constructor(repository: ParticipantListRepository) : ViewModel() {

//    Get single participant stations ------------------------------------------------------------------------

    private val _participantId: MutableLiveData<ParticipantId> = MutableLiveData()

    var getSingleParticipantStations: LiveData<Resource<StationData<List<ParticipantStation>>>>? = Transformations
        .switchMap(_participantId) { input ->
            input.ifExists { participant ->
                repository.getSingleParticipantStations(participant!!.toString())

            }
        }

    fun setParticipantId(participant: String) {
        val update =
            ParticipantId(participant = participant)
        if (_participantId.value == update) {
            return
        }
        _participantId.value = update
    }

    data class ParticipantId(val participant: String?) {

        fun <T> ifExists(f: (String?) -> LiveData<T>): LiveData<T> {
            return if (participant == null) {
                AbsentLiveData.create()
            } else {
                f(participant)
            }
        }
    }


//    --------------------------------------------------------------------------------------------------------

//    get measurement list items -----------------------------------------------------------------------------

//    private val _measurementList = MutableLiveData<String>()

    private val _stationList = MutableLiveData<List<ParticipantStation>>()

    val measurementListItem: LiveData<Resource<List<MeasurementListItem>>> = Transformations
        .switchMap(_stationList) { login ->
            if (login == null) {
                AbsentLiveData.create()
            } else {
                repository.getMeasurementListItems(login)
            }
        }

//    fun setMeasurementId(lang: String?) {
//        if (_measurementList.value != lang) {
//            _measurementList.value = lang
//        }
//    }

//    --------------------------------------------------------------------------------------------------------

//    set measurement list -----------------------------------------------------------------------------------



    val StationList: LiveData<Resource<List<ParticipantStation>>>? = Transformations
        .switchMap(_stationList) { search ->
            if (search == null) {
                AbsentLiveData.create()
            } else {
                repository.getStationList(search)
            }
        }

    fun setStationList(sList: List<ParticipantStation>?) {
        if (_stationList.value != sList) {
            _stationList.value = sList
        }
    }

//    --------------------------------------------------------------------------------------------------------

    //    update single participant ------------------------------------------------------------------------------

    private val _participantUpdateRequest: MutableLiveData<ParticipantListItem> = MutableLiveData()
    private var _participantID: String? = null
    private val _participantRequest: MutableLiveData<ParticipantListItem> = MutableLiveData()

    var participantFollowUpStatusUpdate:LiveData<Resource<ResourceData<ParticipantListItem>>>? = Transformations
        .switchMap(_participantUpdateRequest) { participantRequest ->
            if (participantRequest == null) {
                AbsentLiveData.create()
            } else {
                repository.updateParticipantItem(participantRequest,_participantID!!)
            }
        }

    fun setFollowUpParticipant(participantItem: ParticipantListItem, participantId: String?) {
        _participantID = participantId
        if (_participantRequest.value == participantItem) {
            return
        }
        _participantRequest.value = participantItem
    }
    fun updateParticipantFollowUp(participantItem: ParticipantListItem, participantId: String?) {
        _participantID = participantId
        if (_participantUpdateRequest.value == participantItem) {
            return
        }
        _participantUpdateRequest.value = participantItem
    }

//    --------------------------------------------------------------------------------------------------------

}
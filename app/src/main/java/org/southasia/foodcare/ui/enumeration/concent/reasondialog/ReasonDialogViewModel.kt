package org.southasia.foodcare.ui.enumeration.concent.reasondialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.HouseholdRequestRepository
import org.southasia.foodcare.util.AbsentLiveData
import org.southasia.foodcare.vo.Resource
import org.southasia.foodcare.vo.ResponceData
import org.southasia.foodcare.vo.request.HouseholdRequestMeta
import javax.inject.Inject


class ReasonDialogViewModel
@Inject constructor(householdRequestRepository: HouseholdRequestRepository) : ViewModel() {

    private val _householdRequest: MutableLiveData<HouseholdRequestMeta> = MutableLiveData()
    private val _householdRequestSync: MutableLiveData<HouseholdRequestMeta> = MutableLiveData()

    var householdSave: LiveData<Resource<HouseholdRequestMeta>>? = Transformations
        .switchMap(_householdRequest) { householdRequest ->
            if (householdRequest == null) {
                AbsentLiveData.create()
            } else {
                householdRequestRepository.insertHouseholdRequest(householdRequest)
            }
        }


    var householdRequestSyncRemote: LiveData<Resource<ResponceData>>? = Transformations
        .switchMap(_householdRequestSync) { householdRequest ->
            if (householdRequest == null) {
                AbsentLiveData.create()
            } else {
                householdRequestRepository.syncHousehold(householdRequest)
            }
        }

    fun setHouseholdRequest(householdRequest: HouseholdRequestMeta?) {
        if (_householdRequest.value != householdRequest) {
            _householdRequest.postValue(householdRequest)
        }
    }

    fun setHouseholdRequestSyncRemote(household: HouseholdRequestMeta) {
        if (_householdRequestSync.value != household) {
            _householdRequestSync.postValue(household)
        }
    }
}

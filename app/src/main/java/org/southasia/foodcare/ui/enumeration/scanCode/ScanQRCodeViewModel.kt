package org.southasia.foodcare.ui.enumeration.scanCode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.HouseholdRequestRepository
import org.southasia.foodcare.util.AbsentLiveData
import org.southasia.foodcare.vo.Resource
import org.southasia.foodcare.vo.request.HouseholdRequestMeta
import javax.inject.Inject


class ScanQRCodeViewModel
@Inject constructor(householdRequestRepository: HouseholdRequestRepository) : ViewModel() {

    private val _invitationId: MutableLiveData<String> = MutableLiveData()

    var householdRequestCheck: LiveData<Resource<HouseholdRequestMeta>>? = Transformations
        .switchMap(_invitationId) { householdRequest ->
            if (householdRequest == null) {
                AbsentLiveData.create()
            } else {
                householdRequestRepository.getItemId(householdRequest)
            }
        }

    fun getItemId(screeningId: String?) {
        _invitationId.value = screeningId
    }
}

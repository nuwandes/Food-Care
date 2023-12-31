package org.southasia.foodcare.ui.samplemanagement.storage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.SampleStorageRepository
import org.southasia.foodcare.util.AbsentLiveData
import org.southasia.foodcare.vo.Resource
import org.southasia.foodcare.vo.ResourceData
import org.southasia.foodcare.vo.SampleData
import org.southasia.foodcare.vo.request.SampleStorageRequest
import javax.inject.Inject


class StorageViewModel
@Inject constructor(sampleStorageRepository: SampleStorageRepository) : ViewModel() {

    var hasLinked: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { false }

    var isChecked: Boolean = false

    fun setHasLinked(explained: Boolean) {
        isChecked = explained
        hasLinked.value = (explained)
    }

    private val _sampleStorageRequestRemote: MutableLiveData<SampleStorageRequest> = MutableLiveData()

    private val _sampleStorageRequestLocal: MutableLiveData<SampleStorageRequest> = MutableLiveData()


    var sampleStorageRequestRemote: LiveData<Resource<ResourceData<SampleData>>>? = Transformations

        .switchMap(_sampleStorageRequestRemote) { sampleStorageRequestRemote ->
            if (sampleStorageRequestRemote == null) {
                AbsentLiveData.create()
            } else {
                sampleStorageRepository.syncSampleStorageRequest(sampleStorageRequestRemote)
            }
        }

    var sampleStorageRequestLocal: LiveData<Resource<SampleStorageRequest>>? = Transformations
        .switchMap(_sampleStorageRequestLocal) { sampleStorageRequestLocalx ->
            if (sampleStorageRequestLocalx == null) {
                AbsentLiveData.create()
            } else {
                sampleStorageRepository.insertSampleRequest(sampleStorageRequestLocalx)
            }
        }


    fun setSampleStorageRequestRemote(sampleStorageRequest: SampleStorageRequest) {
        if (_sampleStorageRequestRemote.value != sampleStorageRequest) {
            _sampleStorageRequestRemote.postValue(sampleStorageRequest)
        }
    }


    fun setSampleStorageRequestLocal(sampleStorageRequest: SampleStorageRequest) {
        if (_sampleStorageRequestLocal.value != sampleStorageRequest) {
            _sampleStorageRequestLocal.postValue(sampleStorageRequest)
        }
    }


}

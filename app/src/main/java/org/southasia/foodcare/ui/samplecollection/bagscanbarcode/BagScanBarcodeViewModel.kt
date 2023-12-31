package org.southasia.foodcare.ui.samplecollection.bagscanbarcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.SampleRequestRepository
import org.southasia.foodcare.util.AbsentLiveData
import org.southasia.foodcare.vo.Resource
import org.southasia.foodcare.vo.request.SampleRequest
import javax.inject.Inject


class BagScanBarcodeViewModel
@Inject constructor(sampleRequestRepository: SampleRequestRepository) : ViewModel() {

    private val _sampleId: MutableLiveData<String> = MutableLiveData()
    var screeningIdCheck: LiveData<Resource<SampleRequest>>? = Transformations
        .switchMap(_sampleId) { sampleId ->
            if (sampleId == null) {
                AbsentLiveData.create()
            } else {
                sampleRequestRepository.getItemId(sampleId)
            }
        }
    private val _sampleIdAll: MutableLiveData<String> = MutableLiveData()

    var screeningIdCheckAll: LiveData<Resource<List<SampleRequest>>>? = Transformations
        .switchMap(_sampleIdAll) { sampleId ->
            if (sampleId == null) {
                AbsentLiveData.create()
            } else {
                sampleRequestRepository.getSamples()
            }
        }

    fun setSampleIdAll(sampleId: String?) {
        _sampleIdAll.value = sampleId
    }

    fun setSampleId(sampleId: String?) {
        _sampleId.value = sampleId
    }
}

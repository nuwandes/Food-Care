package org.southasia.foodcare.ui.samplemanagement.hogtt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.StationDevicesRepository
import org.southasia.foodcare.vo.Measurements
import org.southasia.foodcare.vo.Resource
import org.southasia.foodcare.vo.StationDeviceData
import javax.inject.Inject


class HOGTTViewModel
@Inject constructor(stationDevicesRepository: StationDevicesRepository) : ViewModel() {

    private val _stationName = MutableLiveData<String>()
    var hogtt: MutableLiveData<String> = MutableLiveData<String>().apply { "" }
    fun setStationName(stationName: Measurements) {
        val update = "2" + stationName.toString().toLowerCase()
        if (_stationName.value == update) {
            return
        }
        _stationName.value = update
    }

    var stationDeviceList: LiveData<Resource<List<StationDeviceData>>>? = Transformations
        .switchMap(_stationName) { input ->
            stationDevicesRepository.getStationDeviceList(_stationName.value!!)
        }
}

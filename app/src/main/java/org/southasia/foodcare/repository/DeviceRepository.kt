package org.southasia.foodcare.repository

import androidx.lifecycle.LiveData
import org.southasia.foodcare.AppExecutors
import org.southasia.foodcare.api.ApiResponse
import org.southasia.foodcare.api.NghruServiceLocal
import org.southasia.foodcare.vo.Devices
import org.southasia.foodcare.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Repository that handles User objects.
 */

@Singleton
class DeviceRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val nghruServiceLocal: NghruServiceLocal
) {

    fun getDevices(
    ): LiveData<Resource<Devices>> {
        return object : NetworkOnlyBoundResource<Devices>(appExecutors) {
            override fun createCall(): LiveData<ApiResponse<Devices>> {
                return nghruServiceLocal.getDevices();
            }
        }.asLiveData()
    }

}

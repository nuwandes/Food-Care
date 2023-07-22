package org.southasia.foodcare.repository

import androidx.lifecycle.LiveData
import org.southasia.foodcare.AppExecutors
import org.southasia.foodcare.api.ApiResponse
import org.southasia.foodcare.api.NghruService
import org.southasia.foodcare.db.UserDao
import org.southasia.foodcare.util.TokenManager
import org.southasia.foodcare.vo.*
import org.southasia.foodcare.vo.request.SignUpData
import org.southasia.foodcare.vo.request.SignUpRequest
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository that handles User objects.
 */

@Singleton
class SignUpRepository @Inject constructor
    (
    private val appExecutors: AppExecutors,
    private val nghruService: NghruService

) {
    fun syncSignUp(
        signUpData: SignUpRequest
    ): LiveData<Resource<ResourceData<Message>>> {
        return object : NetworkOnlyBcakgroundBoundResource<ResourceData<Message>>(appExecutors) {
            override fun createCall(): LiveData<ApiResponse<ResourceData<Message>>> {
                return nghruService.addSignUp(signUpData)
            }
        }.asLiveData()
    }

}

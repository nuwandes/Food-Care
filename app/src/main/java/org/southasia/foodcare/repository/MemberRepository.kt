package org.southasia.foodcare.repository

import androidx.lifecycle.LiveData
import org.southasia.foodcare.AppExecutors
import org.southasia.foodcare.api.ApiResponse
import org.southasia.foodcare.api.NghruService
import org.southasia.foodcare.vo.Resource
import org.southasia.foodcare.vo.ResourceData
import org.southasia.foodcare.vo.request.Member
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Repository that handles User objects.
 */

@Singleton
class MemberRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val nghruService: NghruService
) : Serializable {


    fun getMember(
        householdId: String
    ): LiveData<Resource<ResourceData<List<Member>>>> {
        return object : NetworkOnlyBoundResource<ResourceData<List<Member>>>(appExecutors) {
            override fun createCall(): LiveData<ApiResponse<ResourceData<List<Member>>>> {
                return nghruService.getMember(householdId, "0")
            }
        }.asLiveData()
    }


//    fun getMemberLocal(householdId: String
//    ): LiveData<Resource<ResourceData<List<Member>>>> {
//        return object : NetworkOnlyBoundResource<MemberData, ResourceData<List<Member>>>(appExecutors) {
//            override fun createCall(): LiveData<ApiResponse<ResourceData<List<Member>>>> {
//                return nghruService.getMember(tokenManager.getToken().accessToken!!, householdId)
//            }
//        }.asLiveData()
//    }

}

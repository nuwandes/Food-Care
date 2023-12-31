package org.southasia.foodcare.repository

import androidx.lifecycle.LiveData
import org.southasia.foodcare.AppExecutors
import org.southasia.foodcare.api.ApiResponse
import org.southasia.foodcare.api.NghruService
import org.southasia.foodcare.db.QuestionnaireDao
import org.southasia.foodcare.vo.*
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Repository that handles User objects.
 */

@Singleton
class QuestionnaireRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val questionnaireDao: QuestionnaireDao,
    private val nghruService: NghruService
) : Serializable {
    fun getQuestionnaire(
        network: Boolean,
        language :String
    ): LiveData<Resource<Questionnaire>> {

        return object : NetworkBoundResource<Questionnaire, ResourceData<Questionnaire>>(appExecutors) {
            override fun saveCallResult(item: ResourceData<Questionnaire>) {
               // item.data?.json?.replace("\r", "")?.replace("\n","")?.replace("\\" , "")
                questionnaireDao.insert(item.data!!)
            }

            override fun shouldFetch(data: Questionnaire?): Boolean = network

            override fun loadFromDb(): LiveData<Questionnaire> {
                return questionnaireDao.getQuestionnaire()
            }

            override fun createCall(): LiveData<ApiResponse<ResourceData<Questionnaire>>> {
                return nghruService.getQuestionnaire(language)
            }
        }.asLiveData()
    }


    fun getQuestionnaireList(
        network: Boolean,
        language :String
    ): LiveData<Resource<List<Questionnaire>>> {

        return object : NetworkBoundResource<List<Questionnaire>, ResourceData<List<Questionnaire>>>(appExecutors) {
            override fun saveCallResult(item: ResourceData<List<Questionnaire>>) {
                // item.data?.json?.replace("\r", "")?.replace("\n","")?.replace("\\" , "")
                if(network){
                    questionnaireDao.nukeTable()
                    questionnaireDao.insert(item.data!!)
                }else{
                    questionnaireDao.insert(item.data!!)
                }

            }

            override fun shouldFetch(data: List<Questionnaire>?): Boolean = network

            override fun loadFromDb(): LiveData<List<Questionnaire>> {
                return questionnaireDao.getQuestionnaires()
            }

            override fun createCall(): LiveData<ApiResponse<ResourceData<List<Questionnaire>>>> {
                return nghruService.getQuestionnaire()
            }
        }.asLiveData()
    }
}

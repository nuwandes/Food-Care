package org.southasia.foodcare.ui.questionnaire.web

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.QuestionnaireRepository
import org.southasia.foodcare.repository.SurveyRepository
import org.southasia.foodcare.repository.UserRepository
import org.southasia.foodcare.util.AbsentLiveData
import org.southasia.foodcare.vo.*
import javax.inject.Inject


class WebViewModel
@Inject constructor(
    surveyRepository: SurveyRepository,
    userRepository: UserRepository,
    questionnaireRepository: QuestionnaireRepository
) : ViewModel() {

    private val _survey: MutableLiveData<QuestionMeta> = MutableLiveData()

    var survey: LiveData<Resource<ResourceData<CommonResponce>>>? = Transformations
        .switchMap(_survey) { _surveyValue ->
            if (_surveyValue == null) {
                AbsentLiveData.create()
            } else {
                surveyRepository.syncSurvey(_surveyValue)
            }
        }

    fun setSurvey(json: QuestionMeta) {
        _survey.postValue(json)
    }

    private val _email = MutableLiveData<String>()

    val user: LiveData<Resource<User>>? = Transformations
        .switchMap(_email) { emailx ->
            if (emailx == null) {
                AbsentLiveData.create()
            } else {
                userRepository.loadUserDB()
            }
        }


    fun setUser(email: String?) {
        if (_email.value != email) {
            _email.value = email
        }
    }

    private val _language = MutableLiveData<QuestionnaireId>()

    val language: LiveData<Resource<Questionnaire>>? = Transformations
        .switchMap(_language) { input ->
            input.ifExists { language, network ->
                questionnaireRepository.getQuestionnaire(language = language, network = network)
            }
        }


    fun getQuestionnaire(
        language: String?,
        network: Boolean?
    ) {
        val update =
            QuestionnaireId(language, network)
        if (_language.value == update) {
            return
        }
        _language.value = update
    }


    data class QuestionnaireId(
        val language: String?,
        val network: Boolean?
    ) {
        fun <T> ifExists(f: (String, Boolean) -> LiveData<T>): LiveData<T> {
            return if (language == null || network == null) {
                AbsentLiveData.create()
            } else {
                f(language!!, network!!)
            }
        }
    }


    //   var member = Member("Faruk Hasan", "Neyamot Ullah", "Ullah", "Male", true, "1254896", "25", "2 May 1980, 38 years", true, true, false, "")

////    get participant request ----------------------------------------------------------------------------
//
//    private val _screeningId: MutableLiveData<String> = MutableLiveData()
//
//    var participant: LiveData<Resource<ResourceData<ParticipantRequest>>> = Transformations
//        .switchMap(_screeningId) { screeningId ->
//            if (screeningId == null) {
//                AbsentLiveData.create()
//            } else {
//                participantRepository.getParticipantRequest(screeningId, "questionnaire")
//            }
//        }
//
//    fun setScreeningId(screeningId: String?) {
//        if (_screeningId.value == screeningId) {
//            return
//        }
//        _screeningId.value = screeningId
//    }
//
////    ----------------------------------------------------------------------------------------------------
//
////    get participant request offline --------------------------------------------------------------------
//
//    private val _screeningIdOffline: MutableLiveData<String> = MutableLiveData()
//
//    var participantOffline: LiveData<Resource<ParticipantRequest>>? = Transformations
//        .switchMap(_screeningIdOffline) { screeningIdOffline ->
//            if (screeningIdOffline == null) {
//                AbsentLiveData.create()
//            } else {
//                participantRepository.getParticipantOffline(screeningIdOffline)
//            }
//        }
//
//    fun setScreeningIdOffline(screeningIdOffline: String?) {
//        if (_screeningIdOffline.value == screeningIdOffline) {
//            return
//        }
//        _screeningIdOffline.value = screeningIdOffline
//    }
//
////    ----------------------------------------------------------------------------------------------------

}

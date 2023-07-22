package org.southasia.foodcare.ui.login.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.AccessTokenRepository
import org.southasia.foodcare.repository.SignUpRepository
import org.southasia.foodcare.repository.StationDevicesRepository
import org.southasia.foodcare.util.AbsentLiveData
import org.southasia.foodcare.vo.*
import org.southasia.foodcare.vo.request.SignUpData
import org.southasia.foodcare.vo.request.SignUpRequest
import javax.inject.Inject


class SignUpViewModel
@Inject constructor(
    signUpRepository: SignUpRepository
) : ViewModel() {

    // to post sign up data -------------------------------------------------------------------------------------------

    private val _signUpRequest: MutableLiveData<SignUpRequest> = MutableLiveData()

    fun setSignupData(signUpData: SignUpRequest) {
        if (_signUpRequest.value == signUpData) {
            return
        }
        _signUpRequest.value = signUpData
    }

    var syncSignupRequest: LiveData<Resource<ResourceData<Message>>>? = Transformations
        .switchMap(_signUpRequest) { signUpRequest ->
            if (signUpRequest == null) {
                AbsentLiveData.create()
            } else {
                signUpRepository.syncSignUp(signUpRequest)
            }
        }

    // ----------------------------------------------------------------------------




}

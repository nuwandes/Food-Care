package org.southasia.foodcare.ui.homeenumerationlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.HomeEmumerationListRepository
import org.southasia.foodcare.repository.UserRepository
import org.southasia.foodcare.util.AbsentLiveData
import org.southasia.foodcare.vo.HomeEmumerationListItem
import org.southasia.foodcare.vo.Resource
import org.southasia.foodcare.vo.User
import javax.inject.Inject


class HomeEmumerationListViewModel
@Inject constructor(repository: HomeEmumerationListRepository, userRepository: UserRepository) : ViewModel() {
    private val _homeemumerationlist = MutableLiveData<String>()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    val homeemumerationlistItem: LiveData<Resource<List<HomeEmumerationListItem>>> = Transformations
        .switchMap(_homeemumerationlist) { login ->
            if (login == null) {
                AbsentLiveData.create()
            } else {
                repository.getHomeEmumerationListItems();
            }
        }


    val user: LiveData<Resource<User>>? = Transformations
        .switchMap(_email) { email ->
            if (email == null) {
                AbsentLiveData.create()
            } else {
                userRepository.loadUser()
            }
        }

    fun setId(lang: String?) {
        if (_homeemumerationlist.value != lang) {
            _homeemumerationlist.value = lang
        }
    }

    fun setUser(email: String?) {
        if (_email.value != email) {
            _email.value = email
        }
    }

}

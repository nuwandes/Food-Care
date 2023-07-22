package org.southasia.foodcare.ui.samplemanagement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.southasia.foodcare.repository.HomeRepository
import org.southasia.foodcare.repository.UserRepository
import org.southasia.foodcare.util.AbsentLiveData
import org.southasia.foodcare.vo.HomeItem
import org.southasia.foodcare.vo.Resource
import javax.inject.Inject


class SampleMangementViewModel
@Inject constructor(repository: HomeRepository, userRepository: UserRepository) : ViewModel() {
    private val _home = MutableLiveData<String>()
    val home: LiveData<String>
        get() = _home


    val homeItem: LiveData<Resource<List<HomeItem>>> = Transformations
        .switchMap(_home) { login ->
            if (login == null) {
                AbsentLiveData.create()
            } else {
                repository.getSampleItems();
            }
        }


    fun setId(lang: String?) {
        if (_home.value != lang) {
            _home.value = lang
        }
    }


}

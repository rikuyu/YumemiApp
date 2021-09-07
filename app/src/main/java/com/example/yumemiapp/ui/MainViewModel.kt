package com.example.yumemiapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumemiapp.model.data.ContributorsItem
import com.example.yumemiapp.model.data.Profile
import com.example.yumemiapp.model.repository.MainRepository
import com.example.yumemiapp.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _contributers: MutableLiveData<State<List<ContributorsItem>>> = MutableLiveData()
    val contributers: LiveData<State<List<ContributorsItem>>> = _contributers

    private val _followings: MutableLiveData<State<List<ContributorsItem>>> = MutableLiveData()
    val followings: LiveData<State<List<ContributorsItem>>> = _followings

    private val _favoList: MutableLiveData<List<Profile>> = MutableLiveData()
    val favoList: LiveData<List<Profile>> = _favoList

    init {
        fetchContributors()
    }

    private fun fetchContributors() {
        viewModelScope.launch {
            _contributers.value = State.Loading()
            val response = repository.fetchContributors()
            if (response.isSuccessful) {
                response.body()?.let {
                    _contributers.value = State.Success(it)
                }
            } else {
                _contributers.value = State.Error("Error")
            }
        }
    }

    fun getFollowing(userNmae: String) {
        viewModelScope.launch {
            _followings.value = State.Loading()
            val response = repository.getFollowings(userNmae)
            if (response.isSuccessful) {
                response.body()?.let {
                    _followings.value = State.Success(it)
                }
            } else {
                _followings.value = State.Error("Error")
            }
        }
    }

    fun getFavoriteContributors() {
        _favoList.value = repository.getContributors()
    }

    fun insertContributor(contributer: Profile) {
        viewModelScope.launch {
            repository.insertContributor(contributer)
        }
    }

    fun deleteContributor(contributer: Profile) {
        viewModelScope.launch {
            repository.deleteContributor(contributer)
        }
    }
}
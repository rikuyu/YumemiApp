package com.example.yumemiapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumemiapp.model.data.ContributersItem
import com.example.yumemiapp.model.data.Profile
import com.example.yumemiapp.model.repository.MainRepository
import com.example.yumemiapp.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
)  : ViewModel() {

    private val _contributers: MutableLiveData<State<List<ContributersItem>>> = MutableLiveData()
    val contributers: LiveData<State<List<ContributersItem>>> = _contributers

    private val _followings: MutableLiveData<State<List<ContributersItem>>> = MutableLiveData()
    val followings: LiveData<State<List<ContributersItem>>> = _followings

    private val _favoList: MutableLiveData<List<Profile>> = MutableLiveData()
    val favoList: LiveData<List<Profile>> = _favoList

    init {
        fetchContributers()
    }

    private fun fetchContributers(){
        viewModelScope.launch {
            _contributers.value = State.Loading()
            val response = repository.fetchContributers()
            if(response.isSuccessful){
                response.body()?.let {
                    _contributers.value = State.Success(it)
                }
            }else{
                _contributers.value = State.Error("Error")
            }
        }
    }

    fun getFollowing(userNmae: String){
        viewModelScope.launch {
            _followings.value = State.Loading()
            val response = repository.getFollowings(userNmae)
            if(response.isSuccessful){
                response.body()?.let {
                    _followings.value = State.Success(it)
                }
            }else{
                _followings.value = State.Error("Error")
            }
        }
    }

    fun getFavoriteContributers(){
        _favoList.value = repository.getContributers()
    }

    fun insertContributer(contributer: Profile){
        viewModelScope.launch {
            repository.insertContributer(contributer)
        }
    }

    fun deleteContributer(contributer: Profile){
        viewModelScope.launch {
            repository.deleteContributer(contributer)
        }
    }
}
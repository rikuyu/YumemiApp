package com.example.yumemiapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumemiapp.model.data.ContributersItem
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
}
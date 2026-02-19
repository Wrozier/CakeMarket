package com.example.cakemarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cakemarket.model.CakeResponseItem
import com.example.cakemarket.repository.CakeRepository
import kotlinx.coroutines.launch

class CakeViewModel : ViewModel() {

    private val repository = CakeRepository()

    // SUCCESS STATE
    private val _cakes = MutableLiveData<List<CakeResponseItem>>()
    val cakes: LiveData<List<CakeResponseItem>> = _cakes

    // LOADING STATE
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    // ERROR STATE
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchCakes() {
        viewModelScope.launch {
            _loading.value = true

            repository.getCakes()
                .onSuccess { cakes ->
                    _cakes.value = cakes
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Unknown error"
                }

            _loading.value = false
        }
    }
}

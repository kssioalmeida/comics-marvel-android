package com.example.comics.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    private val _loadState = MutableLiveData<Boolean>()
    val loadState: LiveData<Boolean> = _loadState

    protected fun launch(asyncBlock: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            _loadState.postValue(true)
            asyncBlock()
            _loadState.postValue(false)
        }
    }

}
package com.example.comics.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.comics.core.BaseViewModel
import com.example.comics.data.ItemVO
import com.example.comics.model.ComicsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val interactor: ComicsInteractor
): BaseViewModel() {

    private val _comicsList = MutableLiveData<List<ItemVO>>()
    val comicsList: LiveData<List<ItemVO>> = _comicsList

    private val _showError = MutableLiveData(false)
    val errorState: LiveData<Boolean> = _showError

    fun getComics(){
        launch {
            _showError.postValue(false)
            interactor.getComics()
                .onEach {
                    _comicsList.postValue(it)
                }
                .catch {
                    _showError.postValue(true)
                }
                .collect()
        }
    }
}
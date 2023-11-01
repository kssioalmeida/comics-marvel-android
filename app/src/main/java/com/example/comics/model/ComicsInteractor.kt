package com.example.comics.model

import com.example.comics.data.ItemVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ComicsInteractor {
    fun getComics(): Flow<List<ItemVO>>
}

class ComicsInteractorImpl @Inject constructor(
    private val repository: ComicsRepository,
    private val mapper: ComicsMapper
): ComicsInteractor {

    override fun getComics(): Flow<List<ItemVO>> {
        return repository.getComics()
            .map {
                mapper.toItemVO(it)
            }
    }
}
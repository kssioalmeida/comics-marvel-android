package com.example.comics.model

import com.example.comics.core.NetworkHandler
import com.example.comics.data.ItemModel
import com.example.comics.data.remote.ComicsService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ComicsRepository {
    fun getComics(): Flow<ItemModel>
}

class ComicsRepositoryImpl @Inject() constructor(
    private val comicsService: ComicsService
): ComicsRepository {

    private companion object {
        const val API_KEY = "b7e14bab409c70a5c4e7c2b319c09d7b"
        const val TS = "1682982412"
        const val HASH = "3482f01e9bf207a437a4b621c91339ad"
    }

    override fun getComics(): Flow<ItemModel> {
        return NetworkHandler().handleResponseToFlow {
            comicsService.getComics(TS, API_KEY, HASH)
        }
    }
}
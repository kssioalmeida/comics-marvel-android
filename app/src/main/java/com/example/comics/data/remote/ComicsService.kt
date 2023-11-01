package com.example.comics.data.remote

import com.example.comics.data.ItemModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsService {
    @GET("comics")
    suspend fun getComics(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ) : Response<ItemModel>
}
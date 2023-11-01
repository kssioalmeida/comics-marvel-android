package com.example.comics.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class BaseRetrofit @Inject constructor() {

    private companion object {
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    }

    fun builder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
}
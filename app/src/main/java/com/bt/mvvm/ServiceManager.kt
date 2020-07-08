package com.bt.mvvm

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceManager {

    private val BASE_URL = "https://dog.ceo/"

    private fun getRetrofit() : Retrofit = Retrofit.Builder()
        .addConverterFactory(
            GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun getDogApi() = getRetrofit().create(DogsApi::class.java)

}
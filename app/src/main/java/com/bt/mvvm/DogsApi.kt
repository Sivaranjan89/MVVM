package com.bt.mvvm

import retrofit2.Response
import retrofit2.http.GET

interface DogsApi {

    //suspend modifier is needed as this is getting called from a coroutine scope
    @GET("api/breeds/image/random")
    suspend fun getRandomDog() : Response<Dog>

}
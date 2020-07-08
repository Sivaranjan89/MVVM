package com.bt.mvvm

class MainRepository {

    //suspend modifier is needed as this is getting called from a coroutine scope
    suspend fun getRandomDog() = ServiceManager.getDogApi().getRandomDog()
}
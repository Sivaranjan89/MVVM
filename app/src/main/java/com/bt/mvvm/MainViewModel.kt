package com.bt.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    //Always make sure that your mutableLivedata is not exposed to the view
    //Only LiveData should be exposed because livedata will not allow the view to modify
    //Only the viewmodel should be able to modify the mutableLiveData
    private val dogLiveData : MutableLiveData<ApiResult> = MutableLiveData()
    val _dogLiveData : LiveData<ApiResult> = dogLiveData

    private val repository : MainRepository = MainRepository()

    //Fetch from Api
    fun fetchRandomDog() {
        //Here we are using Coroutines to handle the API fetch
        //viewModelScope will keep the life of this network call linked to this viewmodel
        //if the viewmodel is destroyed while fetch is active, the coroutine kills the network call as well
        viewModelScope.launch(CoroutineExceptionHandler { context, exception ->
            //Any exception / error will be caught here by the coroutines
            //We are emitting the onError state with the exception received to the view from here
            dogLiveData.value = ApiResult.onError(error = exception)
        }) {
            //get the response by calling the api
            //Since we are using coroutines, we need to use suspend function on getRandomDog() in MainRepository
            val response = repository.getRandomDog()

            //If response is successful, emit the onSuccess state with the response
            //else emit the onError state with our own defined exception and a message to be handled by the view
            if (response.isSuccessful) {
                response.body()?.let { dog ->
                    dogLiveData.value = ApiResult.onSuccess(data = dog)
                }
            } else {
                dogLiveData.value = ApiResult.onError(error = RuntimeException(message = "Unsuccessful Response"))
            }
        }
    }
}
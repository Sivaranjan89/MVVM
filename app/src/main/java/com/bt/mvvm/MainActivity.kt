package com.bt.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize ViewModel
        //this declares the MainActivity as the viewModel owner
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Every observer from viewmodel can be observed from inside this method
        setupObservers()

        //Make the call to fetch from api
        viewModel.fetchRandomDog()
    }

    private fun setupObservers() {
        viewModel._dogLiveData.observe(this, Observer { result ->
            //Since we used sealed class, it will emit one state at a time
            //We can handle the result based on the state emitted
            //In this example we have two states for the Api, onSuccess and onError
            when(result) {
                is ApiResult.onSuccess -> {}
                is ApiResult.onError -> {}
            }
        })
    }
}
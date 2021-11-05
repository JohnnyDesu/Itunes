package com.example.itunesmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.itunesmovies.api.ItunesMoviesService
import com.example.itunesmovies.model.DataGetModel
import com.example.itunesmovies.model.DatalistModel
import retrofit2.Call
import retrofit2.Response

class IndexViewModel : ViewModel() {
    private val  itunesServices = ItunesMoviesService()

    val indexDataGet = MutableLiveData<List<DataGetModel>>()
    val indexDataLive: LiveData<List<DataGetModel>> get() = indexDataGet
    val indexLoadError = MutableLiveData<Boolean>()

    //Indirect Call fetch Data
    fun refresh(){
        getData()
    }

    //Fecth Data from Model
    private fun getData(){
        itunesServices.getItunes().enqueue(object : retrofit2.Callback<DatalistModel>{
            override fun onResponse(call: Call<DatalistModel>, response: Response<DatalistModel>) {
                indexDataGet.value = response.body()!!.results
                indexLoadError.value = false
            }
            override fun onFailure(call: Call<DatalistModel>, t: Throwable) {
                indexLoadError.value = true
            }
        })
    }
}
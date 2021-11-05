package com.example.itunesmovies.api

import com.example.itunesmovies.model.DatalistModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ItunesMoviesService {
    private val baseurl = "https://itunes.apple.com/"
    private val itunesApi: API
    init {
        itunesApi =
            Retrofit.Builder().baseUrl(baseurl).addConverterFactory(GsonConverterFactory.create())
                .build().create(API::class.java)
    }
    //return data get to Model
    fun getItunes(): Call<DatalistModel> {
        return itunesApi.getMango()
    }
}

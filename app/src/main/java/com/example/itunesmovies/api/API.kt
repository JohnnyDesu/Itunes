package com.example.itunesmovies.api

import com.example.itunesmovies.model.DatalistModel
import retrofit2.http.GET

interface API {
    @GET("search?term=star&amp;country=au&amp;media=movie&amp;all")
    fun getMango(): retrofit2.Call<DatalistModel>
}
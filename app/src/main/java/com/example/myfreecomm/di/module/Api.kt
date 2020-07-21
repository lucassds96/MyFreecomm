package com.example.myfreecomm.di.module

import com.example.myfreecomm.model.ItemDetail
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("data.json")
    fun getItens(): Call<List<ItemDetail>>
}
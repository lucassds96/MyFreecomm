package com.example.myfreecomm.di.module

import com.example.myfreecomm.model.ItemDetail
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @get: GET("data.json")
    val getItens: Observable<List<ItemDetail>>
}
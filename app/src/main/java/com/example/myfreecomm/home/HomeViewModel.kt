package com.example.myfreecomm.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfreecomm.di.module.Api
import com.example.myfreecomm.di.module.NetworkUtils
import com.example.myfreecomm.model.ItemDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    init {
        getData()
    }

    var showDetails= MutableLiveData <List<ItemDetail>>()
    var showError = MutableLiveData <Throwable>()

    private fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance(URL_API)
        val endpoint = retrofitClient.create(Api::class.java)
        val callback = endpoint.getItens()

        callback.enqueue(object : Callback<List<ItemDetail>> {
            override fun onFailure(call: Call<List<ItemDetail>>, t: Throwable) {
                showError.value = t
            }

            override fun onResponse(call: Call<List<ItemDetail>>, response: Response<List<ItemDetail>>) {
                response.body()?.forEach {
                    showDetails.value = response.body()
                }
            }
        })
    }

    companion object {
        const val URL_API = "https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/"
    }
}
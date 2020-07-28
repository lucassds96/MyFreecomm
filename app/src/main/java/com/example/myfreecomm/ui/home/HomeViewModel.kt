package com.example.myfreecomm.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfreecomm.di.module.Api
import com.example.myfreecomm.di.module.RetrofitClient
import com.example.myfreecomm.model.ItemDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    lateinit var api: Api

    init {
        getItemsDetail()
    }

    var showDetails= MutableLiveData <List<ItemDetail>>()
    var showError = MutableLiveData <Throwable>()

    private fun getItemsDetail(){
        val retrofit = RetrofitClient.instance
        api = retrofit.create(Api::class.java)
        compositeDisposable.add(api.getItens
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {showDetails.value = it },
                {showError.value = it}
        ))
    }

    public override fun onCleared(){
        super.onCleared()
        compositeDisposable.dispose()
    }
}
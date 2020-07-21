package com.example.myfreecomm.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AppBase: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}
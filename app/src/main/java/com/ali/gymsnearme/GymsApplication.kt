package com.ali.gymsnearme

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GymsApplication:Application(){

    init {
        application = this
    }
    companion object{
        private lateinit var application: GymsApplication
        fun getApplicationContext(): Context = application.applicationContext
    }
}
package com.metamamun.equipment

import android.app.Application
import com.google.firebase.FirebaseApp


import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TSportsApplication : Application() {
//    @Inject lateinit var bindingComponentProvider: Provider<CustomBindingComponentBuilder>
    

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
    
//    override fun onTrimMemory(level: Int) {
//        super.onTrimMemory(level)
//        try {
//            connectivityManager.unregisterNetworkCallback(heartBeatManager)
//        } catch (e: Exception) {
//            CustomAnalytics.logBreadCrumb("connectivity manager unregister error -> ${e.message}")
//        }
//    }
}
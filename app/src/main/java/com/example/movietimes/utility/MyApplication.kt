package com.example.movietimes.utility

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.firebase.FirebaseApp

class MyApplication: Application(), LifecycleObserver {

    companion object {
        @JvmStatic
        var appContext: MyApplication? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        init(context = this)
        FirebaseApp.initializeApp(this)
        registerActivityLifecycleCallbacks(AppLifecycleTracker())
    }

    private fun init(context: MyApplication) {
        appContext = context
    }
}
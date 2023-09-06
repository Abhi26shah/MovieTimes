package com.example.movietimes.receivers

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.MutableLiveData

/**
 * Created by Abhishek Shah on 15 November 2022
 *
 * Thinkitive Technologies Pvt. Ltd.
 *
 * The NetworkStateReceiver is a receiver which is responsible to update the application if the
 * application is connected to the internet or not.
 *
 * It updates whenever the application loses the internet connectivity or connects back to the
 * internet.
 *
 * @author Abhishek Shah
 */
class NetworkStateReceiver: BroadcastReceiver() {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent?) {
//        TODO("Functions When the Network State has been changed")
        context.let {
            val connectivityManager = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            isUserConnected.value =
                networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)?: false
                        || networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)?: false
        }
    }

    companion object {

        private const val TAG = "NetworkStateReceiver"

        @JvmStatic
        var isUserConnected = MutableLiveData<Boolean>()
        private set
    }
}
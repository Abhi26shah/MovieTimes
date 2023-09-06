package com.example.movietimes.retrofit

import android.content.Context
import com.example.movietimes.BuildConfig
import com.example.movietimes.service.APIService

class APIUtils {
    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL

        fun getApiConnectivityService(context: Context): APIService {
            return RetrofitClient.getClient(BASE_URL, context).create(APIService::class.java)
        }
    }
}
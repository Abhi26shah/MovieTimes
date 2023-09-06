package com.example.movietimes.service

import com.example.movietimes.manager.ErrorModel

interface ResponseListener<T> {
    fun onResponseReceived(response: T, requestCode: Int)
    fun onErrorReceived(error: ErrorModel, requestCode: Int)
    fun onLoading(isLoading: Boolean)
}
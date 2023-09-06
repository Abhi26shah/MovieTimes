package com.example.movietimes.manager

import android.net.ParseException
import android.util.Log
import com.google.gson.stream.MalformedJsonException
import com.example.movietimes.service.ResponseListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class NetworkManager {
    private var compositeDisposable: Disposable? = null
    private val tag: String = NetworkManager::class.java.simpleName

    // Function to call API and get response

    fun <V : Any> createAPIRequest(observables: Observable<V>, callBack: ResponseListener<V>) {
        callBack.onLoading(isLoading = true)
        compositeDisposable = observables
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<V>() {
                override fun onNext(t: V) {
                    callBack.onResponseReceived(t,0)
                    callBack.onLoading(isLoading = false)
                }

                override fun onError(e: Throwable) {
                    callBack.onErrorReceived(setUpErrors(e),0)
                    callBack.onLoading(isLoading = false)
                }

                override fun onComplete() {

                }


            })
    }

    // Function to return error received in a proper format

    private fun setUpErrors(t: Throwable): ErrorModel {
        Log.e(tag, "setUpError statusCode: " + "statusCode " + t.message)
        val errorModel = ErrorModel()
        try {
            when (t) {
                is SocketTimeoutException -> {
                    errorModel.errorCode = ResponseCodes.INTERNET_NOT_AVAILABLE
                    errorModel.errorMessage = ResponseCodes.logErrorMessage(errorModel.errorCode)
                }
                is TimeoutException -> {
                    errorModel.errorCode = ResponseCodes.URL_CONNECTION_ERROR
                    errorModel.errorMessage = ResponseCodes.logErrorMessage(errorModel.errorCode)
                }
                is ClassCastException -> {
                    errorModel.errorCode = ResponseCodes.MODEL_TYPE_CAST_EXCEPTION
                    errorModel.errorMessage = ResponseCodes.logErrorMessage(errorModel.errorCode)
                }
                is MalformedJsonException -> {
                    errorModel.errorCode = ResponseCodes.MODEL_TYPE_CAST_EXCEPTION
                    errorModel.errorMessage = ResponseCodes.logErrorMessage(errorModel.errorCode)
                }
                is ParseException -> {
                    errorModel.errorCode = ResponseCodes.MODEL_TYPE_CAST_EXCEPTION
                    errorModel.errorMessage = ResponseCodes.logErrorMessage(errorModel.errorCode)
                }
                is UnknownHostException -> {
                    errorModel.errorCode = ResponseCodes.INTERNET_NOT_AVAILABLE
                    errorModel.errorMessage = ResponseCodes.logErrorMessage(errorModel.errorCode)
                }
                else -> {
                    val errorMessage = (t as HttpException).response()?.errorBody()!!.string()
                    val responseCode = t.response()?.code()
                    if (responseCode != null) {
                        errorModel.errorCode = responseCode
                    }
                    errorModel.errorMessage = errorMessage
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            errorModel.errorCode = ResponseCodes.UNKNOWN_ERROR
            errorModel.errorMessage = ResponseCodes.logErrorMessage(errorModel.errorCode)
        } finally {
//            progressHide()
        }
        return errorModel
    }
}
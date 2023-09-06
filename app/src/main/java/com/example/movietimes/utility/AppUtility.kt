package com.example.movietimes.utility

import android.content.Context
import com.example.movietimes.dao.CountryCodes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by Abhishek Shah on 01 June 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */
class AppUtility(val context: Context) {
    companion object {
        /**
         * Provides the class name as TAG variable whenever required.
         *
         * Mostly used for Logging the data in LogCat.
         */
        private const val TAG = "AppUtility"
    }

    fun getCountryCodes(): ArrayList<CountryCodes> {
        var countryCodes = ArrayList<CountryCodes>()
        val countryCodesJson: String
        try {
            val inputStream = context.assets.open("calling_codes.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            countryCodesJson = String(buffer)
            val typeToken = object : TypeToken<ArrayList<CountryCodes>>() {}.type
            countryCodes = Gson().fromJson(countryCodesJson, typeToken)
        } catch (exception: Exception) {
            Logger.error("$TAG Exception", exception.message.toString())
            exception.printStackTrace()
        }

        return countryCodes
    }
}
package com.example.movietimes.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.movietimes.constants.PreferenceConstants

/**
 * Created by Abhishek Shah on 14/11/22
 */
class PreferenceManager {
    companion object {
        private const val TAG = "PreferenceManager"
        private const val MODE = Context.MODE_PRIVATE

        /**
         * This function reads the stored firebase cloud messaging service device token from the
         * shared preferences and returns it to the caller functions.
         *
         * @param context The context from which the function is being called
         *
         * @param preferenceKey The key used for storing and accessing the stored firebase
         * messaging token.
         *
         * @param defaultValue The value to be returned if the token is not available or
         * accessible in the shared preferences.
         * The default value can be null as the function is capable of returning a null value.
         *
         * @param preferenceName The Shared Preference Name from which the data is to be read
         * and accessed in the shared preferences.
         * The default value of the preferenceName is set to the Firebase Shared Preference
         * name [PreferenceConstants.FIREBASE_PREFERENCE] and can be changed if required.
         *
         * @return A [String] value which represents the Firebase Cloud Messaging Token.
         *
         * @author Abhishek Shah
         */
        fun retrieveFirebaseToken(
            context: Context,
            preferenceKey: String,
            defaultValue: String?,
            preferenceName: String = PreferenceConstants.FIREBASE_PREFERENCE
        ): String? {
            return getSharedPreferences(context = context, preferenceName)?.getString(
                preferenceKey,
                defaultValue
            )
        }

        /**
         * This function stores the firebase cloud messaging service device token to the
         * shared preferences.
         *
         * @param context The context from which the function is being called
         *
         * @param preferenceKey The key used for storing and accessing the stored firebase
         * messaging token.
         *
         * @param value The value to be stored to the shared preference for the specified key.
         *
         * @param preferenceName The Shared Preference Name from which the data is to be read
         * and accessed in the shared preferences.
         * The default value of the preferenceName is set to the Firebase Shared Preference
         * name [PreferenceConstants.FIREBASE_PREFERENCE] and can be changed if required.
         *
         * @see retrieveFirebaseToken
         *
         * @author Abhishek Shah
         */
        fun saveFirebaseToken(
            context: Context,
            preferenceKey: String,
            value: String,
            preferenceName: String = PreferenceConstants.FIREBASE_PREFERENCE
        ) {
            getPreferenceEditor(context = context, preferenceName)?.putString(preferenceKey, value)
                ?.commit()
        }

        private fun getSharedPreferences(
            context: Context,
            preferenceName: String
        ): SharedPreferences? {
            return context.getSharedPreferences(preferenceName, MODE)
        }

        private fun getPreferenceEditor(
            context: Context,
            preferenceName: String
        ): SharedPreferences.Editor? {
            return getSharedPreferences(context, preferenceName)?.edit()
        }
    }
}
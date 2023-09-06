package com.example.movietimes.constants


/**
 * Created by Abhishek Shah on 15 November 2022
 *
 * Thinkitive Technologies Pvt. Ltd.
 */
class PreferenceConstants {
    companion object {
        /**
         * Provides the class name as TAG variable whenever required.
         *
         * Mostly used for Logging the data in LogCat.
         */
        private const val TAG = "PreferenceConstants"

        /**
         * Shared Preferences Name for Preferences File to be used when any data related to the Firebase is to
         * be stored in the Shared Preferences.
         */
        const val FIREBASE_PREFERENCE = "firebase_preference"

        /**
         * Shared Preferences Name for Preferences File to be used when ny data related to the application
         * is to be stored in the Shared Preferences.
         */
        const val APPLICATION_PREFERENCE = "application_preference"

        /**
         * Shared Preferences Key for the Firebase Cloud Messaging Services token to be stored or
         * retrieved from the respective Shared Preferences File.
         */
        const val FIREBASE_NOTIFICATION_TOKEN = "firebase_notification_token"
    }
}
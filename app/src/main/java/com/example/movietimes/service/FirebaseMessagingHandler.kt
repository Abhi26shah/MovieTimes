package com.example.movietimes.service

import android.content.Context
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.example.movietimes.constants.PreferenceConstants
import com.example.movietimes.storage.PreferenceManager
import com.example.movietimes.utility.Logger

class FirebaseMessagingHandler : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Logger.info(TAG, message.notification?.body.toString())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Logger.info(TAG, token)
    }

    companion object {
        const val TAG = "FirebaseMessagingHandler"

        fun getNewDeviceToken(context: Context) {
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Logger.warn(
                        TAG,
                        "Fetching FCM registration token failed with exception ${task.exception?.message}."
                    )
                    return@addOnCompleteListener
                }
                val token = task.result
                PreferenceManager.saveFirebaseToken(
                    context = context,
                    PreferenceConstants.FIREBASE_NOTIFICATION_TOKEN,
                    token
                )
                Logger.info(TAG, token)
            }
        }
    }
}
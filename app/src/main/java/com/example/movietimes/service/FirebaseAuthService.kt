package com.example.movietimes.service

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.example.movietimes.R
import com.example.movietimes.utility.Logger
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


/**
 * Created by Abhishek Shah on 06 June 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */
class FirebaseAuthService(val context: Activity) {



    private var firebaseAuthService: FirebaseAuthService? = null
    private lateinit var phoneAuthOptions: PhoneAuthOptions
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        firebaseAuth.setLanguageCode("en")
    }

    companion object {
        /**
         * Provides the class name as TAG variable whenever required.
         *
         * Mostly used for Logging the data in LogCat.
         */
        private const val TAG = "FirebaseAuthService"

        const val GOOGLE_AUTH_CODE = 1001
    }

    fun authenticateWithPhoneNumber(
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(context) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun authenticateUserOTP(
        otp: String,
        verificationCode: String,
        callback: (task: Task<AuthResult>) -> Unit = {}
    ) {
        val credential = PhoneAuthProvider.getCredential(verificationCode, otp)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            Logger.info(TAG, task.exception?.message.toString())
            callback(task)
        }
    }

    fun authenticateWithGoogle() {
        BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(context.getString(R.string.default_web_client_id))
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            ).build()
    }

    fun signInWithGoogleAccount(idToken: String, onTaskComplete: (task: Task<AuthResult>) -> Unit) {
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(firebaseCredential)
            .addOnCompleteListener { task ->
                onTaskComplete(task)
            }
    }
}
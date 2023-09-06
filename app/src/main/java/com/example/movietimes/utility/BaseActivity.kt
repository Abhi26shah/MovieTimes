package com.example.movietimes.utility

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import com.example.movietimes.receivers.NetworkStateReceiver
import com.example.movietimes.service.FirebaseAuthService
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken

open class BaseActivity : ComponentActivity() {

    companion object {
        var instance: BaseActivity? = null
            private set

        private const val TAG = "BaseActivity"
    }

    private var networkStateReceiver: NetworkStateReceiver? = null

    fun getFirebaseUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    private fun init(): BaseActivity {
        if (instance == null) {
            instance = this
        }
        return instance!!
    }

    override fun onResume() {
        super.onResume()
        init()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)

        if (networkStateReceiver == null) {
            networkStateReceiver = NetworkStateReceiver()
        }
        registerReceiver(networkStateReceiver, intentFilter)
    }

    fun authenticatePhoneNumber(
        phoneNumber: String,
        callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {
        FirebaseAuthService(this).authenticateWithPhoneNumber(
            phoneNumber = phoneNumber,
            callbacks = callback
        )
    }

    fun authenticateWithGoogleAccount() {
        FirebaseAuthService(this).authenticateWithGoogle()
    }

    fun verifyUserOTP(
        otp: String,
        verificationID: String,
        callback: (task: Task<AuthResult>) -> Unit = {}
    ) {
        FirebaseAuthService(this).authenticateUserOTP(
            otp = otp,
            verificationCode = verificationID,
            callback = callback
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkStateReceiver)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    open fun onGoogleAuthComplete(task: Task<AuthResult>) {
//    TODO("Implement it wherever GoogleAuthentication is Required")
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            FirebaseAuthService.GOOGLE_AUTH_CODE -> {
                try {
                    val credential = GoogleSignIn.getSignedInAccountFromIntent(data)
                    val idToken = credential.result.idToken
                    if (idToken != null) {
                        FirebaseAuthService(this).signInWithGoogleAccount(idToken) {task ->

                        }
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    Logger.error(TAG, ex.message.toString())
                }
            }
        }
    }
}
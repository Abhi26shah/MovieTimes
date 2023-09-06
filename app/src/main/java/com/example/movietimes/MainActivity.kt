package com.example.movietimes

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.example.movietimes.modules.navigation.AppNavigation
import com.example.movietimes.ui.theme.Black
import com.example.movietimes.ui.theme.MovieTimesTheme
import com.example.movietimes.utility.BaseActivity
import com.example.movietimes.utility.Logger
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseUser
import java.util.Locale

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        setContent {
            MovieTimesTheme {
                val systemUIController = rememberSystemUiController()
                val darkTheme = !isSystemInDarkTheme()
                DisposableEffect(systemUIController, darkTheme) {
                    systemUIController.setStatusBarColor(
                        color = Black
                    )
                    onDispose { }
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                        .statusBarsPadding()
                        .background(
                            color = Color.Black
                        ),
                    color = Color.Black
                ) {
                    AppNavigation(context = this)
                }
            }
        }
    }
}
package com.example.movietimes.modules.onboarding

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movietimes.R
import com.example.movietimes.constants.NavConstants
import com.example.movietimes.ui.theme.AppInitialBackground
import com.example.movietimes.utility.BaseActivity


/**
 * Created by Abhishek Shah on 25 May 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */

@Composable
fun SplashScreen(navigate: (route: String, closePrev: Boolean) -> Unit = {_, _ ->}) {

    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper())
            .postDelayed({
                if (BaseActivity.instance?.getFirebaseUser() == null)
                    navigate(NavConstants.LoginNavigation.route, true)
                else
                    navigate(NavConstants.HomeNavigation.route, true)
        }, 1500)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = AppInitialBackground,
                alpha = 1f
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.movie_times_logo),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(0.4f)
        )
    }
}


@Composable
@Preview
fun SplashScreenPreview() {
    SplashScreen()
}
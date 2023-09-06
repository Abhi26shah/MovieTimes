package com.example.movietimes.modules.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movietimes.constants.NavConstants
import com.example.movietimes.constants.Navigation
import com.example.movietimes.modules.home.HomeScreen
import com.example.movietimes.modules.onboarding.LoginScreen
import com.example.movietimes.modules.onboarding.SplashScreen


/**
 * Created by Abhishek Shah on 26 April 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */

@Composable
fun AppNavigation(context: Activity) {
    val navController = rememberNavController()
    NavHost(navController = navController,
    startDestination = NavConstants.SplashNavigation.route) {

        composable("${Navigation.BackNavigation.name}/{closeApp}", arguments = listOf(navArgument("closeApp") {
            defaultValue = false
            type = NavType.BoolType
        })) {
            val closeApp = it.arguments?.getBoolean("closeApp")?: false
            if (closeApp) {
                context.finish()
            } else {
                navController.popBackStack()
            }
        }

        composable(NavConstants.SplashNavigation.route) {
            SplashScreen { route, closePrev ->
                if (closePrev) {
                    navController.clearRouteAndNavigate(route)
                } else {
                    navController.navigate(route)
                }
            }
        }

        composable(NavConstants.LoginNavigation.route) {
            LoginScreen { route, closePrev ->
                if (closePrev) {
                    navController.clearRouteAndNavigate(route)
                } else {
                    navController.navigate(route)
                }
            }
        }

        composable(NavConstants.HomeNavigation.route) {
            HomeScreen { route, closePrev ->
                if (closePrev) {
                    navController.clearRouteAndNavigate(route)
                } else {
                    navController.navigate(route)
                }
            }
        }
    }
}

fun NavController.clearRouteAndNavigate(route: String) {
    popBackStack(graph.startDestinationId, true)
    graph.setStartDestination(route)
    navigate(route)
}

fun NavController.navigateWithSingleTop(route: String) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
}
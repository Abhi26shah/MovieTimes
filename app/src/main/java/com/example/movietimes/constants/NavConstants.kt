package com.example.movietimes.constants

import com.example.movietimes.R


/**
 * Created by Abhishek Shah on 26 April 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */
sealed class NavConstants(val id: String,val title: String,val route: String, val icon: Int = 0) {

    object HomeNavigation: NavConstants(
        id = "home",
        title = "Home",
        route = Navigation.HomeScreenNav.name
    )

    object LoginNavigation: NavConstants(
        id = "login",
        title = "Login",
        route = Navigation.LoginScreenNav.name
    )

    object SplashNavigation: NavConstants(
        id = "splash",
        title = "Splash",
        route = Navigation.SplashScreenNav.name
    )

    object DashboardNavigation: NavConstants(
        id = "dashboard",
        title = "Home",
        route = Navigation.HomeFragNav.name,
        icon = R.drawable.ic_home
    )

    object MoviesNavigation: NavConstants(
        id = "movies",
        title = "Movies",
        route = Navigation.MovieFragNav.name,
        icon = R.drawable.ic_movie
    )

    object SeriesNavigation: NavConstants(
        id = "series",
        title = "Series",
        route = Navigation.SeriesFragNav.name,
        icon = R.drawable.ic_series
    )

    object SearchNavigation: NavConstants(
        id = "search",
        title = "Search",
        route = Navigation.SearchFragNav.name,
        icon = R.drawable.ic_search
    )

}

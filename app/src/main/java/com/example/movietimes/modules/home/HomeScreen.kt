package com.example.movietimes.modules.home

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceAround
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movietimes.R
import com.example.movietimes.constants.NavConstants
import com.example.movietimes.constants.Navigation
import com.example.movietimes.modules.navigation.navigateWithSingleTop
import com.example.movietimes.ui.theme.AppBackground
import com.example.movietimes.ui.theme.BottomBarBackground
import com.example.movietimes.ui.theme.TextRegular16
import com.example.movietimes.ui.theme.TextSemiBold20
import com.example.movietimes.utility.Logger
import com.example.movietimes.viewmodel.MovieDBViewModel


/**
 * Created by Abhishek Shah on 19 April 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */

@Composable
fun HomeScreen(navigate: (route: String, closePrev: Boolean) -> Unit = { _, _ -> }) {

    val navController = rememberNavController()

    val bottomMenu = arrayListOf(
        NavConstants.DashboardNavigation,
        NavConstants.MoviesNavigation,
        NavConstants.SeriesNavigation,
        NavConstants.SearchNavigation
    )

    var selectedItem: NavConstants by remember {
        mutableStateOf(bottomMenu[0])
    }

    BackHandler(enabled = true) {
        if (navController.currentDestination?.route.equals(
                NavConstants.DashboardNavigation.route,
                true
            )
        ) {
            navigate("${Navigation.BackNavigation.name}/true", false)
        } else {
            selectedItem = bottomMenu[0]
            navController.navigateWithSingleTop(bottomMenu[0].route)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = AppBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                AppTopBar()

                AppBottomNavigation(
                    modifier = Modifier.weight(1f),
                    navController = navController
                ) { _, _ ->

                }

                AppBottomBar(
                    bottomMenu = bottomMenu,
                    selectedItem = selectedItem,
                    navController = navController
                ) {
                    selectedItem = it
                    navController.navigateWithSingleTop(it.route)
                }
            }
        }
    }
}

@Composable
fun AppTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
            .height(60.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.movie_times_nav_icon),
                contentDescription = null
            )

            Text(
                text = stringResource(id = R.string.app_name),
                style = TextSemiBold20
            )

            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.movie_times_logo),
                contentDescription = null
            )
        }
    }
}

@Composable
fun AppBottomBar(
    bottomMenu: ArrayList<NavConstants>,
    selectedItem: NavConstants,
    navController: NavHostController,
    onItemSelected: (NavConstants) -> Unit
) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(
                brush = BottomBarBackground,
                alpha = 1f
            ),
        contentAlignment = Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = CenterVertically
        ) {
            bottomMenu.forEach {
                MenuItem(
                    modifier = Modifier.weight(1f),
                    item = it,
                    isSelected = selectedItem.id == it.id,
                    onMenuItemClicked = { selected ->
                        onItemSelected(selected)
                    }
                )
            }
        }
    }
}

@Composable
fun MenuItem(
    modifier: Modifier,
    item: NavConstants,
    isSelected: Boolean,
    onMenuItemClicked: (item: NavConstants) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Center
    ) {
        Column(horizontalAlignment = CenterHorizontally,
            verticalArrangement = SpaceAround,
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                onMenuItemClicked(item)
            }) {
            Image(
                imageVector = ImageVector.vectorResource(id = item.icon),
                contentDescription = null
            )

            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = item.title,
                    style = TextRegular16
                )
            }
        }
    }
}

@Composable
fun AppBottomNavigation(
    modifier: Modifier,
    navController: NavHostController,
    navigate: (route: String, closePrev: Boolean) -> Unit = { _, _ -> }
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        NavHost(
            navController = navController,
            startDestination = NavConstants.DashboardNavigation.route
        ) {

            composable(NavConstants.DashboardNavigation.route) {
                DashboardScreen()
            }

            composable(NavConstants.MoviesNavigation.route) {
                MoviesScreen()
            }

            composable(NavConstants.SeriesNavigation.route) {
                SeriesScreen()
            }

            composable(NavConstants.SearchNavigation.route) {
                SearchScreen()
            }

        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
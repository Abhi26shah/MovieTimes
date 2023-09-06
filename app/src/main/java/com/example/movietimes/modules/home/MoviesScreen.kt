package com.example.movietimes.modules.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movietimes.BuildConfig
import com.example.movietimes.ui.theme.TextSemiBold14
import com.example.movietimes.utility.loadRemoteImage
import com.example.movietimes.utility.Logger
import com.example.movietimes.utility.items
import com.example.movietimes.viewmodel.MovieDBViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by Abhishek Shah on 12 June 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesScreen() {
    val viewModel: MovieDBViewModel = viewModel()
    val context = LocalContext.current

    val upcomingMoviesList = viewModel.upcomingMoviesList.collectAsState()
    val genreWithMovies = viewModel.genreWithMovies


    LaunchedEffect(key1 = Unit, block = {
        viewModel.getUpcomingMovies(context = context)
        viewModel.getGenresList(context = context)
    })

    val state = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        upcomingMoviesList.value.size
    }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 =state.currentPage) {
        Logger.error("PAGER",state.currentPage.toString())
        delay(3000)
        scope.launch {
            state.animateScrollToPage(if (state.canScrollForward){state.currentPage+1} else {0})
        }
    }


    Box(
        modifier = Modifier
            .background(color = Color.Transparent)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            LazyColumn(
                userScrollEnabled = true,
                content = {
                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            HorizontalPager(state = state) {
                                val url =
                                    BuildConfig.IMAGE_BASE_URL + upcomingMoviesList.value[it].backdropPath
                                Image(
                                    painter = loadRemoteImage(
                                        context = context, url = url
                                    ), contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp),
                                    contentScale = ContentScale.Crop,
                                    alignment = Alignment.TopStart
                                )
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                        }
                    }
                    items(genreWithMovies) {
                        Row(modifier = Modifier.padding(start = 16.dp)) {
                            Text(
                                text = "${it.key.name} Movies",
                                style = TextSemiBold14,
                                color = Color.White
                            )
                        }

                        LazyRow(content = {
                            items(it.value) {
                                val url = BuildConfig.IMAGE_BASE_URL + it.posterPath
                                Row {
                                    Image(
                                        modifier = Modifier
                                            .height(150.dp)
                                            .width(100.dp)
                                            .clip(
                                                RoundedCornerShape(10.dp)
                                            ),
                                        painter = loadRemoteImage(context = context, url = url),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit
                                    )

                                    Spacer(modifier = Modifier.width(12.dp))
                                }
                            }
                        }, contentPadding = PaddingValues(16.dp))
                    }
                })
        }
    }
}

@Preview
@Composable
fun MoviesScreenPreview() {
    MoviesScreen()
}
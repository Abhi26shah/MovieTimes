package com.example.movietimes.modules.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movietimes.BuildConfig
import com.example.movietimes.R
import com.example.movietimes.ui.theme.TextSemiBold14
import com.example.movietimes.utility.TextInputField
import com.example.movietimes.utility.loadRemoteImage
import com.example.movietimes.viewmodel.MovieDBViewModel
import kotlinx.coroutines.delay


/**
 * Created by Abhishek Shah on 12 June 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen() {
    var query by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val viewModel: MovieDBViewModel = viewModel()

    val searchedItems = viewModel.searchedMoviesList.collectAsState()
    
    LaunchedEffect(key1 = query, block = {
        if (query.isNotEmpty()) {
            delay(1500)
            viewModel.getSearchedList(context, query)
        }
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            TextInputField(
                value = query,
                onValueChanged = {
                    query = it
                },
                placeholder = stringResource(id = R.string.search_by_movie_series_name)
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (query.isEmpty()) {
                Column {
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.recent_searches),
                            style = TextSemiBold14,
                            color = Color.White
                        )

                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                            contentDescription = null
                        )
                    }

                    Spacer(modifier = Modifier.height(40.dp))
                }
            }

            LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(120.dp), content = {
                items(searchedItems.value) {
                    val url = BuildConfig.IMAGE_BASE_URL+it.posterPath
                    Row(horizontalArrangement = Arrangement.Center) {
                        if (it.posterPath.isNullOrEmpty()) {
                            Image(bitmap = ImageBitmap.imageResource(id = R.drawable.no_image_poster), contentDescription = null,
                            modifier = Modifier
                                .height(150.dp)
                                .width(100.dp)
                                .clip(
                                    RoundedCornerShape(10.dp)
                                ),contentScale = ContentScale.FillBounds)
                        } else {
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
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }, horizontalArrangement = Arrangement.SpaceBetween)

        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}
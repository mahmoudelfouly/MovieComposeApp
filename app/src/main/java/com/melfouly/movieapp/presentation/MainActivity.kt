package com.melfouly.movieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.melfouly.movieapp.domain.model.DiscoverMoviesResponse
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.presentation.theme.MovieAppTheme
import com.melfouly.movieapp.presentation.ui.movie.MovieScreen
import com.melfouly.movieapp.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val x = viewModel.moviesList.collectAsState().value
                    var y: DiscoverMoviesResponse? = null
                    when (x) {
                        is NetworkResult.Loading -> {
                        }

                        is NetworkResult.Success -> {
                            y = x.data
                            Timber.d("Success: Total Results:${y.totalResults}, Page:${y.page}, Total Pages:${y.totalPages}, Results:${y.results.size}")
                            Timber.d("Success: Result[0] Title:${y.results[0].title}, Keywords:${y.results[0].keywords}")
                            MovieScreen(y, modifier = Modifier.fillMaxSize())
                        }

                        is NetworkResult.Failure -> {

                        }
                    }

                }
            }
        }
    }
}

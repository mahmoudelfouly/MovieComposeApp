package com.melfouly.movieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.melfouly.movieapp.domain.model.DiscoverMoviesResponse
import com.melfouly.movieapp.domain.model.NetworkResult
import com.melfouly.movieapp.presentation.ui.movie.MovieScreen
import com.melfouly.movieapp.presentation.ui.theme.MovieAppTheme
import com.melfouly.movieapp.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

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
                    val movieList by viewModel.moviesList.collectAsState()
                    when (movieList) {
                        is NetworkResult.Loading -> {
                            Box {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }

                        is NetworkResult.Success -> {
                            MovieScreen(
                                (movieList as NetworkResult.Success<DiscoverMoviesResponse>).data,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        is NetworkResult.Failure -> {
                            Text(
                                text = "Something went wrong.",
                                modifier = Modifier.fillMaxSize(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                }
            }
        }
    }
}

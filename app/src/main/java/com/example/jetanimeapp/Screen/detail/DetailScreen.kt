package com.example.jetanimeapp.Screen.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.jetanimeapp.R
import com.example.jetanimeapp.ViewModelFactory
import com.example.jetanimeapp.di.Injection
import com.example.jetanimeapp.model.EpisodeAnime
import com.example.jetanimeapp.ui.theme.common.UiState

@Composable
fun DetailScreen(
    animeId: Int,
    viewModel: DetailScreenViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAnimeById(animeId)
                Log.d("Loading", uiState.toString())
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    title = data.title,
                    name = data.name,
                    photoUrl = data.photoUrl,
                    description = data.description,
                    genre = data.genre,
                    episode = data.episode,
                    onBackClick = navigateBack,
                )
                Log.d("Detail", uiState.toString())
            }

            is UiState.Error -> {}
        }

    }
}

@Composable
fun DetailContent(
    title: String,
    name: String,
    photoUrl: String,
    description: String,
    genre: List<String>,
    episode: List<EpisodeAnime>,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(400.dp)
                .background(color = Color.LightGray)
                .fillMaxWidth(),
        )
        //Column Description
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround
//            Arrangement.SpaceAround
        ) {
            Column {
                Text(text = title)
                Text(text = name)
            }
            Column {
                Text(text = R.string.description.toString())
                Text(text = description)
            }
//            Text(text = genre)
        }
        //Column All Episode
        Text(text = R.string.episode.toString())
//        Box(modifier = Modifier.fillMaxWidth()) {
//            LazyColumn(
//            ) {
////                items(AnimeData.anime, key = { it.id }) { anime ->
//                items(, key = { it.id }) { anime ->
//                    AnimeEpisode(
//                        modifier = Modifier.fillMaxWidth() //buat styling
//                    )
//                }
//            }
//        }
//
//    }
    }
}

@Composable
fun AnimeEpisode(
    anime: EpisodeAnime, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(180.dp)
            .padding(bottom = 5.dp)
            .fillMaxWidth()
    ) {
        Column {
            AsyncImage(
                model = anime.photoUrl,
                contentDescription = R.string.photo_anime.toString(),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
            Text(text = anime.title)
        }
    }
}
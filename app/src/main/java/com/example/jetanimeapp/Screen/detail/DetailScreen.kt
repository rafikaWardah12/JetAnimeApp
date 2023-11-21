package com.example.jetanimeapp.Screen.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.jetanimeapp.R
import com.example.jetanimeapp.ViewModelFactory
import com.example.jetanimeapp.di.Injection
import com.example.jetanimeapp.model.EpisodeAnime
import com.example.jetanimeapp.ui.theme.Shapes
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
//                AllEpisode(
//                    episode = data.episode
//                )
                Log.d("title", data.title)
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
//    Text(text = title)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(30.dp)

    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(400.dp)
                .background(color = Color.LightGray)
                .fillMaxWidth()
        )
        //Column Description
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.SpaceAround
//            Arrangement.SpaceAround
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    ))
                Text(text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ))
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column {
                Text(
                    text = stringResource(R.string.description),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ))
                Text(text = description)
            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        AllEpisode(episode)
        Log.d("allepisode", episode.toString())
    }
}


@Composable
fun AllEpisode(
    episode: List<EpisodeAnime>,
    modifier: Modifier = Modifier,
) {
    //Column All Episode
    Text(text = stringResource(R.string.episode),
        style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Bold
        ))
//    AnimeEpisode(title = episode.first().title, photoUrl = episode.first().photoUrl )
    Box(modifier = modifier.height(300.dp) ) {
        LazyHorizontalGrid(
            modifier = Modifier,
            rows = GridCells.Fixed(1),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(episode) { anime ->
                AnimeEpisode(
//                    modifier = Modifier.fillMaxWidth(),
                    title = anime.title,
                    photoUrl = anime.photoUrl
                )
            }
        }
    }
}


@Composable
fun AnimeEpisode(
    modifier: Modifier = Modifier,
    title: String,
    photoUrl: String,
) {
    Card(
        modifier = modifier
            .height(150.dp)
            .padding(bottom = 5.dp)
            .fillMaxWidth()
    ) {
        Column {
            AsyncImage(
                model = photoUrl,
                contentDescription = R.string.photo_anime.toString(),
                modifier = Modifier
                    .size(200.dp)
                    .clip(Shapes.small)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
            Text(text = title)
        }
    }
}

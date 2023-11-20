package com.example.jetanimeapp.model

data class Anime(
    val id: Int,
    val title: String,
    val name: String,
    val photoUrl: String,
    val description: String,
    val genre: List<String>,
    val episode: List<EpisodeAnime>,
)

data class EpisodeAnime(
val id: Int,
val title: String,
val photoUrl: String,
)

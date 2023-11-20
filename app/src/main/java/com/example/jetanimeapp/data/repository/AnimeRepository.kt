package com.example.jetanimeapp.data.repository

import com.example.jetanimeapp.data.dummy.AnimeData
import com.example.jetanimeapp.model.Anime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Singleton

class AnimeRepository {
    private val anime = mutableListOf<Anime>()

    init {
        if (anime.isEmpty()) {
            AnimeData.dummyAnime.forEach {
                anime.add(it)
            }
        }
    }

    fun getAnime(): Flow<List<Anime>> {
        return flowOf(anime)
    }

    fun getAnimeById(id: Int): Anime {
        return anime.first {
            it.id == id
        }
    }

    fun getAnimeWithInisial(): List<Anime> = AnimeData.dummyAnime

    fun searchAnimeWithInisial(query: String): List<Anime> {
        return AnimeData.dummyAnime.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    companion object {
        @Volatile
        private var instance: AnimeRepository? = null

        fun getInstance(): AnimeRepository =
            instance ?: synchronized(this) {
                AnimeRepository().apply {
                    instance = this
                }
            }
    }
}
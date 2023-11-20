package com.example.jetanimeapp

import androidx.lifecycle.ViewModel
import com.example.jetanimeapp.data.repository.AnimeRepository
import com.example.jetanimeapp.model.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//class jetAnimeViewModel (private val repository: AnimeRepository): ViewModel() {
//    private val _groupedAnime = MutableStateFlow(
//        repository.getAnime()
//            .sortedBy { it.name }
//            .groupBy { it.name[0] }
//    )
//    val groupedAnime: StateFlow<Map<Char, List<Anime>>> get() = _groupedAnime
//}
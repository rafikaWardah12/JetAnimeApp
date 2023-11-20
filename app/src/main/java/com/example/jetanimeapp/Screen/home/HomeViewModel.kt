package com.example.jetanimeapp.Screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetanimeapp.data.repository.AnimeRepository
import com.example.jetanimeapp.model.Anime
import com.example.jetanimeapp.ui.theme.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: AnimeRepository) : ViewModel() {
    private val _groupedAnime: MutableStateFlow<UiState<List<Anime>>> =
        MutableStateFlow(UiState.Loading)

    //    private val _groupedAnime = MutableStateFlow(
//        repository.getAnime()
//            .sortedBy { it.name }
//            .groupBy { it.name[0] }
//    )

    //Dipanggil di UI
    val groupedAnime: StateFlow<UiState<List<Anime>>>
        get() = _groupedAnime

    fun getAnime() {
        viewModelScope.launch {
            repository.getAnime()
                .catch {
                    _groupedAnime.value = UiState.Error(it.message.toString())
                }
                .collect { anime ->
                    _groupedAnime.value = UiState.Success(anime)
                }
        }
    }
}
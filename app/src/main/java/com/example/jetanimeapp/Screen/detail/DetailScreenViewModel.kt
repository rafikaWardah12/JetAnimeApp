package com.example.jetanimeapp.Screen.detail

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetanimeapp.data.repository.AnimeRepository
import com.example.jetanimeapp.model.Anime
import com.example.jetanimeapp.ui.theme.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailScreenViewModel (
    private val repository: AnimeRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Anime>> =
        MutableStateFlow(UiState.Loading)
    //Dipanggil di UI
    val uiState: StateFlow<UiState<Anime>>
        get() = _uiState

    fun getAnimeById(animeId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(
                repository.getAnimeById(animeId)
            )
        }
    }
}
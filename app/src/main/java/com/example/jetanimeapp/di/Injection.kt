package com.example.jetanimeapp.di

import com.example.jetanimeapp.data.repository.AnimeRepository

object Injection {
    fun provideRepository(): AnimeRepository{
        return AnimeRepository.getInstance()
    }
}
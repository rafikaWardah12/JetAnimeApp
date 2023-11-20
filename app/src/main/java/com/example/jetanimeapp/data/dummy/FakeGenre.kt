package com.example.jetanimeapp.data.dummy

import com.example.jetanimeapp.model.Genre

object FakeGenre {
    val dummyGenre = listOf<Genre>(
        Genre(
            1,
            "romance",
        ),
        Genre(
            2,
            "action"
        ),
        Genre(
            3,
            "comedy"
        ),
        Genre(
            4,
            "fantasy"
        ),
        Genre(
            5,
            "slice of life"
        )
    )

    val mappedDummyGenre = dummyGenre.associate { it.id to it.nameofGenre }
}
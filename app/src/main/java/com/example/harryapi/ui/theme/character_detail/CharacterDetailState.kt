package com.loki.harrypotterapp.ui.character_detail

import com.example.harryapi.domain.models.CharacterItem

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val message: String = "",
    val characterDetail: CharacterItem? = null
)

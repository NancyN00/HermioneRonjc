package com.example.harryapi.ui.theme.character_list

import com.example.harryapi.domain.models.CharacterItem


data class CharacterListState(
    val isLoading: Boolean = false,
    val message: String = "",
    val characterList: List<CharacterItem> = emptyList()
)


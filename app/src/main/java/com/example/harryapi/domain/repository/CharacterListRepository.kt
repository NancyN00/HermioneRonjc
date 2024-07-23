package com.example.harryapi.domain.repository


import com.example.harryapi.domain.models.CharacterItem
import com.example.harryapi.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterListRepository {

    fun getCharacterList(): Flow<Resource<List<CharacterItem>>>
}
package com.example.harryapi.data.remote

import com.example.harryapi.domain.models.CharacterItem
import retrofit2.http.GET

interface HarryPotterApi {

    @GET("characters")
    suspend fun getCharacterList(): List<CharacterItem>
}
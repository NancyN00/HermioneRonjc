package com.example.harryapi.di
import com.example.harryapi.data.remote.HarryPotterApi
import com.example.harryapi.data.repository.CharacterListRepositoryImpl
import com.example.harryapi.domain.repository.CharacterListRepository
import com.example.harryapi.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitApi(): HarryPotterApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HarryPotterApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCharacterListRepository(api: HarryPotterApi): CharacterListRepository {
        return CharacterListRepositoryImpl(api)
    }
}
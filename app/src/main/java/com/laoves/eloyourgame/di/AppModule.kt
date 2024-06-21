package com.laoves.eloyourgame.di

import com.laoves.eloyourgame.common.Constants.ELO_YOUR_GAME_URL
import com.laoves.eloyourgame.data.remote.elo_your_game.EloYourGameApi
import com.laoves.eloyourgame.data.repository.EloYourGameRepositoryImpl
import com.laoves.eloyourgame.domain.repository.EloYourGameRepository
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

    @Provides
    @Singleton
    fun provideEloYourGameApi(): EloYourGameApi {
        return Retrofit.Builder()
            .baseUrl(ELO_YOUR_GAME_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EloYourGameApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEloYourGameRepository(api: EloYourGameApi): EloYourGameRepository {
        return EloYourGameRepositoryImpl(api)
    }
}
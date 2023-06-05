package com.open.composeexpo.di

import android.content.Context
import com.open.composeexpo.ComposeExpoApplication
import com.open.composeexpo.data.api.WeatherApi
import com.open.composeexpo.domain.repositories.WeatherRepository
import com.open.composeexpo.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): ComposeExpoApplication {
        return app as ComposeExpoApplication
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(
        api: WeatherApi
    ) : WeatherRepository {
        return WeatherRepository(api)
    }

    @Singleton
    @Provides
    fun provideWeatherApi() : WeatherApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(WeatherApi::class.java)
    }
}
package com.open.composeexpo.domain.repositories

import com.open.composeexpo.data.api.WeatherApi
import com.open.composeexpo.data.api.models.WeatherResponse
import com.open.composeexpo.utils.Constants
import com.open.weather.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class WeatherRepository @Inject constructor(
    private val api: WeatherApi
){

    suspend fun getWeather(city: String) : Resource<WeatherResponse> {
        val response = try {
            api.weather(city, Constants.API_KEY)
        } catch(e: Exception) {
            return Resource.Error("Unknown error encounter")
        }
        return Resource.Success(response)
    }
}
package com.open.composeexpo.data.api

import com.open.composeexpo.data.api.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

public interface WeatherApi {
    @GET("weather")
    suspend fun weather(@Query("q") city: String,
                        @Query("appId") appId: String) : WeatherResponse
}
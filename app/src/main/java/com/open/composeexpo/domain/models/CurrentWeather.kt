package com.open.composeexpo.domain.models

data class CurrentWeather(
    val temp: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val description: String,
    val icon: String,
    val city: String
)

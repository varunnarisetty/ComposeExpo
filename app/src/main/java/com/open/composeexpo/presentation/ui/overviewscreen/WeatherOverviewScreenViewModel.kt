package com.open.composeexpo.presentation.ui.overviewscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.open.composeexpo.ComposeExpoApplication
import com.open.composeexpo.domain.models.CurrentWeather
import com.open.composeexpo.domain.repositories.WeatherRepository
import com.open.weather.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherOverviewScreenViewModel @Inject constructor(
    private val weatherRepository : WeatherRepository, application: ComposeExpoApplication
) : AndroidViewModel(application) {
    var currentWeather = mutableStateOf<CurrentWeather?>(null)
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    fun loadWeatherInfo(city: String){
        viewModelScope.launch {
            isLoading.value = true
            var result = weatherRepository.getWeather(city)
            when(result){
                is Resource.Success -> {
                    var weatherResource = result.data
                    currentWeather.value = CurrentWeather(
                        temp = weatherResource!!.main.temp,
                        minTemp = weatherResource.main.temp_min,
                        maxTemp = weatherResource.main.temp_max,
                        description = weatherResource.weather[0].description,
                        icon = weatherResource.weather[0].icon,
                        city = weatherResource.name
                    )
                    isLoading.value = false
                }
                is Resource.Error -> {
                    currentWeather.value = null
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }

}
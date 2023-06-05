package com.open.composeexpo.utils

object Constants {
    const val API_KEY = "7317b556117c4c3a16db31d055657a3a"
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private const val ICO_URL = "https://openweathermap.org/img/wn/10d@2x.png"

    fun getIconUrl(icon: String?) : String{
         if(icon == null){
             return ICO_URL
         }
        return "https://openweathermap.org/img/wn/${icon}@2x.png"
    }
}
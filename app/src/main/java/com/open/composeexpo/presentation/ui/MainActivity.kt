package com.open.composeexpo.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.open.composeexpo.presentation.ui.overviewscreen.WeatherOverviewScreen
import com.open.composeexpo.presentation.ui.overviewscreen.WeatherOverviewScreenViewModel
import com.open.composeexpo.presentation.ui.theme.ComposeExpoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    private val viewModel: WeatherOverviewScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExpoTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "weather_overview_screen" ){
                    composable("weather_overview_screen"){
                        WeatherOverviewScreen(navController = navController/*, viewModel*/)
                    }
                    composable(
                        "weather_detail_screen/{cityName}",
                        arguments =  listOf(
                            navArgument("cityName")
                            { type = NavType.StringType }
                        ),
                    ){
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeExpoTheme {
        Greeting("Android")
    }
}
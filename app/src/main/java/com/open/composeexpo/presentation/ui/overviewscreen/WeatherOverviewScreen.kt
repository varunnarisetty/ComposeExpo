package com.open.composeexpo.presentation.ui.overviewscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalFocusManager
import com.open.composeexpo.domain.models.CurrentWeather
import com.open.composeexpo.utils.Constants

@Composable
fun WeatherOverviewScreen(
    navController: NavController,
    viewModel : WeatherOverviewScreenViewModel = hiltViewModel()
){
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            SearchBar(
                hint = "City Name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                viewModel.loadWeatherInfo(it)
            }
            Spacer(modifier = Modifier.height(20.dp))
            WeatherInfo(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
){
    var value by remember {
        mutableStateOf("")
    }
    Row{
        BasicTextField(
            value = value,
            onValueChange = { newText ->
                value = newText
            },
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            ),
            maxLines = 1,
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 64.dp) // margin left and right
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color(0xFFAAE9E6),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.LightGray
                        )
                    }
                    innerTextField()
                }
            },
            modifier = Modifier.weight(3.0f, true)
        )
        Button(
            onClick = {onSearch(value)},
            modifier = Modifier.weight(1.0f, false)
        ) {
            Text("Find")
        }
    }

}

@Composable
fun WeatherInfo(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: WeatherOverviewScreenViewModel = hiltViewModel()
) {
    val currentWeather by remember { viewModel.currentWeather}
    val isLoading by remember { viewModel.isLoading}
    val loadError by remember { viewModel.loadError}

    Box(){
        Column {

            if(isLoading){
                Text(text = "Loading...")
            }else {
                if(currentWeather!=null){
                    WeatherCard(currentWeather = currentWeather!!, modifier)
                }else if (loadError != null){
                    Text(text = loadError)
                }else {
                    Text(text = "Error in Weather")
                }
            }
        }
    }
}

@Composable
fun WeatherCard(currentWeather: CurrentWeather,
                modifier: Modifier = Modifier,){
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.padding(16.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Today in ${currentWeather.city}",
                modifier = Modifier.align(Alignment.End),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            AsyncImage(
                model = Constants.getIconUrl(currentWeather?.icon),
                contentDescription = null,
                modifier = Modifier.width(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${currentWeather.temp}°C",
                fontSize = 50.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = currentWeather.description,
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

package fr.iutmmi.myapplidefilm2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import fr.iutmmi.myapplidefilm2.viewmodel1.MainViewModel

@Composable
fun SerieDetailScreen(seriesId: Int, viewModel: MainViewModel, navController: NavController) {

    LaunchedEffect(seriesId) {
        viewModel.getSeriesDetail(seriesId)
    }


    val series by viewModel.seriesDetail.collectAsState()


    series?.let { series ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = series.name, style = MaterialTheme.typography.headlineMedium)
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${series.poster_path}"),
                contentDescription = series.name,
                modifier = Modifier.height(300.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = series.overview, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Release Date: ${series.first_air_date}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Vote Average: ${series.vote_average}", style = MaterialTheme.typography.bodyMedium)
        }
    } ?: run {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
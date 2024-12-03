package fr.iutmmi.myapplidefilm2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import fr.iutmmi.myapplidefilm2.viewmodel1.MainViewModel
import fr.iutmmi.myapplidefilm2.screens.Movie
import fr.iutmmi.myapplidefilm2.screens.Acteurs
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.ImeAction


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Films(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val movies = viewModel.movies.collectAsState().value
    val navItemList = listOf(
        NavItem("Films", Icons.Default.Home),
        NavItem("Series", Icons.Default.Notifications),
        NavItem("Acteurs", Icons.Default.Settings),

    )

    var selectedIndex by remember { mutableIntStateOf(0) }
    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        searchQuery = ""
        isSearching = false
        viewModel.getFilmsInitiaux()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    if (isSearching) {
                        TextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            label = { Text("Rechercher") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    if (searchQuery.isNotBlank()) {
                                        viewModel.searchMovies(searchQuery)
                                    }
                                    isSearching = false
                                }
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            )
                        )
                    } else {
                        Text(text = "Films")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (isSearching) {
                            if (searchQuery.isNotBlank()) {
                                viewModel.searchMovies(searchQuery)
                            }
                            isSearching = false
                        } else {
                            isSearching = true
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Rechercher")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF8A2BE2),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = index == selectedIndex,
                        onClick = {
                            selectedIndex = index
                            when (index) {
                                0 -> navController.navigate("films")
                                1 -> navController.navigate("series")
                                2 -> navController.navigate("acteurs")
                            }
                        },
                        icon = { Icon(imageVector = navItem.icon, contentDescription = navItem.label) },
                        label = { Text(text = navItem.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "films") {
            composable("films") {

                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    if (searchQuery.isNotBlank() && movies.isEmpty()) {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Aucun résultat trouvé",
                                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                            )
                        }
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(12.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(movies) { movie ->
                                TitleCard(movie, navController)
                            }
                        }
                    }
                }
            }

            composable("movieDetail/{movieId}") { backStackEntry ->
                val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
                if (movieId != null) {
                    MovieDetailScreen(movieId = movieId, viewModel = viewModel, navController = navController)
                }
            }


            composable("seriesDetail/{seriesId}") { backStackEntry ->
                val seriesId = backStackEntry.arguments?.getString("seriesId")?.toIntOrNull()
                if (seriesId != null) {
                    SerieDetailScreen(seriesId = seriesId, viewModel = viewModel, navController = navController)
                }
            }

            composable("series") {
                Series(viewModel = viewModel,navController = navController, modifier = Modifier.padding(innerPadding))
            }
            composable("acteurs") {
                Acteurs(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
            }

        }
    }
}

@Composable
fun TitleCard(movie: Movie, navController: NavController) {
    val imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
        .clickable {
        navController.navigate("movieDetail/${movie.id}")
    },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = movie.title,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilms() {
    Films(viewModel = MainViewModel())
}
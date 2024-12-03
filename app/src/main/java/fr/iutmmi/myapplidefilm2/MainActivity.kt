package fr.iutmmi.myapplidefilm2

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//import fr.iutmmi.myapplidefilm2.screens.Films
import fr.iutmmi.myapplidefilm2.screens.Movie
import fr.iutmmi.myapplidefilm2.screens.Profil
import fr.iutmmi.myapplidefilm2.viewmodel1.MainViewModel

class MainActivity : ComponentActivity() {


    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel.getFilmsInitiaux()

        setContent {
            val navController = rememberNavController()
            //MyApp(viewModel, navController)

            //val movies = viewModel.movies.collectAsState().value
            NavHost(navController = navController, startDestination = "main_screen") {
                composable("main_screen") { MainScreen(navController) }
                //composable("second_screen") { Profil() }
                composable("film_screen") { Films(viewModel = viewModel) }
            }
        }
    }
}

@Composable
fun MyApp(viewModel: MainViewModel,navController: NavController) {


}

@Composable
fun MainScreen(navController: NavController){
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        if (isPortrait) {
            PortraitLayout(navController)
        } else {
            LandscapeLayout(navController)
        }
    }
}

@Composable
fun PortraitLayout(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ProfileImage()
        Spacer(modifier = Modifier.height(16.dp))
        ProfileText()
        Spacer(modifier = Modifier.height(24.dp))
        ContactInfo()
        Spacer(modifier = Modifier.height(24.dp))
        ActionButton(navController)
    }
}

@Composable
fun LandscapeLayout(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileImage()
            Spacer(modifier = Modifier.height(16.dp))
            ProfileText()
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContactInfo()
            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(navController)

        }
    }
}

@Composable
fun ProfileImage() {
    Image(
        painterResource(id = R.drawable.platsmexicains),
        contentDescription = "Profile Picture",
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ProfileText() {
    Text(
        text = "Nicolas Singer",
        style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp),
        textAlign = TextAlign.Center
    )
    Text(
        text = "Maitre de conférence en informatique\nEcole d'ingénieur ISIS - INU Champollion",
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ContactInfo() {
    Text(
        text = "nicolas.singer@gmail.com\nwww.linkedin.com/in/nicolas-singer",
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ActionButton(navController: NavController) {
    Button(onClick = { navController.navigate("film_screen")  }) {
        Text("Démarrer")
    }
}
/*
@Preview(showBackground = true)
@Composable
fun PreviewPortrait() {
    PortraitLayout()
}

@Preview(showBackground = true, widthDp = 700, heightDp = 400)
@Composable
fun PreviewLandscape() {
    LandscapeLayout()
}
 */
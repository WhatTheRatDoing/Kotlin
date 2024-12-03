package fr.iutmmi.myapplidefilm2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text

@Composable
fun Profil() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {

        Text(
            text = "Bienvenue sur la deuxième page !",
            modifier = Modifier.fillMaxSize(),
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 30.sp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSecondScreen() {
    Profil()
}
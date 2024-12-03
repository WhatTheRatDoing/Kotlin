@Composable
fun Profil() {
    // Écran secondaire avec un petit texte
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        // Un texte centré à l'écran
        Text(
            text = "Bienvenue sur la deuxième page !",
            modifier = Modifier.fillMaxSize(),
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 30.sp),
            textAlign = TextAlign.Center
        )
    }
}
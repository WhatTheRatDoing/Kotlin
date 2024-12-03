package fr.iutmmi.myapplidefilm2.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Retrofit Client pour configurer et créer l'instance de TmdbAPI
object RetrofitClient {

    // Création de l'instance Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/") // Base URL de l'API TMDb
        .addConverterFactory(MoshiConverterFactory.create()) // Utilisation de Moshi pour la conversion JSON
        .build()

    // Création de l'instance de l'interface TmdbAPI
    val api: TmdbAPI = retrofit.create(TmdbAPI::class.java)
}
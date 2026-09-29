package com.example.recetarioapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recetarioapp.model.Pokemon
import com.example.recetarioapp.ui.theme.PokemonWikiTheme
import com.example.recetarioapp.ui.theme.screen.MainScreen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokemonWikiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(8.dp),
                        pokemon = listOf(
                            Pokemon(
                                numero = "#0001",
                                nombre = "Bulbasaur",
                                tipos = "Planta / Veneno",
                                descripcion = "Desde que nace, lleva una semilla en el lomo que crece con él."
                            ),
                            Pokemon(
                                numero = "#0004",
                                nombre = "Charmander",
                                tipos = "Fuego",
                                descripcion = "La llama de su cola refleja su estado de salud y sus emociones."
                            ),
                            Pokemon(
                                numero = "#0007",
                                nombre = "Squirtle",
                                tipos = "Agua",
                                descripcion = "Se protege con su caparazón y lanza agua a presión por la boca."
                            ),
                            Pokemon(
                                numero = "#0025",
                                nombre = "Pikachu",
                                tipos = "Eléctrico",
                                descripcion = "Almacena electricidad en las bolsas de sus mejillas."
                            ),
                            Pokemon(
                                numero = "#0133",
                                nombre = "Eevee",
                                tipos = "Normal",
                                descripcion = "Su inestable código genético le permite evolucionar de muchas formas."
                            )
                        )
                    )
                }
            }
        }
    }
}
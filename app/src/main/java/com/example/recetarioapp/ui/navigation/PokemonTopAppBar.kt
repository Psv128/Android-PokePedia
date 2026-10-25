package com.example.recetarioapp.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.recetarioapp.navigation.Rutas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTopAppBar(
    currentRoute: String?,
    canNavigateBack: Boolean,
    onBackClick: () -> Unit
) {
    val title = when {
        currentRoute == Rutas.HOME -> "Pokédex - Inicio"
        currentRoute?.startsWith("detalle") == true -> "Detalle del Pokémon"
        currentRoute == Rutas.COMPARAR -> "Comparador de Pokémons"
        currentRoute == Rutas.PERFIL -> "Perfil de Entrenador"
        else -> "Pokédex App"
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver atrás"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PokemonTopAppBarPreview() {
    Column {
        PokemonTopAppBar(
            currentRoute = Rutas.HOME,
            canNavigateBack = false,
            onBackClick = {}
        )
        PokemonTopAppBar(
            currentRoute = "detalle/1",
            canNavigateBack = true,
            onBackClick = {}
        )
    }
}

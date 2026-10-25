package com.example.recetarioapp.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.recetarioapp.navigation.Rutas

@Composable
fun PokemonBottomBar(
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == Rutas.HOME,
            onClick = { onNavigateToRoute(Rutas.HOME) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") }
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.COMPARAR,
            onClick = { onNavigateToRoute(Rutas.COMPARAR) },
            icon = { Icon(Icons.Default.CompareArrows, contentDescription = "Comparar") },
            label = { Text("Comparar") }
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.PERFIL,
            onClick = { onNavigateToRoute(Rutas.PERFIL) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonBottomBarPreview() {
    Column {
        PokemonBottomBar(
            currentRoute = Rutas.HOME,
            onNavigateToRoute = {}
        )
    }
}

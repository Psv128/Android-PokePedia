package com.example.recetarioapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recetarioapp.data.PokemonRepository
import com.example.recetarioapp.model.Pokemon
import com.example.recetarioapp.ui.components.PokemonCard

@Composable
fun HomeScreen(
    pokemons: List<Pokemon>,
    onPokemonClick: (String) -> Unit,
    onGoToCompare: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Pokédex Wiki",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Selecciona un Pokémon para ver su detalle o compara estadísticas.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
        )

        Button(
            onClick = onGoToCompare,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            Text("Ir a Comparar Pokémons")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(pokemons, key = { it.id }) { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    onClick = { onPokemonClick(pokemon.id) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        pokemons = PokemonRepository.pokemons,
        onPokemonClick = {},
        onGoToCompare = {}
    )
}

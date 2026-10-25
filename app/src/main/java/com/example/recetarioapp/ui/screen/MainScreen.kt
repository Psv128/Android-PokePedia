package com.example.recetarioapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recetarioapp.data.PokemonRepository
import com.example.recetarioapp.model.Pokemon
import com.example.recetarioapp.ui.components.PokemonCard

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    pokemon: List<Pokemon> = PokemonRepository.pokemons
) {
    Column(
        modifier = modifier
    ) {
        Text(text = "Pokédex Wiki", style = MaterialTheme.typography.displaySmall)

        Spacer(modifier = Modifier.height(8.dp))

        PokemonList(
            modifier = Modifier.fillMaxWidth(),
            pokemon = pokemon
        )
    }
}

@Composable
fun PokemonList(
    modifier: Modifier = Modifier,
    pokemon: List<Pokemon>
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pokemon.size) { index ->
            PokemonCard(
                pokemon = pokemon[index],
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListPreview() {
    PokemonList(
        pokemon = PokemonRepository.pokemons
    )
}

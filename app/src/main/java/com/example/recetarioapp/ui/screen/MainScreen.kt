package com.example.recetarioapp.ui.theme.screen

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

import com.example.recetarioapp.model.Pokemon
import com.example.recetarioapp.ui.components.PokemonCard


@Composable
fun MainScreen (
    modifier: Modifier,
    pokemon: List<Pokemon>
){
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
    modifier: Modifier,
    pokemon: List<Pokemon>
){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pokemon.size) { index ->
            PokemonCard(
                modifier = Modifier.fillMaxWidth(),
                pokemon = pokemon[index]
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListPreview(){
    PokemonList(
        modifier = Modifier,
        pokemon = listOf(
            Pokemon("#0001", "Bulbasaur", "Planta / Veneno", "Lleva una semilla en el lomo desde que nace."),
            Pokemon("#0004", "Charmander", "Fuego", "La llama de su cola refleja su estado de salud."),
            Pokemon("#0007", "Squirtle", "Agua", "Se protege con su caparazón y lanza agua a presión." )
        )
    )
}
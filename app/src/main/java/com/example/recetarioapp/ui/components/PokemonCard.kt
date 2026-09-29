package com.example.recetarioapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recetarioapp.model.Pokemon

@Composable
fun PokemonCard(
    modifier: Modifier,
    pokemon: Pokemon
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = pokemon.nombre, style = MaterialTheme.typography.titleLarge)
                Text(text = pokemon.numero, style = MaterialTheme.typography.titleMedium)
            }
            Text(text = pokemon.tipos, style = MaterialTheme.typography.labelLarge)
            Text(text = pokemon.descripcion, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonCardPreview() {
    PokemonCard(
        modifier = Modifier,
        pokemon = Pokemon(
            numero = "#0001",
            nombre = "Bulbasaur",
            tipos = "Planta / Veneno",
            descripcion = "Desde que nace, lleva una semilla en el lomo que crece con él."
        )
    )
}
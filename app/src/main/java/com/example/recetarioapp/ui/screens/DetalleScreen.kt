package com.example.recetarioapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recetarioapp.data.PokemonRepository
import com.example.recetarioapp.ui.components.StatBar
import com.example.recetarioapp.ui.components.TypeTag

@Composable
fun DetalleScreen(
    pokemonId: String,
    onCompareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pokemon = PokemonRepository.getPokemonById(pokemonId)

    if (pokemon == null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Pokémon no encontrado")
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = pokemon.nombre,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = pokemon.numero,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                TypeTag(tipo = pokemon.tipo)

                Text(
                    text = pokemon.descripcion,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Estadísticas Base",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                StatBar(nombreStat = "PS", valor = pokemon.hp)
                StatBar(nombreStat = "Ataque", valor = pokemon.ataque)
                StatBar(nombreStat = "Defensa", valor = pokemon.defensa)
                StatBar(nombreStat = "Velocidad", valor = pokemon.velocidad)
            }
        }

        Button(
            onClick = onCompareClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Comparar con otro Pokémon")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalleScreenPreview() {
    DetalleScreen(
        pokemonId = "1",
        onCompareClick = {}
    )
}

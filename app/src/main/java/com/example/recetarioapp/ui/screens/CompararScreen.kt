package com.example.recetarioapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recetarioapp.data.PokemonRepository
import com.example.recetarioapp.model.Pokemon

@Composable
fun CompararScreen(
    onPokemonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val list = PokemonRepository.pokemons

    var pokemon1 by remember { mutableStateOf(list[0]) }
    var pokemon2 by remember { mutableStateOf(list[1]) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Comparador de Pokémons",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            PokemonSelectorDropdown(
                selectedPokemon = pokemon1,
                pokemons = list,
                onSelected = { pokemon1 = it },
                modifier = Modifier.weight(1f)
            )

            PokemonSelectorDropdown(
                selectedPokemon = pokemon2,
                pokemons = list,
                onSelected = { pokemon2 = it },
                modifier = Modifier.weight(1f)
            )
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = pokemon1.nombre,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onPokemonClick(pokemon1.id) }
                    )
                    Text(
                        text = "VS",
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = pokemon2.nombre,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onPokemonClick(pokemon2.id) }
                    )
                }

                StatRow(
                    nombre = "PS",
                    val1 = pokemon1.hp,
                    val2 = pokemon2.hp
                )
                StatRow(
                    nombre = "Ataque",
                    val1 = pokemon1.ataque,
                    val2 = pokemon2.ataque
                )
                StatRow(
                    nombre = "Defensa",
                    val1 = pokemon1.defensa,
                    val2 = pokemon2.defensa
                )
                StatRow(
                    nombre = "Velocidad",
                    val1 = pokemon1.velocidad,
                    val2 = pokemon2.velocidad
                )
            }
        }
    }
}

@Composable
fun PokemonSelectorDropdown(
    selectedPokemon: Pokemon,
    pokemons: List<Pokemon>,
    onSelected: (Pokemon) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        ) {
            Text(
                text = selectedPokemon.nombre,
                modifier = Modifier.padding(12.dp),
                fontWeight = FontWeight.Bold
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            pokemons.forEach { p ->
                DropdownMenuItem(
                    text = { Text(p.nombre) },
                    onClick = {
                        onSelected(p)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun StatRow(
    nombre: String,
    val1: Int,
    val2: Int
) {
    val greenColor = Color(0xFF2E7D32)
    val neutralColor = MaterialTheme.colorScheme.onSurface

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = val1.toString(),
            modifier = Modifier.weight(1f),
            fontWeight = if (val1 > val2) FontWeight.Bold else FontWeight.Normal,
            color = if (val1 > val2) greenColor else neutralColor
        )

        Text(
            text = nombre,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )

        Text(
            text = val2.toString(),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            fontWeight = if (val2 > val1) FontWeight.Bold else FontWeight.Normal,
            color = if (val2 > val1) greenColor else neutralColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CompararScreenPreview() {
    CompararScreen(
        onPokemonClick = {}
    )
}

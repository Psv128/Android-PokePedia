package com.example.recetarioapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recetarioapp.model.Pokemon
import com.example.recetarioapp.validator.PokemonFormValidator
import kotlinx.coroutines.launch

@Composable
fun PokemonFormScreen(
    onSaveClick: (Pokemon) -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
    pokemonInicial: Pokemon? = null,
) {
    var nombre by remember { mutableStateOf(pokemonInicial?.nombre ?: "") }
    var numero by remember { mutableStateOf(pokemonInicial?.numero ?: "") }
    var tipo by remember { mutableStateOf(pokemonInicial?.tipo ?: "Planta") }
    var descripcion by remember { mutableStateOf(pokemonInicial?.descripcion ?: "") }

    var hpText by remember { mutableStateOf(pokemonInicial?.hp?.toString() ?: "") }
    var ataqueText by remember { mutableStateOf(pokemonInicial?.ataque?.toString() ?: "") }
    var defensaText by remember { mutableStateOf(pokemonInicial?.defensa?.toString() ?: "") }
    var velocidadText by remember { mutableStateOf(pokemonInicial?.velocidad?.toString() ?: "") }

    // Error states
    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorNumero by remember { mutableStateOf<String?>(null) }
    var errorTipo by remember { mutableStateOf<String?>(null) }
    var errorDescripcion by remember { mutableStateOf<String?>(null) }
    var errorHp by remember { mutableStateOf<String?>(null) }
    var errorAtaque by remember { mutableStateOf<String?>(null) }
    var errorDefensa by remember { mutableStateOf<String?>(null) }
    var errorVelocidad by remember { mutableStateOf<String?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val tiposDisponibles = listOf(
        "Planta", "Planta / Veneno", "Fuego", "Agua",
        "Eléctrico", "Normal", "Psíquico", "Fantasma"
    )

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = if (pokemonInicial == null) "Crear Nuevo Pokémon" else "Editar Pokémon",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            // Nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    if (errorNombre != null) errorNombre = null
                },
                label = { Text("Nombre del Pokémon *") },
                isError = errorNombre != null,
                supportingText = { errorNombre?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Número Pokedex
            OutlinedTextField(
                value = numero,
                onValueChange = {
                    numero = it
                    if (errorNumero != null) errorNumero = null
                },
                label = { Text("Número Pokédex * (ej: #0025)") },
                isError = errorNumero != null,
                supportingText = { errorNumero?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Selector Tipo
            Text(
                text = "Tipo de Pokémon *",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold
            )
            TipoDropdownSelector(
                tipoSeleccionado = tipo,
                tipos = tiposDisponibles,
                onTipoSelected = {
                    tipo = it
                    if (errorTipo != null) errorTipo = null
                }
            )

            // Descripción
            OutlinedTextField(
                value = descripcion,
                onValueChange = {
                    descripcion = it
                    if (errorDescripcion != null) errorDescripcion = null
                },
                label = { Text("Descripción *") },
                isError = errorDescripcion != null,
                supportingText = { errorDescripcion?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Text(
                text = "Estadísticas Base *",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = hpText,
                    onValueChange = {
                        hpText = it
                        if (errorHp != null) errorHp = null
                    },
                    label = { Text("PS") },
                    isError = errorHp != null,
                    supportingText = { errorHp?.let { Text(it) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )

                OutlinedTextField(
                    value = ataqueText,
                    onValueChange = {
                        ataqueText = it
                        if (errorAtaque != null) errorAtaque = null
                    },
                    label = { Text("Ataque") },
                    isError = errorAtaque != null,
                    supportingText = { errorAtaque?.let { Text(it) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = defensaText,
                    onValueChange = {
                        defensaText = it
                        if (errorDefensa != null) errorDefensa = null
                    },
                    label = { Text("Defensa") },
                    isError = errorDefensa != null,
                    supportingText = { errorDefensa?.let { Text(it) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )

                OutlinedTextField(
                    value = velocidadText,
                    onValueChange = {
                        velocidadText = it
                        if (errorVelocidad != null) errorVelocidad = null
                    },
                    label = { Text("Velocidad") },
                    isError = errorVelocidad != null,
                    supportingText = { errorVelocidad?.let { Text(it) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onCancelClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancelar")
                }

                Button(
                    onClick = {
                        errorNombre = PokemonFormValidator.validarNombre(nombre)
                        errorNumero = PokemonFormValidator.validarNumero(numero)
                        errorTipo = PokemonFormValidator.validarTipo(tipo)
                        errorDescripcion = PokemonFormValidator.validarDescripcion(descripcion)
                        errorHp = PokemonFormValidator.validarStat(hpText, "PS")
                        errorAtaque = PokemonFormValidator.validarStat(ataqueText, "Ataque")
                        errorDefensa = PokemonFormValidator.validarStat(defensaText, "Defensa")
                        errorVelocidad = PokemonFormValidator.validarStat(velocidadText, "Velocidad")

                        val esValido = (errorNombre == null) && (errorNumero == null) &&
                                (errorTipo == null) && (errorDescripcion == null) &&
                                (errorHp == null) && (errorAtaque == null) &&
                                (errorDefensa == null) && (errorVelocidad == null)

                        if (esValido) {
                            val idFinal = pokemonInicial?.id ?: System.currentTimeMillis().toString()
                            val nuevoPokemon = Pokemon(
                                id = idFinal,
                                numero = numero.trim(),
                                nombre = nombre.trim(),
                                tipo = tipo,
                                descripcion = descripcion.trim(),
                                hp = hpText.toInt(),
                                ataque = ataqueText.toInt(),
                                defensa = defensaText.toInt(),
                                velocidad = velocidadText.toInt()
                            )
                            onSaveClick(nuevoPokemon)
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Por favor, corrige los errores antes de guardar."
                                )
                            }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (pokemonInicial == null) "Crear" else "Guardar")
                }
            }
        }
    }
}

@Composable
fun TipoDropdownSelector(
    tipoSeleccionado: String,
    tipos: List<String>,
    onTipoSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tipoSeleccionado,
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Seleccionar tipo"
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            tipos.forEach { t ->
                DropdownMenuItem(
                    text = { Text(t) },
                    onClick = {
                        onTipoSelected(t)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonFormScreenCrearPreview() {
    PokemonFormScreen(
        pokemonInicial = null,
        onSaveClick = {},
        onCancelClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PokemonFormScreenEditarPreview() {
    PokemonFormScreen(
        pokemonInicial = Pokemon(
            id = "1",
            numero = "#0001",
            nombre = "Bulbasaur",
            tipo = "Planta / Veneno",
            descripcion = "Desde que nace, lleva una semilla en el lomo.",
            hp = 45,
            ataque = 49,
            defensa = 49,
            velocidad = 45
        ),
        onSaveClick = {},
        onCancelClick = {}
    )
}

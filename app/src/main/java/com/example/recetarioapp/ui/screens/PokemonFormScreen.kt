package com.example.recetarioapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recetarioapp.model.Pokemon
import com.example.recetarioapp.validation.ValidatorPokemonForm
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonFormScreen(
    onSaveClick: (Pokemon) -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
    pokemonInicial: Pokemon? = null
) {
    val tiposDisponibles = listOf(
        "Planta", "Fuego", "Agua", "Eléctrico", "Normal", "Psíquico",
        "Lucha", "Veneno", "Tierra", "Volador", "Roca", "Dragón",
        "Planta / Veneno", "Fuego / Volador", "Agua / Hielo"
    )

    var nombre by remember { mutableStateOf(pokemonInicial?.nombre ?: "") }
    var tipo by remember { mutableStateOf(pokemonInicial?.tipo ?: tiposDisponibles.first()) }
    var descripcion by remember { mutableStateOf(pokemonInicial?.descripcion ?: "") }
    var hpStr by remember { mutableStateOf(pokemonInicial?.hp?.toString() ?: "50") }
    var ataqueStr by remember { mutableStateOf(pokemonInicial?.ataque?.toString() ?: "50") }
    var defensaStr by remember { mutableStateOf(pokemonInicial?.defensa?.toString() ?: "50") }
    var velocidadStr by remember { mutableStateOf(pokemonInicial?.velocidad?.toString() ?: "50") }

    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorTipo by remember { mutableStateOf<String?>(null) }
    var errorDescripcion by remember { mutableStateOf<String?>(null) }
    var errorHp by remember { mutableStateOf<String?>(null) }
    var errorAtaque by remember { mutableStateOf<String?>(null) }
    var errorDefensa by remember { mutableStateOf<String?>(null) }
    var errorVelocidad by remember { mutableStateOf<String?>(null) }

    var dropdownExpanded by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val esEdicion = pokemonInicial != null

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = if (esEdicion) "Editar Pokémon" else "Nuevo Pokémon",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = if (esEdicion) "Modifica los datos del Pokémon." else "Introduce los datos para registrar un nuevo Pokémon.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Campo Nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    if (errorNombre != null) errorNombre = ValidatorPokemonForm.validarNombre(it)
                },
                label = { Text("Nombre del Pokémon *") },
                isError = errorNombre != null,
                supportingText = errorNombre?.let { err -> { Text(err) } },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Selector Tipo (Dropdown)
            ExposedDropdownMenuBox(
                expanded = dropdownExpanded,
                onExpandedChange = { dropdownExpanded = !dropdownExpanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = tipo,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo de Pokémon *") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownExpanded) },
                    isError = errorTipo != null,
                    supportingText = errorTipo?.let { err -> { Text(err) } },
                    modifier = Modifier
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, true)
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false }
                ) {
                    tiposDisponibles.forEach { t ->
                        DropdownMenuItem(
                            text = { Text(t) },
                            onClick = {
                                tipo = t
                                dropdownExpanded = false
                                if (errorTipo != null) errorTipo = ValidatorPokemonForm.validarTipo(t)
                            }
                        )
                    }
                }
            }

            // Campo Descripción
            OutlinedTextField(
                value = descripcion,
                onValueChange = {
                    descripcion = it
                    if (errorDescripcion != null) errorDescripcion = ValidatorPokemonForm.validarDescripcion(it)
                },
                label = { Text("Descripción *") },
                isError = errorDescripcion != null,
                supportingText = errorDescripcion?.let { err -> { Text(err) } },
                minLines = 2,
                maxLines = 4,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Estadísticas Base (1-255)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )

            // Estadísticas en 2 Filas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = hpStr,
                    onValueChange = {
                        hpStr = it
                        if (errorHp != null) errorHp = ValidatorPokemonForm.validarStat(it, "PS")
                    },
                    label = { Text("PS (HP) *") },
                    isError = errorHp != null,
                    supportingText = errorHp?.let { err -> { Text(err) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = ataqueStr,
                    onValueChange = {
                        ataqueStr = it
                        if (errorAtaque != null) errorAtaque = ValidatorPokemonForm.validarStat(it, "Ataque")
                    },
                    label = { Text("Ataque *") },
                    isError = errorAtaque != null,
                    supportingText = errorAtaque?.let { err -> { Text(err) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = defensaStr,
                    onValueChange = {
                        defensaStr = it
                        if (errorDefensa != null) errorDefensa = ValidatorPokemonForm.validarStat(it, "Defensa")
                    },
                    label = { Text("Defensa *") },
                    isError = errorDefensa != null,
                    supportingText = errorDefensa?.let { err -> { Text(err) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = velocidadStr,
                    onValueChange = {
                        velocidadStr = it
                        if (errorVelocidad != null) errorVelocidad = ValidatorPokemonForm.validarStat(it, "Velocidad")
                    },
                    label = { Text("Velocidad *") },
                    isError = errorVelocidad != null,
                    supportingText = errorVelocidad?.let { err -> { Text(err) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )
            }

            // Botones de acción
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
                        errorNombre = ValidatorPokemonForm.validarNombre(nombre)
                        errorTipo = ValidatorPokemonForm.validarTipo(tipo)
                        errorDescripcion = ValidatorPokemonForm.validarDescripcion(descripcion)
                        errorHp = ValidatorPokemonForm.validarStat(hpStr, "PS")
                        errorAtaque = ValidatorPokemonForm.validarStat(ataqueStr, "Ataque")
                        errorDefensa = ValidatorPokemonForm.validarStat(defensaStr, "Defensa")
                        errorVelocidad = ValidatorPokemonForm.validarStat(velocidadStr, "Velocidad")

                        val esValido = errorNombre == null && errorTipo == null &&
                                errorDescripcion == null && errorHp == null &&
                                errorAtaque == null && errorDefensa == null &&
                                errorVelocidad == null

                        if (esValido) {
                            val pokemonFinal = Pokemon(
                                id = pokemonInicial?.id ?: "",
                                numero = pokemonInicial?.numero ?: "",
                                nombre = nombre.trim(),
                                tipo = tipo.trim(),
                                descripcion = descripcion.trim(),
                                hp = hpStr.trim().toInt(),
                                ataque = ataqueStr.trim().toInt(),
                                defensa = defensaStr.trim().toInt(),
                                velocidad = velocidadStr.trim().toInt()
                            )
                            onSaveClick(pokemonFinal)
                        } else {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Por favor, corrige los errores del formulario.")
                            }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (esEdicion) "Guardar Cambios" else "Crear Pokémon")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonFormScreenPreview() {
    PokemonFormScreen(
        onSaveClick = {},
        onCancelClick = {}
    )
}

package com.example.linux_logic_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Palette
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.linux_logic_app.ui.theme.LiloMain

@Composable
fun CustomizeScreen() {
    val headerColor = remember { mutableStateOf(Color.Black) }
    val textColor = remember { mutableStateOf(Color.White) }
    val colorOptions =
        listOf(Color.Black, Color.Gray, Color.White, Color.Red, Color.Green, Color.Blue)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())

    ) {
        Text(
            text = "Passe dein Terminal individuell an!",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.TwoTone.Palette,
                contentDescription = "Palette Icon for Customization",
                tint = LiloMain
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Farboptionen:",
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}

@Composable
fun ColorPickerDropdown(
    selectedColor: MutableState<Color>,
    label: String
) {
    var expanded by remember { mutableStateOf(false) }
    var showColorDialog by remember { mutableStateOf(false) }

    Column {
        // Label für den Picker
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(selectedColor.value, shape = RoundedCornerShape(8.dp))
                .clickable { expanded = !expanded }
                .padding(12.dp)
        ) {
            Text(
                text = "Aktuell ausgewählt",
                color = Color.White
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Wähle Farbe aus") },
                onClick = {
                    expanded = false
                    showColorDialog = true
                }
            )
        }

        if (showColorDialog) {
            ColorPickerDialog(
                initialColor = selectedColor.value,
                onColorSelected = {
                    selectedColor.value = it
                    showColorDialog = false
                },
                onDismiss = { showColorDialog = false }
            )
        }
    }
}


@Composable
fun ColorPickerDialog(
    initialColor: Color,
    onColorSelected: (Color) -> Unit,
    onDismiss: () -> Unit
) {
    var red by remember { mutableStateOf(initialColor.red) }
    var green by remember { mutableStateOf(initialColor.green) }
    var blue by remember { mutableStateOf(initialColor.blue) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Farbe auswählen") },
        text = {
            Column {
                Text("Rot: ${(red * 255).toInt()}")
                Slider(
                    value = red,
                    onValueChange = { red = it },
                    valueRange = 0f..1f
                )

                Text("Grün: ${(green * 255).toInt()}")
                Slider(
                    value = green,
                    onValueChange = { green = it },
                    valueRange = 0f..1f
                )

                Text("Blau: ${(blue * 255).toInt()}")
                Slider(
                    value = blue,
                    onValueChange = { blue = it },
                    valueRange = 0f..1f
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onColorSelected(Color(red, green, blue)) }) {
                Text("Auswählen")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Abbrechen")
            }
        }
    )
}

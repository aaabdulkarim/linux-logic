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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomizeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Passe dein Terminal individuell an!",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Farboptionen für Terminal-Kopf:",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        ColorCustomizationComponent(
            defaultColors = listOf(
                Color.Black, Color.Gray, Color.White, Color.Red, Color.Green, Color.Blue
            )
        )
    }
}

@Composable
fun ColorCustomizationComponent(defaultColors: List<Color>) {
    var selectedColor by remember { mutableStateOf(Color.Black) }
    var isColorPickerDialogOpen by remember { mutableStateOf(false) }
    var expandedDropdown by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Color Picker Box
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(selectedColor, RoundedCornerShape(8.dp))
                .clickable { isColorPickerDialogOpen = true }
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Dropdown for Standard Colors
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                .padding(8.dp)
                .clickable { expandedDropdown = !expandedDropdown }
        ) {
            Text("Standardfarben auswählen")

            DropdownMenu(
                expanded = expandedDropdown,
                onDismissRequest = { expandedDropdown = false }
            ) {
                defaultColors.forEach { color ->
                    DropdownMenuItem(
                        text = { Box(modifier = Modifier.size(24.dp).background(color)) },
                        onClick = {
                            selectedColor = color
                            expandedDropdown = false
                        }
                    )
                }
            }
        }
    }

    // Color Picker Dialog
    if (isColorPickerDialogOpen) {
        Dialog(onDismissRequest = { isColorPickerDialogOpen = false }) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Farbe auswählen", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    ColorSlider(
                        onColorSelected = { selectedColor = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { isColorPickerDialogOpen = false }) {
                        Text("Fertig")
                    }
                }
            }
        }
    }
}

@Composable
fun ColorSlider(onColorSelected: (Color) -> Unit) {
    var red by remember { mutableStateOf(0f) }
    var green by remember { mutableStateOf(0f) }
    var blue by remember { mutableStateOf(0f) }

    Column {
        Text("Rot")
        Slider(
            value = red,
            onValueChange = {
                red = it
                onColorSelected(Color(red, green, blue))
            }
        )
        Text("Grün")
        Slider(
            value = green,
            onValueChange = {
                green = it
                onColorSelected(Color(red, green, blue))
            }
        )
        Text("Blau")
        Slider(
            value = blue,
            onValueChange = {
                blue = it
                onColorSelected(Color(red, green, blue))
            }
        )
    }
}


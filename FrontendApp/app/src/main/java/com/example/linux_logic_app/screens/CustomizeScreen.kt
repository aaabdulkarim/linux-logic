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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.twotone.Palette
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.linux_logic_app.components.ColorPicker
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloDark
import com.example.linux_logic_app.ui.theme.LiloDarkSec
import com.example.linux_logic_app.ui.theme.LiloLight
import com.example.linux_logic_app.ui.theme.LiloLightSec
import com.example.linux_logic_app.ui.theme.LiloMain
import com.example.linux_logic_app.ui.theme.LiloMainSec
import com.example.linux_logic_app.ui.theme.LiloOrange

/*
https://www.youtube.com/watch?v=QqcMKQgfzec
https://mvnrepository.com/artifact/com.godaddy.android.colorpicker/compose-color-picker/0.7.0
https://github.com/skydoves/colorpicker-compose/tree/main
 */
@Composable
fun CustomizeScreen() {
    val defaultColorList = listOf(
        Color.Black to "Schwarz", Color.Cyan to "Cyan", Color.Magenta to "Magenta", Color.Yellow to "Gelb",
        Color.Gray to "Grau", Color.LightGray to "Hellgrau", Color.DarkGray to "Dunkelgrau",
        Color.White to "Weiß",
        Color.Red to "Rot", Color.Green to "Grün", Color.Blue to "Blau",
        LiloMain to "Lilo Hauptfarbe", LiloMainSec to "Lilo Sekundärfarbe", LiloOrange to "Lilo Orange", LiloBlue to "Lilo Blau",
        LiloLight to "Lilo Hell", LiloLightSec to "Lilo Hell Sekundär",
        LiloDark to "Lilo Dunkel", LiloDarkSec to "Lilo Dunkel Sekundär"
    )

    var selectedColor by remember { mutableStateOf(Color.Black) }

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

        Spacer(modifier = Modifier.height(16.dp))

        ColorPickerWithDropdown(
            selectedColor = selectedColor,
            onColorSelected = { color -> selectedColor = color },
            defaultColorList = defaultColorList
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun ColorPickerWithDropdown(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    defaultColorList: List<Pair<Color, String>>
) {
    var showColorPicker by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(selectedColor, RoundedCornerShape(8.dp))
                .clickable { showColorPicker = true }
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown Icon"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            defaultColorList.forEach { (color, name) ->
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(24.dp).background(color))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(name)
                        }
                    },
                    onClick = {
                        onColorSelected(color)
                        expanded = false
                    }
                )
            }
        }

        if (showColorPicker) {
            AlertDialog(
                onDismissRequest = { showColorPicker = false },
                title = { Text("Wähle eine Farbe") },
                text = {
                    Column {
                        ColorPicker(onColorSelected = { color ->
                            onColorSelected(color)
                        })
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = { showColorPicker = false }
                    ) {
                        Text("Bestätigen")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showColorPicker = false }
                    ) {
                        Text("Abbrechen")
                    }
                }
            )
        }
    }
}
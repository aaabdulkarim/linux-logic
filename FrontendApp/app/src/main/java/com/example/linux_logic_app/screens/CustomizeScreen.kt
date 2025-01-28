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
import androidx.compose.material.icons.twotone.DriveFileRenameOutline
import androidx.compose.material.icons.twotone.Palette
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloDark
import com.example.linux_logic_app.ui.theme.LiloDarkSec
import com.example.linux_logic_app.ui.theme.LiloLight
import com.example.linux_logic_app.ui.theme.LiloLightSec
import com.example.linux_logic_app.ui.theme.LiloMain
import com.example.linux_logic_app.ui.theme.LiloMainSec
import com.example.linux_logic_app.ui.theme.LiloOrange

@Composable
fun CustomizeScreen() {
    val defaultColorList = listOf(
        Color.Black, Color.Cyan, Color.Magenta, Color.Yellow,
        Color.Gray, Color.LightGray, Color.DarkGray,
        Color.White,
        Color.Red, Color.Green, Color.Blue,
        LiloMain, LiloMainSec, LiloOrange, LiloBlue,
        LiloLight, LiloLightSec,
        LiloDark, LiloDarkSec
    )

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

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Terminal-Kopf:",
                style = MaterialTheme.typography.bodyLarge
            )

            ColorPicker(
                defaultColorList = defaultColorList,
                defaultColor = Color.Black
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Terminal-Körper:",
                style = MaterialTheme.typography.bodyLarge
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Kopfzeile:",
                style = MaterialTheme.typography.bodyLarge
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Befehle:",
                style = MaterialTheme.typography.bodyLarge
            )

        }
    }
}

@Composable
fun ColorPicker(defaultColorList: List<Color>, defaultColor: Color) {
    var selectedColor by remember { mutableStateOf(defaultColor) }
    var isColorPickerDialogOpen by remember { mutableStateOf(false) }
    var expandedDropdown by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color = selectedColor, shape = RoundedCornerShape(8.dp))
                .clickable {
                    isColorPickerDialogOpen = true
                }
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(8.dp))
                .clickable {
                    expandedDropdown = !expandedDropdown
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Icon for CustomizeScreen",
                    modifier = Modifier
                        .size(24.dp)
                        .rotate(if (expandedDropdown) 180f else 0f),
                    tint = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = "Farbe auswählen",
                    color = MaterialTheme.colorScheme.onBackground
                )

                DropdownMenu(
                    expanded = expandedDropdown,
                    onDismissRequest = { expandedDropdown = false }
                ) {
                    defaultColorList.forEach { color ->
                        DropdownMenuItem(
                            text = {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .background(color)
                                )
                            },
                            onClick = {
                                selectedColor = color
                                expandedDropdown = false
                            }
                        )
                    }
                }
            }
        }
    }

    // Color Picker Dialog
    if (isColorPickerDialogOpen) {
        Dialog(
            onDismissRequest = {
                isColorPickerDialogOpen = false
            }
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Wählen Sie bitte eine Farbe aus"
                    )
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






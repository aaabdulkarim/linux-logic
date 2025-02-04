package com.example.linux_logic_app.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.SubdirectoryArrowRight
import androidx.compose.material.icons.twotone.Colorize
import androidx.compose.material.icons.twotone.Palette
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.linux_logic_app.components.ColorPicker
import com.example.linux_logic_app.components.Terminal
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
fun CustomizationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Passe dein Terminal individuell an!",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ColorCustomizationCard()

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp) // Feste Höhe für Terminal-Vorschau
        ) {
            Terminal("ws://10.0.107.0:8000/ws", true)
        }
    }
}


@Composable
fun ColorCustomizer(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    defaultColorList: List<Pair<Color, String>>
) {
    var showColorPicker by remember { mutableStateOf(false) }
    var showDefaultColors by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (showDefaultColors) 180f else 0f,
        label = "Rotation of ArrowDropDown Icon"
    )
    var selectedColorName by remember { mutableStateOf("Farbe auswählen") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(selectedColor, RoundedCornerShape(8.dp))
                .clickable {
                    showColorPicker = true
                }
                .border(1.dp, LiloMain, RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(8.dp))

        Row(
            modifier = Modifier
                .clickable {
                    showDefaultColors = true
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown Icon",
                modifier = Modifier
                    .graphicsLayer(rotationZ = rotationAngle),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = selectedColorName,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        DropdownMenu(
            expanded = showDefaultColors,
            onDismissRequest = {
                showDefaultColors = false
            },
            modifier = Modifier
                .height(300.dp)
                .animateContentSize()
        ) {
            defaultColorList.forEach { (color, name) ->
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(color = color, shape = RoundedCornerShape(8.dp))
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = name,
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    },
                    onClick = {
                        onColorSelected(color)
                        selectedColorName = name
                        showDefaultColors = false
                    }
                )
            }
        }
    }

    if (showColorPicker) {
        ColorPickerDialog(
            initialColor = selectedColor,
            onDismiss = {
                showColorPicker = false
            },
            onConfirm = { color ->
                onColorSelected(color)
                selectedColorName = "Benutzerdefiniert"
                showColorPicker = false
            }
        )
    }
}

@Composable
fun ColorPickerDialog(
    initialColor: Color,
    onDismiss: () -> Unit,
    onConfirm: (Color) -> Unit
) {
    var tempColor by remember { mutableStateOf(initialColor) }

    AlertDialog(
        modifier = Modifier
            .fillMaxHeight(fraction = .8f),
        containerColor = MaterialTheme.colorScheme.background,
        iconContentColor = LiloMain,
        titleContentColor = MaterialTheme.colorScheme.onBackground,
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Wählen Sie eine Farbe",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            ColorPicker(
                onColorSelected = {
                    tempColor = it
                }
            )
        },
        icon = {
            Icon(
                imageVector = Icons.TwoTone.Colorize,
                contentDescription = "Colorize Icon for Customization"
            )
        },
        confirmButton = {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = LiloMain,
                    contentColor = Color.White,
                ),
                contentPadding = PaddingValues(16.dp),
                onClick = {
                    onConfirm(tempColor)
                }
            ) {
                Text(
                    text = "Bestätigen",
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        },
        dismissButton = {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = LiloBlue,
                    contentColor = Color.White,
                ),
                contentPadding = PaddingValues(16.dp),
                onClick = {
                    onDismiss()
                }
            ) {
                Text(
                    text = "Abbrechen",
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    )
}

/*
https://www.youtube.com/watch?v=SNcMCH5DqaM
 */
@Composable
fun ColorCustomizationCard() {
    val defaultColorList = listOf(
        Color.White to "Weiß",
        Color.Black to "Schwarz",
        Color.Cyan to "Cyan",
        Color.Magenta to "Magenta",
        Color.Yellow to "Gelb",
        Color.Red to "Rot",
        Color.Green to "Grün",
        Color.Blue to "Blau",
        Color.Gray to "Grau",
        Color.LightGray to "Hellgrau",
        Color.DarkGray to "Dunkelgrau",
        LiloMain to "Lilo Hauptfarbe",
        LiloMainSec to "Lilo Sekundärfarbe",
        LiloOrange to "Lilo Orange",
        LiloBlue to "Lilo Blau",
        LiloLight to "Lilo Hell",
        LiloLightSec to "Lilo Hell Sekundär",
        LiloDark to "Lilo Dunkel",
        LiloDarkSec to "Lilo Dunkel Sekundär"
    )

    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Rotation of Arrow-Icon"
    )
    // Eigene Zustände für jede Option
    var terminalHeader by remember { mutableStateOf(Color.Black) }
    var terminalBody by remember { mutableStateOf(LiloDark) }
    var terminalHeaderText by remember { mutableStateOf(Color.White) }
    var shellPrompt by remember { mutableStateOf(Color.Green) }
    var commands by remember { mutableStateOf(Color.White) }
    var checked by remember { mutableStateOf(true) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = LiloBlue
        )
    ) {
        // Eine Column, die die statische Zeile und den erweiterten Inhalt enthält
        Column(
            modifier = Modifier
                .clickable {
                    expanded = !expanded
                }
                .padding(16.dp)
                .animateContentSize()
        ) {
            // Statische Zeile mit Icons und Text
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Palette,
                    contentDescription = "Palette Icon for Customization",
                    tint = LiloMain
                )

                Text(
                    text = "Anpassung - Farben",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                )

                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    modifier = Modifier.graphicsLayer(rotationZ = rotationAngle),
                    tint = Color.White
                )
            }
            // Erweiterter Inhalt, nur sichtbar wenn die Card expanded ist
            if (expanded) {
                Spacer(modifier = Modifier.height(16.dp))

                if (checked) {
                    Text(
                        text = "Verwendung der Default-Farben",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        textAlign = TextAlign.Center
                    )

                } else {
                    ColorCustomizationOption(
                        option = "Terminal Kopf:",
                        selectedColor = terminalHeader,
                        onColorSelected = { terminalHeader = it },
                        defaultColorList = defaultColorList
                    )

                    ColorCustomizationOption(
                        option = "Terminal Körper:",
                        selectedColor = terminalBody,
                        onColorSelected = { terminalBody = it },
                        defaultColorList = defaultColorList
                    )

                    ColorCustomizationOption(
                        option = "Terminal Kopf (Text):",
                        selectedColor = terminalHeaderText,
                        onColorSelected = { terminalHeaderText = it },
                        defaultColorList = defaultColorList
                    )

                    ColorCustomizationOption(
                        option = "Shell Prompt:",
                        selectedColor = shellPrompt,
                        onColorSelected = { shellPrompt = it },
                        defaultColorList = defaultColorList
                    )

                    ColorCustomizationOption(
                        option = "Befehle:",
                        selectedColor = commands,
                        onColorSelected = { commands = it },
                        defaultColorList = defaultColorList
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Default",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                        },
                        thumbContent = if (checked) {
                            {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Check Icon for Customization",
                                    modifier = Modifier.size(SwitchDefaults.IconSize),
                                )
                            }
                        } else {
                            {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Check Icon for Customization",
                                    modifier = Modifier.size(SwitchDefaults.IconSize),
                                    tint = Color.White
                                )
                            }
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = LiloOrange,
                            checkedTrackColor = LiloMain,
                            uncheckedThumbColor = LiloBlue,
                            uncheckedTrackColor = LiloMain,
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ColorCustomizationOption(
    option: String,
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    defaultColorList: List<Pair<Color, String>>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = option,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.SubdirectoryArrowRight,
                contentDescription = "SubdirectoryArrowRight for Customization"
            )
            ColorCustomizer(
                selectedColor = selectedColor,
                onColorSelected = onColorSelected,
                defaultColorList = defaultColorList,
            )
        }
    }
}

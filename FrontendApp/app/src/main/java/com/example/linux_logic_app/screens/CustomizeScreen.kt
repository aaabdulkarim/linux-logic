package com.example.linux_logic_app.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.twotone.Colorize
import androidx.compose.material.icons.twotone.Palette
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.linux_logic_app.R
import com.example.linux_logic_app.components.ColorPicker
import com.example.linux_logic_app.components.CustomizationItem
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

        Spacer(modifier = Modifier.height(8.dp))

        CustomizationCard(
            customizationItem = CustomizationItem(
                name = "Farben anpassen",
                icon = Icons.Filled.Palette,
                backgroundImage = R.drawable.colors_customize,
                description = "Ein Bild zur Beschreibung der Anpassung für Farben im Terminal"
            )
        )
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
        label = "Rotation of Arrow-Icon"
    )
    var selectedColorName by remember { mutableStateOf("Farbe auswählen") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
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
                                    .background(color, shape = RoundedCornerShape(8.dp))
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
            onDismiss = { showColorPicker = false },
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
                onColorSelected = { tempColor = it }
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
Vorübergehend wird dieses Composable nicht wiederverwendbar gestaltet, da es bis jetzt nur die
Anpassung der Farbe im terminal gibt.
 */
@Composable
fun CustomizationCard(customizationItem: CustomizationItem) {
    var isExpanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "Rotation of Arrow-Icon"
    )

    var selectedColor by remember { mutableStateOf(LiloMain) }

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

    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable { isExpanded = !isExpanded },
        border = BorderStroke(5.dp, LiloMain)
    ) {
        Column( // Use a Column as the main layout for the Card
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = customizationItem.backgroundImage),
                    contentDescription = customizationItem.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Row(
                    modifier = Modifier
                        .fillMaxSize() // Die gesamte Row füllen um den Content zu centern
                        .padding(12.dp)
                        .animateContentSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = customizationItem.icon,
                        contentDescription = "Custom Icon",
                        modifier = Modifier.size(40.dp),
                        tint = Color.White
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(12.dp)
                    ) {
                        Text(
                            text = customizationItem.name,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        if (isExpanded) {
                            Column( // This Column is now inside the main Card Column
                                modifier = Modifier.padding(16.dp) // Add some padding
                            ) {
                                Column {
                                    Text(
                                        text = "Terminal Kopf"
                                    )

                                    ColorCustomizer(
                                        selectedColor = selectedColor,
                                        onColorSelected = { color ->
                                            selectedColor = color
                                        },
                                        defaultColorList = defaultColorList
                                    )
                                }

                                Column {
                                    Text(
                                        text = "Terminal Körper"
                                    )

                                    ColorCustomizer(
                                        selectedColor = selectedColor,
                                        onColorSelected = { color ->
                                            selectedColor = color
                                        },
                                        defaultColorList = defaultColorList
                                    )
                                }

                                Column {
                                    Text(
                                        text = "Terminal Kopf (Text)"
                                    )

                                    ColorCustomizer(
                                        selectedColor = selectedColor,
                                        onColorSelected = { color ->
                                            selectedColor = color
                                        },
                                        defaultColorList = defaultColorList
                                    )
                                }

                                Column {
                                    Text(
                                        text = "Terminal Befehle"
                                    )

                                    ColorCustomizer(
                                        selectedColor = selectedColor,
                                        onColorSelected = { color ->
                                            selectedColor = color
                                        },
                                        defaultColorList = defaultColorList
                                    )
                                }
                            }
                        }
                    }
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        modifier = Modifier
                            .graphicsLayer(rotationZ = rotationAngle),
                        contentDescription = if (isExpanded) "Show less" else "Show more",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
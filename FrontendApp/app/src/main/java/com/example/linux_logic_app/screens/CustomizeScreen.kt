package com.example.linux_logic_app.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
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
        Color.White to "Weiß",
        Color.Black to "Schwarz", Color.Cyan to "Cyan", Color.Magenta to "Magenta", Color.Yellow to "Gelb",
        Color.Red to "Rot", Color.Green to "Grün", Color.Blue to "Blau",
        Color.Gray to "Grau", Color.LightGray to "Hellgrau", Color.DarkGray to "Dunkelgrau",
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
                .padding(bottom = 8.dp)
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

        Spacer(modifier = Modifier.height(8.dp))

        ColorCustomizer(
            selectedColor = selectedColor,
            onColorSelected = { color ->
                selectedColor = color
            },
            defaultColorList = defaultColorList
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomizationCard(
            "Farboptionen",
            Icons.TwoTone.Palette
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
 */
@Composable
fun CustomizationCard(name: String, icon: ImageVector) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Icon for Customization Option",
                tint = LiloMain
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text("Anpassung - ")
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                if(expanded) {
                    Text(text = "dfsdfdsfsf".repeat(4))
                }
            }

            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(
                    imageVector = if(expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if(expanded) "Show less" else "Show more"
                )
            }
        }
    }

}

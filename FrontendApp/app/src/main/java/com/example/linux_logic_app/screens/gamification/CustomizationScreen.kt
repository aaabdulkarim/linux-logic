package com.example.linux_logic_app.screens.gamification

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
import androidx.compose.material3.HorizontalDivider
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
import com.example.linux_logic_app.components.customization.ColorPicker
import com.example.linux_logic_app.components.terminal.PreviewTerminal
import com.example.linux_logic_app.components.viewmodels.UserViewModel
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloDanger
import com.example.linux_logic_app.ui.theme.LiloDark
import com.example.linux_logic_app.ui.theme.LiloDarkSec
import com.example.linux_logic_app.ui.theme.LiloLight
import com.example.linux_logic_app.ui.theme.LiloLightSec
import com.example.linux_logic_app.ui.theme.LiloMain
import com.example.linux_logic_app.ui.theme.LiloMainSec
import com.example.linux_logic_app.ui.theme.LiloOrange
import com.example.linux_logic_app.ui.theme.LiloSuccess

/**
 * CustomizationScreen - Der Bildschirm zur individuellen Anpassung des Terminals.
 * Dieses Composable zeigt einen Titel, eine ColorCustomizationCard und eine Vorschau des Terminals,
 * sodass der Benutzer die gewählten Einstellungen sofort sehen kann.
 * Wichtige Aspekte:
 * - Scrollable Column für dynamischen Inhalt.
 * - Integration von ColorCustomizationCard und PreviewTerminal zur Vorschau der Änderungen.
 * Quellen & Input:
 * https://www.youtube.com/watch?v=QqcMKQgfzec
 * https://mvnrepository.com/artifact/com.godaddy.android.colorpicker/compose-color-picker/0.7.0
 * https://github.com/skydoves/colorpicker-compose/tree/main
 * @param userViewModel Verwaltet den Zustand und die Logik des aktuell angemeldeten Nutzers.
 */
@Composable
fun CustomizationScreen(userViewModel: UserViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Passen Sie Ihr Terminal individuell an!",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Die Card zur Anpassung der Terminalfarben
        ColorCustomizationCard(userViewModel = userViewModel)

        Spacer(modifier = Modifier.height(16.dp))

        /*
        Anmerkung: Entweder weight verwenden für Dynamik oder fixe Höhe um auch innerhalb eines Scrolls anzupassen.
        Siehe PlayScreen die fixe Höhe aller Course Cards - 200.dp
        */
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            PreviewTerminal(userViewModel = userViewModel)
        }
    }
}

/**
 * ColorCustomizer - Ein Composable zur Auswahl einer Farbe aus einer Dropdown-Liste oder via ColorPicker.
 * Dieses Composable zeigt ein farbiges Kästchen, das bei Klick einen ColorPicker-Dialog öffnet.
 * Außerdem kann der Benutzer über ein Dropdown-Menü aus einer Liste vordefinierter Farben wählen.
 * Wichtige Aspekte:
 * - Verwendung von animateFloatAsState zur Animation des Dropdown-Pfeils.
 * - DropdownMenu zur Auswahl von vordefinierten Farben.
 * @param selectedColor Die aktuell ausgewählte Farbe.
 * @param onColorSelected Callback, der aufgerufen wird, wenn eine Farbe ausgewählt wurde.
 * @param defaultColorList Liste von Farb-/Name-Paaren zur Auswahl.
 */
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
                modifier = Modifier.graphicsLayer(rotationZ = rotationAngle),
                tint = Color.White
            )
            Text(
                text = selectedColorName,
                color = Color.White
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

/**
 * ColorPickerDialog - Ein Dialog zur Farbauswahl.
 * Dieses Composable öffnet einen AlertDialog, in dem der Benutzer mittels eines ColorPicker
 * eine Farbe auswählen kann. Die temporäre Farbe wird gespeichert, bis der Benutzer den Dialog bestätigt.
 * Wichtige Aspekte:
 * - Der Dialog passt seine Höhe an den Inhalt an (80% der Bildschirmhöhe).
 * - Übergabe von onDismiss und onConfirm, um den Dialog zu schließen bzw. die Auswahl zu bestätigen.
 * @param initialColor Die anfänglich ausgewählte Farbe.
 * @param onDismiss Callback zum Schließen des Dialogs.
 * @param onConfirm Callback, der die ausgewählte Farbe zurückgibt.
 */
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
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = LiloSuccess,
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
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = LiloDanger,
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

/**
 * ColorCustomizationCard - Eine Card zur Anpassung der Terminalfarben.
 * Dieses Composable zeigt eine Karte, in der der Benutzer per Klick auf die Karte
 * weitere Optionen zur Farbanpassung öffnen kann. Es integriert mehrere ColorCustomizationOptionen,
 * mit denen einzelne Aspekte des Terminaldesigns angepasst werden können.
 * https://www.youtube.com/watch?v=SNcMCH5DqaM
 * Wichtige Aspekte:
 * - Animierte Expansion der Karte mittels animateContentSize.
 * - Umschalten zwischen vordefinierten Farben und benutzerdefinierten Farben (Switch).
 * @param userViewModel Verwaltet den Zustand der Terminalfarben des Nutzers.
 */
@Composable
fun ColorCustomizationCard(userViewModel: UserViewModel) {
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

    val currentColors = userViewModel.terminalViewModel.terminalColors
    val useDefaultColors = userViewModel.terminalViewModel.useDefaultColors

    Card(
        colors = CardDefaults.cardColors(
            containerColor = LiloBlue
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(16.dp)
                .animateContentSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Palette,
                    contentDescription = "Palette Icon for Customization",
                    tint = LiloOrange
                )
                Text(
                    text = "Farbanpassung",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    modifier = Modifier.graphicsLayer(rotationZ = rotationAngle),
                    tint = Color.White
                )
            }

            if (expanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    HorizontalDivider(thickness = 1.dp, color = LiloMain)
                    Spacer(modifier = Modifier.height(16.dp))

                    if (useDefaultColors) {
                        Text(
                            text = "Verwendung der Default-Farben",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    } else {
                        ColorCustomizationOption(
                            option = "Terminal Kopf:",
                            selectedColor = currentColors.headerColor,
                            onColorSelected = {
                                userViewModel.updateTerminalColors(
                                    currentColors.copy(headerColor = it)
                                )
                            },
                            defaultColorList = defaultColorList
                        )

                        ColorCustomizationOption(
                            option = "Terminal Körper:",
                            selectedColor = currentColors.bodyColor,
                            onColorSelected = {
                                userViewModel.updateTerminalColors(
                                    currentColors.copy(bodyColor = it)
                                )
                            },
                            defaultColorList = defaultColorList
                        )

                        ColorCustomizationOption(
                            option = "Terminal Kopf (Text):",
                            selectedColor = currentColors.headerTextColor,
                            onColorSelected = {
                                userViewModel.updateTerminalColors(
                                    currentColors.copy(headerTextColor = it)
                                )
                            },
                            defaultColorList = defaultColorList
                        )

                        ColorCustomizationOption(
                            option = "Shell Prompt:",
                            selectedColor = currentColors.shellPromptColor,
                            onColorSelected = {
                                userViewModel.updateTerminalColors(
                                    currentColors.copy(shellPromptColor = it)
                                )
                            },
                            defaultColorList = defaultColorList
                        )

                        ColorCustomizationOption(
                            option = "Befehle:",
                            selectedColor = currentColors.commandColor,
                            onColorSelected = {
                                userViewModel.updateTerminalColors(
                                    currentColors.copy(commandColor = it)
                                )
                            },
                            defaultColorList = defaultColorList
                        )

                        ColorCustomizationOption(
                            option = "Cursor:",
                            selectedColor = currentColors.cursorColor,
                            onColorSelected = {
                                userViewModel.updateTerminalColors(
                                    currentColors.copy(cursorColor = it)
                                )
                            },
                            defaultColorList = defaultColorList
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Default",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Switch(
                            checked = useDefaultColors,
                            onCheckedChange = {
                                userViewModel.updateDefaultColors(it)
                            },
                            thumbContent = {
                                if (useDefaultColors) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Check Icon for Customization",
                                        modifier = Modifier.size(SwitchDefaults.IconSize),
                                        tint = Color.White
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear Icon for Customization",
                                        modifier = Modifier.size(SwitchDefaults.IconSize),
                                        tint = Color.White
                                    )
                                }
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = LiloOrange,
                                checkedTrackColor = LiloMain,
                                uncheckedThumbColor = LiloBlue,
                                uncheckedTrackColor = LiloMain
                            )
                        )
                    }
                }
            }
        }
    }
}

/**
 * ColorCustomizationOption - Ein einzelnes Element zur Farbanpassung.
 * Dieses Composable zeigt einen Titel und integriert das ColorCustomizer-Composable,
 * mit dem eine spezifische Farbe (z. B. für den Terminalkopf oder den Shell Prompt) angepasst werden kann.
 * Wichtige Aspekte:
 * - Die Option zeigt einen Titel, ein Icon und ruft ColorCustomizer auf, um die Farbauswahl zu ermöglichen.
 * @param option Beschriftung der anzupassenden Option.
 * @param selectedColor Die aktuell ausgewählte Farbe für diese Option.
 * @param onColorSelected Callback, der die Auswahl der neuen Farbe verarbeitet.
 * @param defaultColorList Liste vordefinierter Farben zur Auswahl.
 */
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
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.SubdirectoryArrowRight,
                contentDescription = "SubdirectoryArrowRight for Customization",
                tint = Color.White
            )
            ColorCustomizer(
                selectedColor = selectedColor,
                onColorSelected = onColorSelected,
                defaultColorList = defaultColorList,
            )
        }
    }
}

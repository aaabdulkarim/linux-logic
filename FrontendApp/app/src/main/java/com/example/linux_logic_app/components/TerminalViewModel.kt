package com.example.linux_logic_app.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.linux_logic_app.ui.theme.LiloDark

// 1. Definieren des Color-Modells als Data-Klasse
data class TerminalColors(
    val headerColor: Color,
    val bodyColor: Color,
    val headerTextColor: Color,
    val shellPromptColor: Color,
    val commandColor: Color,
    val cursorColor: Color
)

// 2. Festlegen der Default-Farben als Konstante
val defaultTerminalColors = TerminalColors(
    headerColor = Color.Black,
    bodyColor = LiloDark,
    headerTextColor = Color.White,
    shellPromptColor = Color.Green,
    commandColor = Color.White,
    cursorColor = Color.Green
)

// 3. Erstellen des ViewModels, welches den State zentral verwaltet
class TerminalViewModel : ViewModel() {
    // Der aktuell verwendete Farb-State (initial die Default-Farben)
    var terminalColors by mutableStateOf(defaultTerminalColors)
        private set

    // Wenn Default-Farben verwendet werden
    var useDefaultColors by mutableStateOf(true)
        private set

    /**
     * Aktualisiert den Farb-State.
     * Wenn [newColors] exakt den Default-Farben entspricht,
     * wird [useDefaultColors] auf true gesetzt.
     */
    fun updateColors(newColors: TerminalColors) {
        terminalColors = newColors
        useDefaultColors = newColors == defaultTerminalColors
    }

    /**
     * Schaltet explizit in den Default-Modus oder lässt benutzerdefinierte Farben zu.
     *
     * Wird [useDefault] auf true gesetzt, werden die Default-Farben aktiv.
     * Bei false bleiben die aktuellen, benutzerdefinierten Farben erhalten.
     */
    fun updateDefaultMode(useDefault: Boolean) {
        useDefaultColors = useDefault
        if (useDefault) {
            terminalColors = defaultTerminalColors
        }
    }
}


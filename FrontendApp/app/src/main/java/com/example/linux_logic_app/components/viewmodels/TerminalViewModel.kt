package com.example.linux_logic_app.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.linux_logic_app.ui.theme.LiloDark

/**
 * Datenklasse, die alle möglichen Farben für das Linux Logic Terminal kapselt.
 * Diese Klasse enthält Farbinformationen für:
 * @property headerColor die Farbe für den Header (headerColor)
 * @property headerColor die Farbe für den Hauptkörper des Terminals (bodyColor)
 * @property headerColor die Farbe für den Text im Header (headerTextColor)
 * @property headerColor die Farbe für den Shell-Prompt (shellPromptColor)
 * @property headerColor die Farbe für die eingegebenen Befehle (commandColor)
 * @property headerColor die Farbe für den Cursor (cursorColor)
 */
data class TerminalColors(
    val headerColor: Color,
    val bodyColor: Color,
    val headerTextColor: Color,
    val shellPromptColor: Color,
    val commandColor: Color,
    val cursorColor: Color
)

// Festlegen der Default-Farben als Konstante, die als Ausgangswert für das Terminal genutzt werden
val defaultTerminalColors = TerminalColors(
    headerColor = Color.Black,
    bodyColor = LiloDark,
    headerTextColor = Color.White,
    shellPromptColor = Color.Green,
    commandColor = Color.White,
    cursorColor = Color.Green
)

/**
 * Das TerminalViewModel verwaltet den Zustand der aktuellen Terminalfarben,
 * die individuell für jeden Benutzer festgelegt werden können.
 * Es ermöglicht das Aktualisieren der Farben und das Umschalten zwischen benutzerdefinierten
 * Farben und den Default-Farben.
 * @param terminalColors Die anfänglichen Terminalfarben. Standardmäßig werden die Default-Farben genutzt.
 */
class TerminalViewModel(terminalColors: TerminalColors = defaultTerminalColors) : ViewModel() {

    // Der aktuell verwendete Farb-State wird reaktiv gehalten,
    // sodass Änderungen automatisch in der UI reflektiert werden.
    var terminalColors by mutableStateOf(terminalColors)
        private set

    // Flag, das angibt, ob die Default-Farben aktiv sind.
    // Wird initial gesetzt, indem geprüft wird, ob die initialen Farben den Default-Farben entsprechen.
    var useDefaultColors by mutableStateOf(terminalColors == defaultTerminalColors)
        private set

    /**
     * Aktualisiert den Farb-State des Terminals.
     * Wenn [newColors] exakt den Default-Farben entspricht, wird [useDefaultColors] auf true gesetzt.
     * Andernfalls werden die benutzerdefinierten Farben übernommen.
     * @param newColors Neue Farbwerte, die angewendet werden sollen.
     */
    fun updateColors(newColors: TerminalColors) {
        terminalColors = newColors
        useDefaultColors = newColors == defaultTerminalColors
    }

    /**
     * Schaltet explizit in den Default-Modus oder erlaubt benutzerdefinierte Farben.
     * Wird [useDefault] auf true gesetzt, so werden die Default-Farben aktiv und als Farb-State gesetzt.
     * Andernfalls bleiben die aktuell gesetzten, benutzerdefinierten Farben erhalten.
     * @param useDefault Boolean, der angibt, ob die Default-Farben genutzt werden sollen.
     */
    fun updateDefaultMode(useDefault: Boolean) {
        useDefaultColors = useDefault
        if (useDefaultColors) {
            terminalColors = defaultTerminalColors
        }
    }
}

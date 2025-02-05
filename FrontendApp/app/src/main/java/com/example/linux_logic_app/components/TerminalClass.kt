package com.example.linux_logic_app.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.linux_logic_app.ui.theme.LiloDark

data class TerminalColors(
    val headerColor: Color,
    val bodyColor: Color,
    val headerTextColor: Color,
    val shellPromptColor: Color,
    val commandColor: Color,
)

class TerminalViewModel : ViewModel() {
    var terminalColors by mutableStateOf(
        TerminalColors(
            headerColor = Color.Black,
            bodyColor = LiloDark,
            headerTextColor = Color.White,
            shellPromptColor = Color.Green,
            commandColor = Color.White
        )
    )
        private set

    fun updateColors(newColors: TerminalColors) {
        terminalColors = newColors
    }
}


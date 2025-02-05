package com.example.linux_logic_app.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.linux_logic_app.ui.theme.LiloDark
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class TerminalColors(
    val headerColor: Color = Color.Black,
    val bodyColor: Color = LiloDark,
    val headerTextColor: Color = Color.White,
    val shellPromptColor: Color = Color.Green,
    val commandColor: Color = Color.White
)

@HiltViewModel
class TerminalViewModel @Inject constructor() : ViewModel() {

    var terminalColors by mutableStateOf(TerminalColors())
        private set

    fun updateColors(newColors: TerminalColors) {
        terminalColors = newColors
    }
}

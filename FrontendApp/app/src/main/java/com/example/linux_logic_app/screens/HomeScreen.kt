package com.example.linux_logic_app.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linux_logic_app.components.Terminal
import com.example.linux_logic_app.components.TerminalViewModel

@Composable
fun HomeScreen(terminalViewModel: TerminalViewModel) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {
        Terminal("ws://172.20.10.2:8000/ws", preview = false, terminalViewModel = terminalViewModel)
    }
}
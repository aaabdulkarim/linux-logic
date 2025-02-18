package com.example.linux_logic_app.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LevelScreen() {
    Text(
        text = "Level Page",
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.labelSmall
    )
}
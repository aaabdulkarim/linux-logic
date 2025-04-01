package com.example.linux_logic_app.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// ViewModel für Settings-Zustand
// Kann möglicherweise in Zukunft verwendet werden
class SettingsViewModel : ViewModel() {
    private var accountCardExpanded by mutableStateOf(false)
        private set

    fun updateAccountCardExpanded(expanded: Boolean) {
        accountCardExpanded = expanded
    }
}
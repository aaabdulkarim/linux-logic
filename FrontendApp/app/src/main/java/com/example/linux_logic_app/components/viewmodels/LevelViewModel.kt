package com.example.linux_logic_app.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.linux_logic_app.components.scenario.Scenario

/**
 * Das LevelViewModel verwaltet den Zustand des aktuellen Szenarios und des Sublevels.
 */
class LevelViewModel(
    initialScenario: Scenario? = null,
    initialLevel: Int = 1
) : ViewModel() {

    // Aktuelles Scenario (null, wenn keines ausgewählt wurde)
    private var _currentScenario by mutableStateOf<Scenario?>(initialScenario)
    val currentScenario: Scenario? get() = _currentScenario

    // Aktueller Sublevel-Index
    private var _currentLevel by mutableIntStateOf(initialLevel)
    val currentLevel: Int get() = _currentLevel

}
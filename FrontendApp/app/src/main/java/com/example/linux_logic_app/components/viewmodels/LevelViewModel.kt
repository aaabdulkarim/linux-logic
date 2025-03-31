package com.example.linux_logic_app.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.linux_logic_app.components.scenario.Scenario
import com.example.linux_logic_app.components.scenario.Sublevel

/**
 * Das LevelViewModel verwaltet das aktuell ausgewählte Scenario und
 * den aktuellen Level‑Index (bzw. das aktive Sublevel).
 *
 * @param scenario Das vom Benutzer ausgewählte Scenario.
 */
class LevelViewModel(private val scenario: Scenario) : ViewModel() {

    private var _currentLevel by mutableIntStateOf(1)
    val currentLevel: Int get() = _currentLevel

    /**
     * Gibt das aktuelle Sublevel zurück. Da es immer ein gültiges Sublevel gibt, wird `!!` verwendet.
     */
    fun getCurrentSublevel(): Sublevel = scenario.sublevels[_currentLevel]!!

    /**
     * Gibt den Namen des aktuellen Levels zurück.
     */
    fun getCurrentLevelName(): String = "Level $_currentLevel"

    /**
     * Gibt die Beschreibung des aktuellen Sublevels zurück.
     */
    fun getCurrentSublevelDescription(): String = getCurrentSublevel().description

    /**
     * Prüft, ob es ein weiteres Sublevel gibt.
     */
    fun hasNextSublevel(): Boolean = scenario.hasNextSublevel(_currentLevel)

    /**
     * Wechselt zum nächsten Sublevel, falls vorhanden.
     * Gibt `true` zurück, wenn das nächste Level existiert, sonst `false`.
     */
    fun nextSublevel(): Boolean {
        return if (hasNextSublevel()) {
            _currentLevel += 1
            true
        } else {
            false
        }
    }

    /**
     * Setzt das Level zurück (z.B. beim Neustart des Szenarios).
     */
    fun resetLevel() {
        _currentLevel = 1
    }
}


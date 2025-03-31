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
    var currentLevel by mutableIntStateOf(1)
        private set

    /** Gibt das aktuelle Sublevel zurück oder null, falls es nicht existiert */
    fun getCurrentSublevel(): Sublevel? = scenario.getSublevel(currentLevel)

    /** Gibt den Namen des aktuellen Levels zurück */
    fun getCurrentLevelName(): String = "Level $currentLevel"

    /** Gibt die Beschreibung des aktuellen Sublevels zurück oder einen Platzhaltertext */
    fun getCurrentSublevelDescription(): String =
        getCurrentSublevel()?.description ?: "Keine Beschreibung vorhanden."

    /** Prüft, ob ein weiteres Sublevel existiert */
    fun hasNextSublevel(): Boolean = scenario.hasNextSublevel(currentLevel)

    /** Prüft, ob das aktuelle Sublevel das letzte ist */
    fun isLastSublevel(): Boolean = scenario.getLastSublevel()?.id == currentLevel

    /** Wechselt zum nächsten Sublevel, falls vorhanden */
    fun nextSublevel(): Boolean {
        return if (hasNextSublevel()) {
            currentLevel++
            true
        } else {
            false
        }
    }
}

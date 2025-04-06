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
 * @param scenario Das vom Benutzer ausgewählte Scenario, welches alle Sublevels und Kursinformationen enthält.
 * @property currentLevel ist das aktuelle Level als Integer-State (für die distincte ID)
 */
class LevelViewModel(private val scenario: Scenario) : ViewModel() {

    // Speichert den aktuellen Level-Index (beginnend mit 1).
    // Die Verwendung von mutableIntStateOf ermöglicht es, den Status reaktiv zu verwalten,
    // sodass UI-Komponenten, die diesen Wert beobachten, automatisch aktualisiert werden.
    var currentLevel by mutableIntStateOf(1)
        private set

    /**
     * Gibt das aktuelle Sublevel zurück oder null, falls es nicht existiert.
     * Diese Funktion verwendet die Methode getSublevel aus dem Scenario,
     * um anhand des aktuellen Level-Index das entsprechende Sublevel abzurufen.
     * @return Das Sublevel, das dem aktuellen Level entspricht, oder null, wenn keines vorhanden ist.
     */
    fun getCurrentSublevel(): Sublevel? = scenario.getSublevel(currentLevel)

    /**
     * Gibt den Namen des aktuellen Levels zurück.
     * Hier wird der Level-Name dynamisch aus dem aktuellen Level-Index generiert.
     * @return Ein String im Format "Level X", wobei X der aktuelle Level-Index ist.
     */
    fun getCurrentLevelName(): String = "Level $currentLevel"

    /**
     * Gibt die Beschreibung des aktuellen Sublevels zurück.
     * Falls das aktuelle Sublevel keine Beschreibung enthält (null), wird ein Platzhaltertext zurückgegeben.
     * @return Die Beschreibung des aktuellen Sublevels oder "Keine Beschreibung vorhanden."
     */
    fun getCurrentSublevelDescription(): String =
        getCurrentSublevel()?.description ?: "Keine Beschreibung vorhanden."

    /**
     * Prüft, ob ein weiteres Sublevel existiert.
     * Diese Funktion ruft die entsprechende Methode des Scenario-Objekts auf,
     * um zu überprüfen, ob der nächste Level (currentLevel + 1) vorhanden ist.
     * @return true, wenn ein nächstes Sublevel existiert, sonst false.
     */
    fun hasNextSublevel(): Boolean = scenario.hasNextSublevel(currentLevel)

    /**
     * Prüft, ob das aktuelle Sublevel das letzte im Scenario ist.
     * Dies geschieht, indem die ID des letzten vorhandenen Sublevels mit dem aktuellen Level-Index verglichen wird.
     * @return true, wenn das aktuelle Sublevel das letzte ist, sonst false.
     */
    fun isLastSublevel(): Boolean = scenario.getLastSublevel()?.id == currentLevel

    /**
     * Wechselt zum nächsten Sublevel, falls vorhanden.
     * Wenn ein nächstes Sublevel existiert, wird der currentLevel-Index inkrementiert und die Funktion gibt true zurück.
     * Andernfalls bleibt der Index unverändert und es wird false zurückgegeben.
     * @return true, wenn erfolgreich zum nächsten Sublevel gewechselt wurde, sonst false.
     */
    fun nextSublevel(): Boolean {
        return if (hasNextSublevel()) {
            currentLevel++
            true
        } else {
            false
        }
    }

    fun getScenarioAmount(): Int = getScenarioAmount()
}


package com.example.linux_logic_app.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.linux_logic_app.components.scenario.Scenario
import com.example.linux_logic_app.components.scenario.Sublevel

/**
 * Diese Klasse namens LevelViewModel, inkludiert die Levellogik eines ausgewählten Scenarios
 * und wird separiert, um Kapselung, Wiederverwendbarkeit und Testbarkeit zu verbessern.
 * @property initialScenario Optional initial ausgewähltes Scenario.
 * @property initialLevel Der initiale Level-Index, standardmäßig 1.
 */
class LevelViewModel(
    initialScenario: Scenario? = null,
    initialLevel: Int = 1
) : ViewModel() {

    // Internes Scenario, das aktuell ausgewählt wurde im private State
    private var _currentScenario by mutableStateOf<Scenario?>(initialScenario)

    /**
     * Das aktuell ausgewählte Scenario. Kann null sein, wenn keines ausgewählt wurde.
     * Dies ist der unveränderliche public State des Szenarios, welches von außen
     * nicht geändert werden kann
     */
    val currentScenario: Scenario? get() = _currentScenario

    // Aktueller Level-Index (beginnend bei 1).
    private var _currentLevel by mutableIntStateOf(initialLevel)

    /**
     * Der aktuelle Level-Index.
     */
    val currentLevel: Int get() = _currentLevel

    /**
     * Wählt ein neues Scenario aus und setzt den Level-Index auf 1 zurück.
     * @param scenario Das Scenario, das ausgewählt werden soll.
     */
    fun selectScenario(scenario: Scenario) {
        _currentScenario = scenario
        _currentLevel = 1
    }

    /**
     * Liefert das aktuell ausgewählte Sublevel als Result.
     * @return Ein [Result] mit dem aktuellen [Sublevel] oder ein Fehler, falls kein Scenario ausgewählt wurde.
     */
    fun getCurrentSublevel(): Result<Sublevel> {
        val scenario =
            _currentScenario ?: return Result.failure(Exception("Kein Scenario ausgewählt!"))
        return scenario.getSublevel(_currentLevel)
    }

    /**
     * Wechselt zum nächsten Sublevel, sofern vorhanden, und liefert es als Result.
     * @return Ein [Result] mit dem nächsten [Sublevel] oder ein Fehler, falls kein nächstes Sublevel existiert.
     */
    fun nextLevel(): Result<Sublevel> {
        val scenario =
            _currentScenario ?: return Result.failure(Exception("Kein Scenario ausgewählt!"))
        return if (scenario.hasNextSublevel(_currentLevel)) {
            _currentLevel += 1
            scenario.getSublevel(_currentLevel)
        } else {
            Result.failure(Exception("Kein nächstes Sublevel vorhanden!"))
        }
    }

    /**
     * Wechselt zum vorherigen Sublevel, sofern vorhanden, und liefert es als Result.
     * @return Ein [Result] mit dem vorherigen [Sublevel] oder ein Fehler, falls bereits beim ersten Level.
     */
    fun previousLevel(): Result<Sublevel> {
        val scenario =
            _currentScenario ?: return Result.failure(Exception("Kein Scenario ausgewählt!"))
        return if (_currentLevel > 1) {
            _currentLevel -= 1
            scenario.getSublevel(_currentLevel)
        } else {
            Result.failure(Exception("Bereits beim ersten Level!"))
        }
    }

    /**
     * Liefert das letzte Sublevel des aktuell ausgewählten Scenarios als Result.
     * @return Ein [Result] mit dem letzten [Sublevel] oder ein Fehler, falls kein Scenario ausgewählt wurde.
     */
    fun getLastSublevel(): Result<Sublevel> {
        val scenario =
            _currentScenario ?: return Result.failure(Exception("Kein Scenario ausgewählt!"))
        return scenario.getLastSublevel()
    }

    /**
     * Setzt den aktuellen Level wieder auf 1 zurück.
     */
    fun resetLevel() {
        _currentLevel = 1
    }

    /**
     * Gibt einen dynamischen Namen basierend auf dem aktuellen Level-Index zurück.
     * @return Einen String im Format "Level X".
     */
    fun getCurrentLevelName(): String = "Level $_currentLevel"
}

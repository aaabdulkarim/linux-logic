package com.example.linux_logic_app.components.scenario

/**
 * Diese Datenklasse namens Sublevel repräsentiert die Attribute eines Sublevels mit der ID, der
 * Beschreibung und dem Status (Erledigt oder nicht erledigt). Ein Scneario hat mehrere Sublevel,
 * welche eine spezifische Reihenfolge und Ordnung aufweisen müssen. Wie das umgesetzt wird, ist
 * in der Scenario Klasse zu sehen.
 * @property id Eindeutige Identifikationsnummer des Sublevels.
 * @property description Beschreibung oder Anleitung des Lernmoduls.
 * @property done Status, ob das Sublevel bereits abgeschlossen wurde (true) oder noch aussteht (false).
 */
data class Sublevel(
    val id: Int,
    val description: String,
    val done: Boolean
) {
    /*val id: String
        get() = "Level x" // Der Name wird später dynamisch aus dem Key generiert
     */
}

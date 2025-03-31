package com.example.linux_logic_app.components.scenario

/**
 * Diese Datenklasse namens Sublevel repräsentiert die Attribute eines Sublevels mit dem Titel
 * und der Beschreibung. Ein Scneario hat mehrere Sublevel, welche eine spezifische Reihenfolge
 * und Ordnung aufweisen müssen. Wie das umgesetzt wird, ist in der Scenario Klasse zu sehen.
 * @property id Die ID des Sublevels
 * @property description Eine kurze Beschreibung des Kurses.
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

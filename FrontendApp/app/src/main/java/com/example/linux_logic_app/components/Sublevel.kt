package com.example.linux_logic_app.components

/**
 * Diese Datenklasse namens Sublevel repräsentiert die Attribute eines Sublevels mit dem Titel
 * und der Beschreibung. Ein Scneario hat mehrere Sublevel, welche eine spezifische Reihenfolge
 * und Ordnung aufweisen müssen. Wie das umgesetzt wird, ist in der Scenario Klasse zu sehen.
 * @property name Der Name des Kurses.
 * @property description Eine kurze Beschreibung des Kurses.
 */
data class Sublevel(
    val description: String
) {
    val name: String
        get() = "Level x" // Der Name wird später dynamisch aus dem Key generiert
}

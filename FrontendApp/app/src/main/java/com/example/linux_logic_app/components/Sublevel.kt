package com.example.linux_logic_app.components

/**
 * Diese Datenklasse namens Subcourse repräsentiert die Attribute eines Sublevels mit dem Titel, der
 * Beschreibung und dem zugehörigen Index, da es eine Reihenfolge innerhalb der Kurse gibt.
 * @property name Der Name des Kurses.
 * @property description Eine kurze Beschreibung des Kurses.
 */
data class Sublevel(
    val description: String
) {
    val name: String
        get() = "Level x" // Der Name wird später dynamisch aus dem Key generiert
}

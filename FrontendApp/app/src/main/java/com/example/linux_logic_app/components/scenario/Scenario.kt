package com.example.linux_logic_app.components.scenario

import com.example.linux_logic_app.R

/**
 * Diese Datenklasse namens Scenario repräsentiert die Attribute eines Kurses mit der ID, dem Kurstitel,
 * der Beschreibung, dem zugehörigen Bild, sowie einer LinkedHashMap aus Subleveln. Da die Level eine
 * feste Reihenfolge haben (Level 1 → Level 2 → ... → Level 10), ist eine LinkedHashMap ideal.
 * Sie speichert die Einträge in der Reihenfolge, in der sie hinzugefügt wurden. Zudem ist bietet sie
 * einen schnellen Zugriff, ist einfach zu implementieren, jedoch performancelastig.
 * - Beibehaltung der Einfügereihenfolge: Die Lernmodule (Sublevels) sollen in einer festen Sequenz (z. B. Level 1 → Level 2 → …) dargestellt werden.
 * - Schneller Zugriff: Wie bei einer HashMap erfolgt der Zugriff in konstanter Zeit (O(1)).
 * - Vermeidung unerwünschter Sortierung: Im Gegensatz zu anderen Collections wie TreeMap, die die Elemente sortieren, bleibt die ursprüngliche Reihenfolge erhalten.
 * @property id Eindeutige Identifikationsnummer des Kurses.
 * @property name Name des Kurses.
 * @property description Beschreibung des Kurses.
 * @property imageRes Referenz zur Bildressource, die den Kurs visuell repräsentiert.
 * @property sublevels Geordnete Sammlung der Sublevels, in der Reihenfolge ihres Auftretens.
 */
data class Scenario(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int,
    val sublevels: LinkedHashMap<Int, Sublevel>
) {
    /**
     * Überprüft, ob ein Sublevel mit der angegebenen ID existiert.
     * @param id ID des zu prüfenden Sublevels.
     * @return true, wenn das Sublevel existiert, sonst false.
     */
    fun hasSublevel(id: Int): Boolean = sublevels.containsKey(id)

    /**
     * Überprüft, ob das Szenario mindestens ein Sublevel enthält.
     * @return true, wenn Sublevels vorhanden sind, sonst false.
     */
    fun hasSublevels(): Boolean = sublevels.isNotEmpty()

    /**
     * Überprüft, ob ein Sublevel existiert, das direkt auf das aktuelle folgt.
     * @param currentId ID des aktuellen Sublevels.
     * @return true, wenn das nächste Sublevel vorhanden ist, sonst false.
     */
    fun hasNextSublevel(currentId: Int): Boolean = sublevels.containsKey(currentId + 1)

    /**
     * Liefert das Sublevel anhand der angegebenen ID.
     * @param id ID des gewünschten Sublevels.
     * @return Das Sublevel, falls vorhanden, sonst null.
     */
    fun getSublevel(id: Int): Sublevel? = sublevels[id]

    /**
     * Liefert das nächste Sublevel, basierend auf der aktuellen ID.
     * @param currentId ID des aktuellen Sublevels.
     * @return Das nächste Sublevel, falls vorhanden, sonst null.
     */
    fun getNextSublevel(currentId: Int): Sublevel? = sublevels[currentId + 1]

    /**
     * Liefert das letzte Sublevel im Szenario.
     * @return Das letzte Sublevel, falls vorhanden, sonst null.
     */
    fun getLastSublevel(): Sublevel? = sublevels.values.lastOrNull()

    fun getAmountOfScenarios(): Int = scenarioList.size
}

/**
 * Eine vordefinierte Liste von Kursen (Scenarios), die in der App zur Darstellung verwendet werden (temporär).
 * Jeder Kurs wird durch ein Scenario-Objekt repräsentiert, das neben dem Namen, der Beschreibung und der Bildreferenz
 * auch eine Sammlung von Sublevels enthält. Diese Sammlung ist in einer LinkedHashMap organisiert, um die Reihenfolge
 * der Lernmodule beizubehalten.
 */
val scenarioList = listOf(
    Scenario(
        id = 1,
        name = "Linux Grundlagen",
        description = "Deine ersten Schritte: Lerne die Grundlagen des Linux-Betriebssystems kennen. Du wirst verstehen, wie du dich im Terminal zurechtfindest, Dateien verwaltest und einfache Aufgaben automatisierst.",
        imageRes = R.drawable.linux_basics_course,
        sublevels = linkedMapOf(
            1 to Sublevel(
                description = "Lerne die Geschichte und die grundlegenden Konzepte von Linux kennen. Erfahre, warum Linux in Servern, Embedded Systems und der Softwareentwicklung so weit verbreitet ist.",
                id = 1,
                done = false
            ),
            2 to Sublevel(
                description = "Mache dich mit der Kommandozeile vertraut und lerne grundlegende Befehle wie `ls`, `cd`, `pwd` und `man`, um dich im Dateisystem zu bewegen.",
                id = 2,
                done = false
            ),
            3 to Sublevel(
                description = "Lerne, wie du Dateien erstellst, bearbeitest, verschiebst, umbenennst und löschst. Erfahre, wie du mit Verzeichnissen arbeitest und deren Berechtigungen verwaltest.",
                id = 3,
                done = false
            ),
            4 to Sublevel(
                description = "Verstehe, wie Linux-Prozesse arbeiten, wie du sie mit `ps`, `top` oder `kill` steuerst und wie Benutzer- und Gruppenrechte funktionieren.",
                id = 4,
                done = false
            ),
            5 to Sublevel(
                description = "Lerne, was Shell-Skripte sind und wie du sie erstellst. Erfahre, wie du Skripte ausführbar machst und welche grundlegenden Strukturen es gibt.",
                id = 5,
                done = false
            ),
            6 to Sublevel(
                description = "Nutze Variablen, um Werte zu speichern, und verwende Schleifen (`for`, `while`, `until`), um Abläufe effizient zu automatisieren.",
                id = 6,
                done = false
            ),
            7 to Sublevel(
                description = "Lerne Techniken zur Fehlerbehandlung und Protokollierung, damit deine Skripte robust und fehlertolerant sind.",
                id = 7,
                done = false
            ),
            8 to Sublevel(
                description = "Erfahre, wie du Hintergrundprozesse startest, beendest und steuerst. Nutze `nohup`, `jobs`, `fg`, `bg` und `disown`, um Skripte effizient auszuführen.",
                id = 8,
                done = false
            ),
        )
    ),
    Scenario(
        id = 2,
        name = "Fortgeschrittene Bash",
        description = "Werde zum Bash-Profi: Erstelleeeeeeeeeeeeeeeeeeeeeee leistungsstarke Bash-Skripte, um deine Arbeit am Computer zu automatisieren und zu beschleunigen. Erweitere deine Kenntnisse in der Shell und werde zum Experten für die Kommandozeile.\nWerde zum Bash-Profi: Erstelle leistungsstarke Bash-Skripte, um deine Arbeit am Computer zu automatisieren und zu beschleunigen. Erweitere deine Kenntnisse in der Shell und werde zum Experten für die Kommandozeile.\nWerde zum Bash-Profi: Erstelle leistungsstarke Bash-Skripte, um deine Arbeit am Computer zu automatisieren und zu beschleunigen. Erweitere deine Kenntnisse in der Shell und werde zum Experten für die Kommandozeile.\nWerde zum Bash-Profi: Erstelle leistungsstarke Bash-Skripte, um deine Arbeit am Computer zu automatisieren und zu beschleunigen. Erweitere deine Kenntnisse in der Shell und werde zum Experten für die Kommandozeile.",
        imageRes = R.drawable.bash_course,
        sublevels = linkedMapOf()
    ),
    Scenario(
        id = 3,
        name = "Docker Kenntnisse",
        description = "Beherrsche Docker: Entwickle und verwalte moderne Anwendungen mit Docker Containern. Lerne, wie du deine Anwendungen portabel und skalierbar machst.",
        imageRes = R.drawable.docker_course,
        sublevels = linkedMapOf()
    ),
    Scenario(
        id = 4,
        name = "Linux Dateisystem und Navigation",
        description = "Navigiere durch das Linux-Dateisystem: Versteh den Aufbau des Linux-Dateisystems und wie du effizient darin navigierst. Lerne, wie du Dateien und Verzeichnisse verwaltest und suchst.",
        imageRes = R.drawable.dateisystem_navigation_course,
        sublevels = linkedMapOf()
    ),
    Scenario(
        id = 5,
        name = "Textbearbeitung mit vim und nano",
        description = "Werde zum Texteditor-Meister: Lerne die leistungsstarken Texteditoren vim und nano kennen und werde zum Profi in der Textbearbeitung.",
        imageRes = R.drawable.nano_vs_vim_course,
        sublevels = linkedMapOf()
    ),
    Scenario(
        id = 6,
        name = "Linux Systemadministration",
        description = "Werde zum Linux-Administrator: Installiere, konfiguriere und verwalte deine eigenen Linux-Systeme. Lerne, wie du Probleme behebst und dein System optimierst.",
        imageRes = R.drawable.systemadministartion_course,
        sublevels = linkedMapOf()
    ),
    Scenario(
        id = 7,
        name = "Netzwerkverwaltung unter Linux",
        description = "Baue deine eigenen Netzwerke: Konfiguriere Netzwerke und richte verschiedene Netzwerkdienste ein. Lerne, wie du mehrere Computer miteinander verbindest und sicher kommunizierst.",
        imageRes = R.drawable.netzwerkverwaltung_course,
        sublevels = linkedMapOf()
    )
)

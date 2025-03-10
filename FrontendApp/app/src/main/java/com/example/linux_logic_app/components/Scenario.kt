package com.example.linux_logic_app.components

import com.example.linux_logic_app.R

/**
 * Diese Datenklasse namens Scenario repräsentiert die Attribute eines Kurses mit dem Kurstitel, der
 * Beschreibung und dem zugehörigen Bild, sowie einer LinkedHashMap aus Subleveln.
 * Da die Level eine feste Reihenfolge haben (Level 1 → Level 2 → ... → Level 10), ist eine
 * LinkedHashMap ideal. Sie speichert die Einträge in der Reihenfolge, in der sie hinzugefügt wurden.
 * Zudem ist bietet sie einen schnellen Zugriff, ist einfach zu implementieren, jedoch performancelastig.
 * Jeder Kurs enthält einen Namen, eine Beschreibung und eine Referenz auf ein Bildressourcen-Element.
 * @property name Der Name des Kurses.
 * @property description Eine kurze Beschreibung des Kurses.
 * @property imageRes Die ID der Bildressource, die mit diesem Kurs verknüpft ist.
 */
data class Scenario(
    val name: String,
    val description: String,
    val imageRes: Int,
    val sublevels: LinkedHashMap<Int, Sublevel>
) {
    /**
     * Diese Methode hasSublevel
     */
    fun hasSublevel(id: Int): Boolean = sublevels.containsKey(id)

    /**
     * Diese Methode hasSublevel
     */
    fun existSublevels(): Boolean = sublevels.isNotEmpty()

    /**
     * Diese Methode hasNextSublevel prüft, ob ein weiteres Level existiert
     */
    fun hasNextSublevel(currentId: Int): Boolean = sublevels.containsKey(currentId + 1)

    /**
     * Diese Methode getSublevel
     */
    fun getSublevel(id: Int): Result<Sublevel> {
        // Prüft, ob die LinkedHashMap `subcourses` das Sublevel mit der gegebenen ID enthält.
        return sublevels[id]?.let {
            // Falls das Sublevel existiert, wird ein erfolgreicher `Result` zurückgegeben.
            Result.success(it)
        } ?: Result.failure(Exception("Fehler: Sublevel mit der ID $id nicht vorhanden!"))
        // Falls kein Sublevel mit der ID existiert, wird ein fehlgeschlagenes `Result` mit einer Exception erzeugt.
    }

    /**
     * Diese Methode getNextSublevel holt das nächste Sublevel, falls es vorhanden ist
     */
    fun getNextSublevel(currentId: Int): Result<Sublevel> {
        // Prüft, ob die LinkedHashMap `subcourses` das Sublevel mit der nächsten ID enthält.
        return sublevels[currentId + 1]?.let {
            // Falls ein nächstes Sublevel existiert, wird ein erfolgreicher `Result` zurückgegeben.
            Result.success(it)
        } ?: Result.failure(Exception("Fehler: Kein nächstes Sublevel vorhanden!"))
        // Falls kein Sublevel mit einer nächsten ID existiert, wird ein fehlgeschlagenes `Result` mit einer Exception erzeugt.
    }

    /**
     * Diese Methode `getLastSublevel` holt das letzte Sublevel in der Reihenfolge.
     */
    fun getLastSublevel(): Result<Sublevel> {
        if (!existSublevels()) {
            return Result.failure(Exception("Fehler: Keine Sublevels in diesem Szenario $name vorhanden!"))
        }

        // Sagt aus, dass man zu 100% sicher ist, dass dieser Wert nicht null ist
        val lastKey = sublevels.keys.maxOrNull()!!
        return Result.success(sublevels[lastKey]!!)
    }
}

/**
 * Eine vordefinierte Liste von Kursen, die für die Darstellung in der App verwendet wird.
 * Diese Liste enthält mehrere Kursobjekte mit spezifischen Informationen zu jedem Kurs, wie
 * Namen, Beschreibungen und zugehörigen Bildressourcen.
 */
val courseList = listOf(
    Scenario(
        name = "Linux Grundlagen",
        description = "Deine ersten Schritte: Lerne die Grundlagen des Linux-Betriebssystems kennen. Du wirst verstehen, wie du dich im Terminal zurechtfindest, Dateien verwaltest und einfache Aufgaben automatisierst.",
        imageRes = R.drawable.linux_basics_course,
        sublevels = linkedMapOf(
            1 to Sublevel(
                description = "Lerne die Geschichte und die grundlegenden Konzepte von Linux kennen. Erfahre, warum Linux in Servern, Embedded Systems und der Softwareentwicklung so weit verbreitet ist."
            ),
            2 to Sublevel(
                description = "Mache dich mit der Kommandozeile vertraut und lerne grundlegende Befehle wie `ls`, `cd`, `pwd` und `man`, um dich im Dateisystem zu bewegen."
            ),
            3 to Sublevel(
                description = "Lerne, wie du Dateien erstellst, bearbeitest, verschiebst, umbenennst und löschst. Erfahre, wie du mit Verzeichnissen arbeitest und deren Berechtigungen verwaltest."
            ),
            4 to Sublevel(
                description = "Verstehe, wie Linux-Prozesse arbeiten, wie du sie mit `ps`, `top` oder `kill` steuerst und wie Benutzer- und Gruppenrechte funktionieren."
            ),
            5 to Sublevel(
                description = "Lerne, was Shell-Skripte sind und wie du sie erstellst. Erfahre, wie du Skripte ausführbar machst und welche grundlegenden Strukturen es gibt."
            ),
            6 to Sublevel(
                description = "Nutze Variablen, um Werte zu speichern, und verwende Schleifen (`for`, `while`, `until`), um Abläufe effizient zu automatisieren."
            ),
            7 to Sublevel(
                description = "Lerne Techniken zur Fehlerbehandlung und Protokollierung, damit deine Skripte robust und fehlertolerant sind."
            ),
            8 to Sublevel(
                description = "Erfahre, wie du Hintergrundprozesse startest, beendest und steuerst. Nutze `nohup`, `jobs`, `fg`, `bg` und `disown`, um Skripte effizient auszuführen."
            ),
        )
    ),
    Scenario(
        name = "Fortgeschrittene Bash",
        description = "Werde zum Bash-Profi: Erstelleeeeeeeeeeeeeeeeeeeeeee leistungsstarke Bash-Skripte, um deine Arbeit am Computer zu automatisieren und zu beschleunigen. Erweitere deine Kenntnisse in der Shell und werde zum Experten für die Kommandozeile.\nWerde zum Bash-Profi: Erstelle leistungsstarke Bash-Skripte, um deine Arbeit am Computer zu automatisieren und zu beschleunigen. Erweitere deine Kenntnisse in der Shell und werde zum Experten für die Kommandozeile.\nWerde zum Bash-Profi: Erstelle leistungsstarke Bash-Skripte, um deine Arbeit am Computer zu automatisieren und zu beschleunigen. Erweitere deine Kenntnisse in der Shell und werde zum Experten für die Kommandozeile.\nWerde zum Bash-Profi: Erstelle leistungsstarke Bash-Skripte, um deine Arbeit am Computer zu automatisieren und zu beschleunigen. Erweitere deine Kenntnisse in der Shell und werde zum Experten für die Kommandozeile.",
        imageRes = R.drawable.bash_course,
        sublevels = linkedMapOf()
    ),
    Scenario(
        name = "Docker Kenntnisse",
        description = "Beherrsche Docker: Entwickle und verwalte moderne Anwendungen mit Docker Containern. Lerne, wie du deine Anwendungen portabel und skalierbar machst.",
        imageRes = R.drawable.docker_course,
        sublevels = linkedMapOf()
    ),
    Scenario(
        name = "Linux Dateisystem und Navigation",
        description = "Navigiere durch das Linux-Dateisystem: Versteh den Aufbau des Linux-Dateisystems und wie du effizient darin navigierst. Lerne, wie du Dateien und Verzeichnisse verwaltest und suchst.",
        imageRes = R.drawable.dateisystem_navigation_course,
        sublevels = linkedMapOf()
    ),
    Scenario(
        name = "Textbearbeitung mit vim und nano",
        description = "Werde zum Texteditor-Meister: Lerne die leistungsstarken Texteditoren vim und nano kennen und werde zum Profi in der Textbearbeitung.",
        imageRes = R.drawable.nano_vs_vim_course,
        sublevels = linkedMapOf()
    ),
    Scenario(
        name = "Linux Systemadministration",
        description = "Werde zum Linux-Administrator: Installiere, konfiguriere und verwalte deine eigenen Linux-Systeme. Lerne, wie du Probleme behebst und dein System optimierst.",
        imageRes = R.drawable.systemadministartion_course,
        sublevels = linkedMapOf()
    ),
    Scenario(
        name = "Netzwerkverwaltung unter Linux",
        description = "Baue deine eigenen Netzwerke: Konfiguriere Netzwerke und richte verschiedene Netzwerkdienste ein. Lerne, wie du mehrere Computer miteinander verbindest und sicher kommunizierst.",
        imageRes = R.drawable.netzwerkverwaltung_course,
        sublevels = linkedMapOf()
    )
)

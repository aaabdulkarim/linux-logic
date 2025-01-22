package com.example.linux_logic_app.navigation

import com.example.linux_logic_app.R

/**
 * Diese Datenklasse namens Course repräsentiert die Attribute eines Kurses mit dem Kurstitel, der
 * Beschreibung und dem zugehörigen Bild.
 * Jeder Kurs enthält einen Namen, eine Beschreibung und eine Referenz auf ein Bildressourcen-Element.
 * @property name Der Name des Kurses.
 * @property description Eine kurze Beschreibung des Kurses.
 * @property imageRes Die ID der Bildressource, die mit diesem Kurs verknüpft ist.
 */
data class Course(
    val name: String,
    val description: String,
    val imageRes: Int
)

/**
 * Eine vordefinierte Liste von Kursen, die für die Darstellung in der App verwendet wird.
 * Diese Liste enthält mehrere Kursobjekte mit spezifischen Informationen zu jedem Kurs, wie
 * Namen, Beschreibungen und zugehörigen Bildressourcen.
 */
val courseList = listOf(
    Course(
        name = "Linux Basics",
        description = "Lernen Sie die Grundlagen von Linux.",
        imageRes = R.drawable.linux_basics_course
    ),
    Course(
        name = "Advanced Bash",
        description = "Erstellen Sie leistungsstarke Bash-Skripte.",
        imageRes = R.drawable.bash_course
    ),
    Course(
        name = "Docker Essentials",
        description = "Beherrschen Sie Docker-Container.",
        imageRes = R.drawable.docker_course
    ),
    Course(
        name = "Linux Dateisystem und Navigation",
        description = "Erlernen Sie das Dateisystem und die Navigation unter Linux.",
        imageRes = R.drawable.dateisystem_navigation_course
    ),
    Course(
        name = "Textbearbeitung mit vim und nano",
        description = "Erlernen Sie grundlegende Textbearbeitung unter Linux.",
        imageRes = R.drawable.nano_vs_vim_course
    ),
    Course(
        name = "Linux Systemadministration",
        description = "Erlernen Sie die Grundlagen der Systemadministration unter Linux.",
        imageRes = R.drawable.systemadministartion_course
    ),
    Course(
        name = "Netzwerkverwaltung unter Linux",
        description = "Verwalten Sie Netzwerke unter Linux.",
        imageRes = R.drawable.netzwerkverwaltung_course
    )
)

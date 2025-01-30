package com.example.linux_logic_app.components

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
        name = "Linux Grundlagen",
        description = "Deine ersten Schritte: Lerne die Grundlagen des Linux-Betriebssystems kennen. Du wirst verstehen, wie du dich im Terminal zurechtfindest, Dateien verwaltest und einfache Aufgaben automatisierst.",
        imageRes = R.drawable.linux_basics_course
    ),
    Course(
        name = "Fortgeschrittene Bash",
        description = "Werde zum Bash-Profi: Erstelleeeeeeeeeeeeeeeeeeeeeee leistungsstarke Bash-Skripte, um deine Arbeit am Computer zu automatisieren und zu beschleunigen. Erweitere deine Kenntnisse in der Shell und werde zum Experten für die Kommandozeile.\nWerde zum Bash-Profi: Erstelle leistungsstarke Bash-Skripte, um deine Arbeit am Computer zu automatisieren und zu beschleunigen. Erweitere deine Kenntnisse in der Shell und werde zum Experten für die Kommandozeile.\nWerde zum Bash-Profi: Erstelle leistungsstarke Bash-Skripte, um deine Arbeit am Computer zu automatisieren und zu beschleunigen. Erweitere deine Kenntnisse in der Shell und werde zum Experten für die Kommandozeile.\nWerde zum Bash-Profi: Erstelle leistungsstarke Bash-Skripte, um deine Arbeit am Computer zu automatisieren und zu beschleunigen. Erweitere deine Kenntnisse in der Shell und werde zum Experten für die Kommandozeile.",
        imageRes = R.drawable.bash_course
    ),
    Course(
        name = "Docker Kenntnisse",
        description = "Beherrsche Docker: Entwickle und verwalte moderne Anwendungen mit Docker Containern. Lerne, wie du deine Anwendungen portabel und skalierbar machst.",
        imageRes = R.drawable.docker_course
    ),
    Course(
        name = "Linux Dateisystem und Navigation",
        description = "Navigiere durch das Linux-Dateisystem: Versteh den Aufbau des Linux-Dateisystems und wie du effizient darin navigierst. Lerne, wie du Dateien und Verzeichnisse verwaltest und suchst.",
        imageRes = R.drawable.dateisystem_navigation_course
    ),
    Course(
        name = "Textbearbeitung mit vim und nano",
        description = "Werde zum Texteditor-Meister: Lerne die leistungsstarken Texteditoren vim und nano kennen und werde zum Profi in der Textbearbeitung.",
        imageRes = R.drawable.nano_vs_vim_course
    ),
    Course(
        name = "Linux Systemadministration",
        description = "Werde zum Linux-Administrator: Installiere, konfiguriere und verwalte deine eigenen Linux-Systeme. Lerne, wie du Probleme behebst und dein System optimierst.",
        imageRes = R.drawable.systemadministartion_course
    ),
    Course(
        name = "Netzwerkverwaltung unter Linux",
        description = "Baue deine eigenen Netzwerke: Konfiguriere Netzwerke und richte verschiedene Netzwerkdienste ein. Lerne, wie du mehrere Computer miteinander verbindest und sicher kommunizierst.",
        imageRes = R.drawable.netzwerkverwaltung_course
    )
)

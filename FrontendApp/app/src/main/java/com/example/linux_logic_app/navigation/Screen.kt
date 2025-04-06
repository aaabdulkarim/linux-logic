package com.example.linux_logic_app.navigation

/**
 * Screen ist eine sealed class (Versiegelte Klasse), welche die verfügbaren Bildschirme (Screens)
 * in unserer App definiert. Durch die Verwendung einer sealed class wird die Navigation typsicher
 * gestaltet, da alle möglichen Routen der App explizit als Subklasse von Screen definiert werden.
 * Dies minimiert Fehler und erleichtert die Wartung der Navigation in der App.
 *
 * Vorteile dieser Struktur:
 * - Typsicherheit: Fehlerhafte Routen werden zur Kompilierzeit erkannt.
 * - Erweiterbarkeit: Neue Bildschirme können durch Hinzufügen weiterer Subklassen problemlos integriert werden.
 * - Zentrale Verwaltung: Alle Routen der App sind an einem Ort definiert.
 * Jede Subklasse von Screen repräsentiert eine eindeutige Route in der Navigation der App.
 *
 * Konstruktor:
 * - route: String
 *   Definiert die eindeutige Route für einen Bildschirm, die in der Navigation verwendet wird.
 */
sealed class Screen(val route: String) {
    /*
    Ein data object kombiniert die Vorteile eines object (Singleton) und einer data class
    (automatisch generierte Methoden wie equals(), hashCode() und toString()), wodurch es ideal für
    typsichere, effiziente und vergleichbare Einzelexemplare wie Navigationselemente ist.
     */
    data object Start : Screen("start")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Main : Screen("main")
    data object Settings : Screen("settings")
    data object Notifications : Screen("notifications")
    data object Feedback : Screen("feedback")
    data object Help : Screen("help")
    data object Level : Screen("level")

    // Folgende Screens gehören zur Navigation innerhalb des Main-Screens
    data object Customize : Screen("Terminal")
    data object Home : Screen("Home")
    data object Play : Screen("Spielen")
}
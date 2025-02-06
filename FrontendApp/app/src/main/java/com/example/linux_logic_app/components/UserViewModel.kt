package com.example.linux_logic_app.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * Dies ist die Datenklasse für den Linux Logic Benutzer.
 * Enthält grundlegende Benutzerdaten.
 */
data class User(
    val username: String,
    val email: String,
    val password: String // Passwort verschlüsseln, hashen usw. falls derartige komplexe Vorgänge realistisch sind
)

/**
 * Das UserViewModel verwaltet den Zustand des aktuell angemeldeten Benutzers.
 * Es stellt Methoden zum Einloggen, Aktualisieren von Benutzerdaten und Ausloggen bereit.
 * Der ViewModel-Ansatz sorgt dafür, dass alle Composables, die den Zustand beobachten,
 * automatisch neu zusammengesetzt werden, wenn sich der Zustand ändert.
 */
class UserViewModel : ViewModel() {

    // Der aktuell angemeldete Benutzer. Ist null, wenn niemand angemeldet ist.
    private var user by mutableStateOf<User?>(null)

    // Liste registrierter Benutzer
    private val registeredUsers = mutableListOf<User>()

    /**
     * Diese Methode register ist für die Registrierung eines neuen Benutzers zuständig.
     * @param username ist der Benutzername
     * @param email ist die E-Mail des Benutzers
     * @param password ist das Passwort des Benutzers
     * Gibt true zurück, wenn die Registrierung erfolgreich war, false bei Fehlern.
     */
    fun register(username: String, email: String, password: String): Boolean {
        if (emailExists(email)) {
            return false    // Fehler: E-Mail bereits vorhanden
        }

        val newUser = User(username, email, password)
        registeredUsers.add(newUser)
        user = newUser      // Automatische Anmeldung nach Registrierung
        return true
    }

    /**
     * Diese Methode login setzt die Anmeldung eines bestehenden Benutzers um.
     * @param email ist die E-Mail des Benutzers
     * @param password ist das Passwort des Benutzers
     * Gibt true zurück, wenn der Login erfolgreich war, sonst false.
     */
    fun login(email: String, password: String): Boolean {
        // Direktes Suchen nach einem Benutzer, der sowohl E-Mail als auch Passwort korrekt hat
        val liloUser = registeredUsers.firstOrNull { it.email == email && it.password == password }

        return liloUser?.let {
            user = it  // Setze den gefundenen Benutzer
            true       // Erfolgreicher Login
        } ?: false // Rückgabe false, wenn kein Benutzer gefunden wurde
    }


    /**
     * Diese Methode updateUserData aktualisiert bestimmte Felder des aktuell angemeldeten Benutzers.
     * Mit der copy()-Funktion der data class werden nur die geänderten Werte überschrieben.
     * @param newUsername Optional neuer Benutzername. Falls dieser Wert null ist, wird der alte Value beibehalten.
     * @param newEmail Optional neue E-Mail-Adresse. Falls dieser Wert null ist, wird der alte Value beibehalten.
     * @param newPassword Optional neues Passwort. Falls dieser Wert null ist, wird der alte Value beibehalten.
     */
    fun updateUserData(
        newUsername: String? = null,
        newEmail: String? = null,
        newPassword: String? = null
    ): Boolean {
        val currentUser = user ?: return false  // Null-Safety-Prüfung auf user, zurückgeben falls null

        // Wenn neue E-Mail angegeben wurde, prüfen, ob sie nicht vom aktuellen Benutzer verwendet wird
        if (newEmail != null && newEmail != currentUser.email && emailExists(newEmail)) {
            return false    // Fehler: E-Mail bereits vergeben
        }

        // Wenn keine Änderungen vorliegen, einfach true zurückgeben
        if (newUsername == null && newEmail == null && newPassword == null) {
            return true // Keine Änderungen erforderlich
        }

        // Erstelle einen neuen Benutzer, nur mit den geänderten Werten
        val updatedUser = currentUser.copy(
            username = newUsername ?: currentUser.username,
            email = newEmail ?: currentUser.email,
            password = newPassword ?: currentUser.password
        )

        // Benutzer in der Liste aktualisieren
        val index = registeredUsers.indexOf(currentUser)
        if (index != -1) { // Überprüfen, ob der Benutzer in der Liste gefunden wurde
            registeredUsers[index] = updatedUser
        } else {
            return false  // Falls der Benutzer nicht gefunden wurde, kann er nicht aktualisiert werden
        }

        // Den aktuellen Benutzer auf die aktualisierte Version setzen
        user = updatedUser
        return true
    }


    /**
     * Diese Methode logout meldet den Benutzer ab und setzt den User-State auf null.
     */
    fun logout() {
        user = null
    }

    /**
     * Diese Methode emailExists überprüft, ob eine E-Mail-Adresse bereits registriert ist.
     */
    private fun emailExists(email: String): Boolean {
        return registeredUsers.any { it.email == email }
    }
}

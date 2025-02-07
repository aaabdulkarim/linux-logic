package com.example.linux_logic_app.components

import androidx.compose.runtime.State
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
    /* Kapselung
    Interner Zustand, der von Compose beobachtet werden kann.
    _email ist privat und schützt den Zustand vor direktem Zugriff von außen.
    */
    private var _email by mutableStateOf("")

    /*
     Öffentliche, nur lesbare Darstellung des Zustands.
     email bietet Zugriff auf den Zustand ohne direkte Änderungsmöglichkeit.
     */
    val email: String get() = _email

    private var _username by mutableStateOf("")
    val username: String get() = _username

    private var _password by mutableStateOf("")
    val password: String get() = _password

    private var _confirmPassword by mutableStateOf("")
    val confirmPassword: String get() = _confirmPassword

    private var _emailErrorMessage by mutableStateOf<String?>(null)
    val emailErrorMessage: String? get() = _emailErrorMessage

    private var _usernameErrorMessage by mutableStateOf<String?>(null)
    val usernameErrorMessage: String? get() = _usernameErrorMessage

    private var _passwordErrorMessage by mutableStateOf<String?>(null)
    val passwordErrorMessage: String? get() = _passwordErrorMessage

    private var _confPasswordMessage by mutableStateOf<String?>(null)
    val confPasswordMessage: String? get() = _confPasswordMessage

    // Regular Expression um die Email zu validieren
    private val emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"

    // Der aktuell angemeldete Benutzer. Ist null, wenn niemand angemeldet ist.
    //private var user by mutableStateOf<User?>(null)

    private var _user by mutableStateOf<User?>(null)
    val user: User? get() = _user

    // Liste registrierter Benutzer
    private var registeredUsers = mutableListOf<User>()

    init {
        registeredUsers.add(User("Admin", "admin@test.com", "admin123"))
    }

    fun onUsernameChange(newUsername: String) {
        _username = newUsername
        _usernameErrorMessage = validateUsername(newUsername)
    }

    fun onEmailChange(newEmail: String) {
        _email = newEmail
        _emailErrorMessage = validateEmail(newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _password = newPassword
        _passwordErrorMessage = validatePassword(newPassword)
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        _confirmPassword = newConfirmPassword
        _confPasswordMessage = validatePasswords()
    }

    private fun validateEmail(email: String): String? {
        return when {
            email.isEmpty() -> "E-Mail darf nicht leer sein!"
            !email.matches(emailPattern.toRegex()) -> "Invalide E-Mail Adresse!"
            else -> null
        }
    }

    private fun validateUsername(username: String): String? {
        return when {
            username.isBlank() -> "Benutzername darf nicht leer sein!"
            username.length !in 3..20 -> "Benutzername muss zwischen 3 - 20 Zeichen lang sein!"
            !username.matches("^[a-zA-Z0-9_-]+$".toRegex()) -> "Nur Buchstaben, Zahlen, _ und - erlaubt!"
            !username.matches("^[a-zA-Z0-9][a-zA-Z0-9_-]*[a-zA-Z0-9]$".toRegex()) -> "Kein Start oder Ende mit Sonderzeichen!"
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.isEmpty() -> "Passwort darf nicht leer sein!"
            password.length < 8 -> "Passwort muss mindestens 8 Zeichen enthalten!"
            else -> null
        }
    }

    private fun validatePasswords(): String? {
        return if (_password == _confirmPassword)
            null
        else
            "Passwörter stimmen nicht überein!"
    }

    /**
     * Diese Methode login setzt die Anmeldung eines bestehenden Benutzers um.
     * @param email ist die E-Mail des Benutzers
     * @param password ist das Passwort des Benutzers
     * Gibt true zurück, wenn der Login erfolgreich war, sonst false.
     */
    fun login(email: String, password: String): Boolean {
        // Eingabevalidierung durchführen
        val emailError = validateEmail(email)
        val passwordError = validatePassword(password)

        // Fehlermeldungen setzen
        _emailErrorMessage = emailError
        _passwordErrorMessage = passwordError

        // Falls Fehler vorliegen, abbrechen
        if (emailError != null || passwordError != null) {
            return false
        }

        // Benutzer suchen, der mit der eingegebenen E-Mail übereinstimmt
        val registeredUser = registeredUsers.find { it.email == email }

        // Falls kein Benutzer mit der E-Mail existiert, Fehlermeldung setzen und abbrechen
        if (registeredUser == null) {
            _emailErrorMessage = "E-Mail nicht registriert!"
            return false
        }

        // Passwort prüfen, wenn Benutzer existiert
        if (registeredUser.password != password) {
            _passwordErrorMessage = "Passwort stimmt nicht überein!"
            return false
        }

        // Erfolgreicher Login
        _user = registeredUser
        return true
    }

    /**
     * Diese Methode register ist für die Registrierung eines neuen Benutzers zuständig.
     * @param username ist der Benutzername
     * @param email ist die E-Mail des Benutzers
     * @param password ist das Passwort des Benutzers
     * Gibt true zurück, wenn die Registrierung erfolgreich war, false bei Fehlern.
     */
    fun register(username: String, email: String, password: String): Boolean {
        if (emailExists(email)) {
            _emailErrorMessage = "E-Mail bereits registriert!"
            return false    // Fehler: E-Mail bereits vorhanden
        }

        // Eingabevalidierung durchführen
        val usernameError = validateUsername(username)
        val emailError = validateEmail(email)
        val passwordError = validatePassword(password)
        val confirmPasswordError = validatePasswords()

        // Fehlermeldungen setzen
        _usernameErrorMessage = usernameError
        _emailErrorMessage = emailError
        _passwordErrorMessage = passwordError
        _confPasswordMessage = confirmPasswordError

        // Falls Fehler vorliegen, abbrechen
        if (emailError != null || passwordError != null || usernameError != null || confirmPasswordError != null) {
            return false
        }

        val newUser = User(username, email, password)
        registeredUsers.add(newUser)
        _user = newUser      // Automatische Anmeldung nach Registrierung
        return true
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
        val currentUser =
            _user ?: return false // Null-Safety-Prüfung auf user, zurückgeben falls null

        // Wenn eine neue E-Mail angegeben wurde, prüfen, ob sie nicht vom aktuellen Benutzer verwendet wird
        if (newEmail != null && newEmail != currentUser.email && registeredUsers.any { it.email == newEmail })
            return false // Fehler: E-Mail bereits vergeben

        // Keine Änderungen vor, direkt true zurückgeben
        if (newUsername == null && newEmail == null && newPassword == null)
            return true // Keine Änderungen erforderlich

        // Erstelle einen neuen Benutzer, nur mit den geänderten Werten
        val updatedUser = currentUser.copy(
            username = newUsername ?: currentUser.username,
            email = newEmail ?: currentUser.email,
            password = newPassword ?: currentUser.password
        )

        // Benutzer in der Liste aktualisieren
        val index = registeredUsers.indexOfFirst { it.email == currentUser.email }
        if (index == -1) return false // Benutzer nicht gefunden

        registeredUsers[index] = updatedUser // Benutzer aktualisieren
        _user = updatedUser // Den aktuellen Benutzer auf die aktualisierte Version setzen
        return true
    }


    /**
     * Diese Methode logout meldet den Benutzer ab und setzt den User-State auf null.
     */
    fun logout() {
        _user = null
    }

    /**
     * Diese Methode emailExists überprüft, ob eine E-Mail-Adresse bereits registriert ist.
     */
    private fun emailExists(email: String): Boolean {
        return registeredUsers.any { it.email == email }
    }

    /**
     * Diese Methode clearErrorMessages setzt alle ErrorMessages beim Erfolg auf null
     */
    fun clearErrorMessages() {
        //  holt den Wert innerhalb des State Containers während ohne  versucht wird die Referenz zu ändern
        _emailErrorMessage = null
        _usernameErrorMessage = null
        _passwordErrorMessage = null
        _confPasswordMessage = null
    }

    /**
     * Diese Methode clearAllFields setzt alle EingabeFelder zurück
     */
    fun clearAllFields() {
        _username = ""
        _email = ""
        _password = ""
        _confirmPassword = ""
    }
}

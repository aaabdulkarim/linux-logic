package com.example.linux_logic_app.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.linux_logic_app.components.terminal.TerminalColors
import com.example.linux_logic_app.components.terminal.TerminalViewModel
import com.example.linux_logic_app.components.terminal.defaultTerminalColors

/**
 * Dies ist die Datenklasse für den Linux Logic Benutzer.
 * Enthält grundlegende Benutzerdaten.
 */
data class User(
    val username: String,
    val email: String,
    val password: String, // Passwort verschlüsseln, hashen usw. falls derartige komplexe Vorgänge realistisch sind
    val terminalColors: TerminalColors = defaultTerminalColors // Terminalfarben für den Benutzer
)

/**
 * Das UserViewModel verwaltet den Zustand des aktuell angemeldeten Benutzers.
 * Es stellt Methoden zum Einloggen, Aktualisieren von Benutzerdaten und Ausloggen bereit.
 * Der ViewModel-Ansatz sorgt dafür, dass alle Composables, die den Zustand beobachten,
 * automatisch neu zusammengesetzt werden, wenn sich der Zustand ändert.
 */
class UserViewModel : ViewModel() {
    // Verwendung von sealed class für Fehlernachrichten ?

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

    private var _verifyPassword by mutableStateOf("")
    val verifyPassword: String get() = _verifyPassword

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
    private var registeredUsers = mutableListOf<User>().apply {
        add(
            User(
                username = "Admin",
                email = "admin@test.com",
                password = "Admin123#"
            )
        )
        add(
            User(
                username = "Tester",
                email = "test@test.com",
                password = "Test123#"
            )
        )
    }

    //var terminalViewModel by mutableStateOf(TerminalViewModel()) // Das terminalViewModel kann nicht null sein

    // Verwaltet den TerminalViewModel für den aktuell angemeldeten Benutzer
    private var _terminalViewModel: TerminalViewModel = TerminalViewModel(defaultTerminalColors)
    val terminalViewModel: TerminalViewModel get() = _terminalViewModel

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

    fun onVerifyPasswordChange(verifyPassword: String) {
        _verifyPassword = verifyPassword
        _passwordErrorMessage =
            validatePassword(verifyPassword)    // Die Fehlernachricht wird hier einmal gesetzt
    }

    // Single-Expression Functions, wenn der Funktionskörper nur einen einzelnen Ausdruck enthält
    private fun validateEmail(email: String): String? =
        when {
            email.isEmpty() -> "E-Mail darf nicht leer sein!"
            !email.matches(emailPattern.toRegex()) -> "Invalide E-Mail Adresse!"
            else -> null
        }

    private fun validateUsername(username: String): String? =
        when {
            username.isBlank() -> "Benutzername darf nicht leer sein!"
            username.length !in 3..20 -> "Benutzername muss zwischen 3 - 20 Zeichen lang sein!"
            !username.matches("^[a-zA-Z0-9_-]+$".toRegex()) -> "Nur Buchstaben, Zahlen, _ und - erlaubt!"
            !username.matches("^[a-zA-Z0-9][a-zA-Z0-9_-]*[a-zA-Z0-9]$".toRegex()) -> "Kein Start oder Ende mit Sonderzeichen!"
            //^[a-zA-Z0-9_.!-]+$
            else -> null
        }

    //internal als Zugriffsmodifikator Innerhalb des Moduls (UI und Logik)
    private fun validatePassword(password: String): String? =
        when {
            password.isEmpty() -> "Passwort darf nicht leer sein!"
            password.length < 8 -> "Passwort muss mindestens 8 Zeichen enthalten!"
            !password.any { it.isUpperCase() } -> "Passwort muss mindestens einen Großbuchstaben enthalten!"
            !password.any { it.isDigit() } -> "Passwort muss mindestens eine Zahl enthalten!"
            !password.any { !it.isLetterOrDigit() } -> "Passwort muss mindestens ein Sonderzeichen enthalten!"
            else -> null
        }

    private fun validatePasswords(): String? =
        if (_password == _confirmPassword) null
        else "Passwörter stimmen nicht überein!"

    /**
     * Diese Methode login setzt die Anmeldung eines bestehenden Benutzers um.
     * @param email ist die E-Mail des Benutzers
     * @param password ist das Passwort des Benutzers
     * Gibt true zurück, wenn der Login erfolgreich war, sonst false.
     */
    fun login(email: String, password: String): Boolean {
        clearErrorMessages()

        // Eingabevalidierung durchführen und Fehlermeldungen setzen
        _emailErrorMessage = validateEmail(email)
        _passwordErrorMessage = validatePassword(password)

        // Falls Fehler vorliegen, abbrechen
        if (_emailErrorMessage != null || _passwordErrorMessage != null) return false

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

        // Initialisiere das TerminalViewModel mit den Terminalfarben des Benutzers
        _terminalViewModel = TerminalViewModel(registeredUser.terminalColors)

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
        clearErrorMessages()

        if (emailExists(email)) _emailErrorMessage =
            "E-Mail bereits registriert!"      // Fehler: E-Mail bereits vorhanden

        if (usernameExists(username)) _usernameErrorMessage =
            "Benutzername bereits vergeben!"      // Fehler: Benutzername bereits vergeben

        if (_emailErrorMessage != null || _usernameErrorMessage != null) return false

        // Eingabevalidierung durchführen und Fehlermeldungen setzen
        _usernameErrorMessage = validateUsername(username)
        _emailErrorMessage = validateEmail(email)
        _passwordErrorMessage = validatePassword(password)
        _confPasswordMessage = validatePasswords()

        // Falls Fehler vorliegen, abbrechen
        if (listOf(
                _usernameErrorMessage,
                _emailErrorMessage,
                _passwordErrorMessage,
                _confPasswordMessage
            ).any { it != null }
        ) {
            return false
        }

        val newUser = User(username, email, password)
        registeredUsers.add(newUser)
        _user = newUser      // Automatische Anmeldung nach Registrierung
        /*
        Automatisch alle Felder und Errors zurücksetzen, sodass man beim erfolgreichen Registrieren
        sich danach der Anmeldung unterziehen muss, was eine gewisse Identiät erfordert.
         */
        clearAllFields()
        clearErrorMessages()
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
        // Sicherstellen, dass ein Benutzer eingeloggt ist.
        val currentUser = _user ?: return false

        // Keine Änderungen erforderlich
        if (newUsername == null && newEmail == null && newPassword == null) return true

        clearErrorMessages()

        if (newEmail != null && newEmail != currentUser.email && emailExists(email)) _emailErrorMessage =
            "E-Mail bereits registriert!"      // Fehler: E-Mail bereits vorhanden

        if (newUsername != null && newUsername != currentUser.username && usernameExists(newUsername)) _usernameErrorMessage =
            "Benutzername bereits vergeben!"      // Fehler: Benutzername bereits vergeben

        // Eingabevalidierung durchführen und Fehlermeldungen setzen
        _usernameErrorMessage = validateUsername(newUsername ?: currentUser.username)
        _emailErrorMessage = validateEmail(newEmail ?: currentUser.email)
        _passwordErrorMessage = validatePassword(newPassword ?: currentUser.password)

        if (_emailErrorMessage != null || _usernameErrorMessage != null || _passwordErrorMessage != null) return false

        // Benutzer kopieren mit neuen Werten
        val updatedUser = currentUser.copy(
            //Der Elvis-Operator in Kotlin. Er bedeutet "falls der linke Ausdruck null ist, benutze den rechten Ausdruck stattdessen".
            username = newUsername ?: currentUser.username,
            email = newEmail ?: currentUser.email,
            password = newPassword ?: currentUser.password
        )

        // Benutzer-Index suchen und Daten aktualisieren
        val index = registeredUsers.indexOfFirst { it.email == currentUser.email }
        if (index == -1) return false // Benutzer nicht gefunden

        // Daten des Benutzers aktualisieren
        registeredUsers[index] = updatedUser
        _user = updatedUser
        return true
    }

    fun cancelChanges() {
        // Setze alle Eingabefelder (Bearbeitungszustand) auf die Originalwerte des aktuell angemeldeten Benutzers zurück.
        _username = _user?.username.orEmpty()
        _email = _user?.email.orEmpty()
        _password = _user?.password.orEmpty()
        _confirmPassword = _user?.password.orEmpty()

        // Löschen aller angezeigten Fehlermeldungen.
        clearErrorMessages()
    }

    /**
     * Diese Methode logout meldet den Benutzer ab und setzt den User-State auf null.
     */
    fun logout() {
        _user = null
        clearAllFields()
        clearErrorMessages()
    }

    /**
     * Diese Methode emailExists überprüft, ob eine E-Mail-Adresse bereits besetzt ist.
     * @param email ist die E-Mail welche gesucht ist
     */
    private fun emailExists(email: String): Boolean = registeredUsers.any { it.email == email }

    /**
     * Diese Methode usernameExists überprüft, ob ein Benutzername bereits vergeben ist.
     * @param username ist der Benutzername welcher gesucht ist
     */
    private fun usernameExists(username: String): Boolean =
        registeredUsers.any { it.username == username }


    /**
     * Diese Methode clearErrorMessages setzt alle ErrorMessages beim Erfolg auf null
     */
    fun clearErrorMessages() {
        // .value holt den Wert innerhalb des State Containers während ohne .value versucht wird die Referenz zu ändern
        _emailErrorMessage = null
        _usernameErrorMessage = null
        _passwordErrorMessage = null
        _confPasswordMessage = null
    }

    /**
     * Diese Methode clearAllFields setzt alle EingabeFelder zurück
     */
    private fun clearAllFields() {
        _username = ""
        _email = ""
        _password = ""
        _confirmPassword = ""
    }

    fun verifyPassword(verifyPassword: String): Boolean {
        clearErrorMessages() // Fehlernachrichten zurücksetzen

        // Prüfe, ob die Passwortvalidierung bereits einen Fehler zurückgegeben hat
        if (_passwordErrorMessage != null) return false

        // Prüfe, ob das Passwort mit dem gespeicherten übereinstimmt
        return if (_user?.password == verifyPassword) {
            _verifyPassword = ""
            true // Erfolgreiche Validierung
        } else {
            _passwordErrorMessage = "Passwort stimmt nicht überein!" // Fehler setzen
            false // Fehlerfall
        }
    }

    fun cancelVerification() {
        _verifyPassword = ""    // Löschen des Inputs für die Passwort Verifizierung
        clearErrorMessages()    // Löschen aller angezeigten Fehlermeldungen.
    }

    fun updateTerminalColors(newColors: TerminalColors) {
        val currentUser = _user ?: return
        val updatedUser = currentUser.copy(terminalColors = newColors)

        // Benutzer in der registrierten Liste aktualisieren
        val index = registeredUsers.indexOfFirst { it.email == currentUser.email }
        if (index != -1) {
            registeredUsers[index] = updatedUser
            _user = updatedUser  // Aktualisieren des aktuellen Benutzers
        }

        // TerminalViewModel auch aktualisieren
        _terminalViewModel.updateColors(newColors)
    }

    fun updateDefaultColors(useDefault: Boolean) {
        val currentUser = _user ?: return
        val updatedUser = currentUser.copy(terminalColors = defaultTerminalColors)

        // Benutzer in der registrierten Liste aktualisieren
        val index = registeredUsers.indexOfFirst { it.email == currentUser.email }
        if (index != -1) {
            registeredUsers[index] = updatedUser
            _user = updatedUser  // Aktualisieren des aktuellen Benutzers
        }

        _terminalViewModel.updateDefaultMode(useDefault) // Wenn terminalViewModel null ist, Standardwert true
    }
}

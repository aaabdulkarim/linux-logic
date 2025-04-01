package com.example.linux_logic_app.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.linux_logic_app.components.scenario.Scenario

/**
 * Dies ist die Datenklasse für den Linux Logic Benutzer.
 * Enthält grundlegende Benutzerdaten.
 * @property username Der Benutzername.
 * @property email Die E-Mail-Adresse des Benutzers.
 * @property password Das (hier unverschlüsselte) Passwort.
 * In einer realen Anwendung sollten Passwörter verschlüsselt oder gehasht werden (Backend-Implementierung)
 * @property terminalColors Die Terminalfarben, die für den Benutzer festgelegt sind.
 */
data class User(
    val username: String,
    val email: String,
    val password: String, // Passwort verschlüsseln, hashen usw. falls derartige komplexe Vorgänge realistisch sind
    val terminalColors: TerminalColors = defaultTerminalColors, // Terminalfarben für den Benutzer
)

/**
 * Das UserViewModel verwaltet den Zustand des aktuell angemeldeten Benutzers und ist das zentrale
 * Herzstück der Logik und über die gesamte mobile App bekannt, kapselt verschiedenste Klassen, ViewModels
 * und Methoden, welche Unterschiede in der Funktion aufweisen und klar getrennt sind, jedoch hier fusionieren.
 * Es stellt Methoden zum Einloggen, Aktualisieren von Benutzerdaten und Ausloggen bereit.
 * Durch den Einsatz von Compose und reaktiven State-Containern werden alle Composables,
 * die diesen Zustand beobachten, automatisch neu zusammengesetzt, wenn sich der Zustand ändert.
 */
class UserViewModel : ViewModel() {
    /**
     * Interner Zustand: Privater State, der von Compose beobachtet wird.
     * Die Verwendung von privaten Variablen (_email, _username, etc.) schützt den Zustand vor direktem externen Zugriff.
     * Öffentliche Getter ermöglichen den reinen Lesezugriff.
     */
    private var _email by mutableStateOf("")
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

    // Validierungsmuster und -logik

    // Regular Expression um die Email zu validieren.
    private val emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"

    // Der aktuell angemeldete Benutzer. Ist null, wenn niemand angemeldet ist.
    private var _user by mutableStateOf<User?>(null)
    val user: User? get() = _user

    // Registrierte Benutzer: Vorab definierte Benutzer in einer Liste.
    private var registeredUsers = mutableListOf<User>().apply {
        add(
            User(
                username = "Admin",
                email = "admin@test.com",
                password = "Admin123#",
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

    /**
     * Terminal- und Level-ViewModels: Zustandsverwaltung für Terminalfarben und Kurslevels.
     * TerminalViewModel ist immer vorhanden und wird beim Login aktualisiert.
     * LevelViewModel wird initial auf null gesetzt und später durch die Szenario-Auswahl belegt.
     */
    private var _terminalViewModel: TerminalViewModel = TerminalViewModel(defaultTerminalColors)

    /**
     * Liefert den aktuell verwendeten TerminalViewModel.
     * @return Das TerminalViewModel, welches die Terminalfarben und -logik enthält.
     */
    val terminalViewModel: TerminalViewModel get() = _terminalViewModel

    private var _levelViewModel by mutableStateOf<LevelViewModel?>(null)
    val levelViewModel: LevelViewModel? get() = _levelViewModel

    // Methoden zur Aktualisierung der Eingabefelder und Validierung
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
        // Setzt die Fehlernachricht basierend auf der Passwortvalidierung
        _passwordErrorMessage = validatePassword(verifyPassword)
    }

    // Validierungsfunktionen: Single-Expression Functions für kurze, prägnante Validierung.
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
            else -> null
        }

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

    // Authentifizierungsfunktionen: Login, Registrierung, Update, Logout.

    /**
     * Diese Methode login setzt die Anmeldung eines bestehenden Benutzers um.
     * Es werden zunächst die Eingaben validiert. Falls keine Fehler auftreten,
     * wird nach einem registrierten Benutzer gesucht. Bei erfolgreicher Validierung
     * und Authentifizierung wird der Benutzer als aktuell angemeldet gesetzt.
     * @param email E-Mail des Benutzers.
     * @param password Passwort des Benutzers.
     * @return true, wenn der Login erfolgreich war, sonst false.
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

        // Passwortprüfung
        if (registeredUser.password != password) {
            _passwordErrorMessage = "Passwort stimmt nicht überein!"
            return false
        }

        // Erfolgreicher Login: Benutzer und zugehörige ViewModels aktualisieren
        _user = registeredUser
        _terminalViewModel = TerminalViewModel(registeredUser.terminalColors)
        _levelViewModel = null // LevelViewModel wird erst bei Auswahl eines Scenarios gesetzt

        return true
    }

    /**
     * Diese Methode register ist für die Registrierung eines neuen Benutzers zuständig.
     * Nach Validierung der Eingaben wird überprüft, ob die E-Mail oder der Benutzername bereits vergeben sind.
     * Bei Erfolg wird der neue Benutzer registriert und automatisch angemeldet.
     * @param username Der gewünschte Benutzername.
     * @param email Die E-Mail-Adresse.
     * @param password Das gewünschte Passwort.
     * @return true, wenn die Registrierung erfolgreich war, false bei Fehlern.
     */
    fun register(username: String, email: String, password: String): Boolean {
        clearErrorMessages()

        if (emailExists(email)) _emailErrorMessage =
            "E-Mail bereits registriert!"      // Fehler: E-Mail bereits vorhanden

        if (usernameExists(username)) _usernameErrorMessage =
            "Benutzername bereits vergeben!"   // Fehler: Benutzername bereits vergeben

        if (_emailErrorMessage != null || _usernameErrorMessage != null) return false

        // Eingabevalidierung
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
        _user = newUser      // Automatische Anmeldung nach erfolgreicher Registrierung

        // Felder und Fehlermeldungen zurücksetzen, um den Zustand zu säubern
        clearAllFields()
        clearErrorMessages()
        return true
    }

    /**
     * Diese Methode updateUserData aktualisiert bestimmte Felder des aktuell angemeldeten Benutzers.
     * Mithilfe der copy()-Funktion der Datenklasse werden nur die geänderten Werte überschrieben.
     * @param newUsername Optional neuer Benutzername. Falls null, bleibt der alte Wert erhalten.
     * @param newEmail Optional neue E-Mail-Adresse. Falls null, bleibt der alte Wert erhalten.
     * @param newPassword Optional neues Passwort. Falls null, bleibt der alte Wert erhalten.
     * @return true, wenn die Aktualisierung erfolgreich war, false andernfalls.
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

        if (newEmail != null && newEmail != currentUser.email && emailExists(email))
            _emailErrorMessage = "E-Mail bereits registriert!"  // Fehler: E-Mail bereits vorhanden

        if (newUsername != null && newUsername != currentUser.username && usernameExists(newUsername))
            _usernameErrorMessage =
                "Benutzername bereits vergeben!" // Fehler: Benutzername bereits vergeben

        // Validierung der Eingaben
        _usernameErrorMessage = validateUsername(newUsername ?: currentUser.username)
        _emailErrorMessage = validateEmail(newEmail ?: currentUser.email)
        _passwordErrorMessage = validatePassword(newPassword ?: currentUser.password)

        if (_emailErrorMessage != null || _usernameErrorMessage != null || _passwordErrorMessage != null)
            return false

        // Aktualisierung des Benutzers mit neuen Werten
        val updatedUser = currentUser.copy(
            username = newUsername ?: currentUser.username,
            email = newEmail ?: currentUser.email,
            password = newPassword ?: currentUser.password
        )

        // Suche den Benutzer in der registrierten Liste und aktualisiere den Eintrag
        val index = registeredUsers.indexOfFirst { it.email == currentUser.email }
        if (index == -1) return false // Benutzer nicht gefunden

        registeredUsers[index] = updatedUser
        _user = updatedUser
        return true
    }

    /**
     * Setzt alle Eingabefelder (im Bearbeitungszustand) auf die Originalwerte des aktuell angemeldeten Benutzers zurück.
     */
    fun cancelChanges() {
        _username = _user?.username.orEmpty()
        _email = _user?.email.orEmpty()
        _password = _user?.password.orEmpty()
        _confirmPassword = _user?.password.orEmpty()
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
     * Überprüft, ob eine gegebene E-Mail-Adresse bereits registriert ist.
     * @param email Die zu überprüfende E-Mail-Adresse.
     * @return true, wenn die E-Mail bereits existiert, sonst false.
     */
    private fun emailExists(email: String): Boolean = registeredUsers.any { it.email == email }

    /**
     * Überprüft, ob ein gegebener Benutzername bereits vergeben ist.
     * @param username Der zu überprüfende Benutzername.
     * @return true, wenn der Benutzername bereits existiert, sonst false.
     */
    private fun usernameExists(username: String): Boolean =
        registeredUsers.any { it.username == username }

    /**
     * Setzt alle Fehlernachrichten zurück.
     */
    fun clearErrorMessages() {
        _emailErrorMessage = null
        _usernameErrorMessage = null
        _passwordErrorMessage = null
        _confPasswordMessage = null
    }

    /**
     * Setzt alle Eingabefelder zurück.
     */
    private fun clearAllFields() {
        _username = ""
        _email = ""
        _password = ""
        _confirmPassword = ""
    }

    /**
     * Überprüft, ob das eingegebene Passwort mit dem aktuell gespeicherten Passwort übereinstimmt.
     * @param verifyPassword Das zur Überprüfung eingegebene Passwort.
     * @return true, wenn die Passwörter übereinstimmen, andernfalls false.
     */
    fun verifyPassword(verifyPassword: String): Boolean {
        clearErrorMessages() // Fehlernachrichten zurücksetzen

        if (_passwordErrorMessage != null) return false

        return if (_user?.password == verifyPassword) {
            _verifyPassword = ""
            true // Erfolgreiche Validierung
        } else {
            _passwordErrorMessage = "Passwort stimmt nicht überein!" // Fehler setzen
            false
        }
    }

    /**
     * Setzt die Verifizierungsdaten zurück und löscht alle Fehlermeldungen.
     */
    fun cancelVerification() {
        _verifyPassword = ""
        clearErrorMessages()
    }

    /**
     * Aktualisiert die Terminalfarben des aktuell angemeldeten Benutzers.
     * Der Benutzer wird in der registrierten Liste aktualisiert und das TerminalViewModel erhält
     * die neuen Farbwerte.
     * @param newColors Die neuen Terminalfarben.
     */
    fun updateTerminalColors(newColors: TerminalColors) {
        val currentUser = _user ?: return
        val updatedUser = currentUser.copy(terminalColors = newColors)

        // Aktualisiere den Benutzer in der registrierten Liste
        val index = registeredUsers.indexOfFirst { it.email == currentUser.email }
        if (index != -1) {
            registeredUsers[index] = updatedUser
            _user = updatedUser
        }

        // Aktualisiere das TerminalViewModel, damit die UI die neuen Farben anzeigt
        _terminalViewModel.updateColors(newColors)
    }

    /**
     * Schaltet zwischen den benutzerdefinierten Farben und den Default-Farben um.
     * Bei Aktivierung des Default-Modus werden die Default-Farben gesetzt und
     * der Benutzer in der registrierten Liste entsprechend aktualisiert.
     * @param useDefault Boolean, das angibt, ob die Default-Farben verwendet werden sollen.
     */
    fun updateDefaultColors(useDefault: Boolean) {
        val currentUser = _user ?: return
        val updatedUser = currentUser.copy(terminalColors = defaultTerminalColors)

        // Aktualisiere den Benutzer in der registrierten Liste
        val index = registeredUsers.indexOfFirst { it.email == currentUser.email }
        if (index != -1) {
            registeredUsers[index] = updatedUser
            _user = updatedUser
        }

        // Aktualisiere das TerminalViewModel entsprechend dem Default-Modus
        _terminalViewModel.updateDefaultMode(useDefault)
    }

    /**
     * Setzt das aktuelle Scenario und initialisiert das LevelViewModel.
     * Diese Methode kapselt die gesamte Logik zur Szenario-Auswahl,
     * indem sie das LevelViewModel nur dann erstellt, wenn ein Scenario ausgewählt wurde.
     * @param scenario Das vom Benutzer ausgewählte Scenario. Falls null, wird _levelViewModel auf null gesetzt.
     */
    fun selectScenarioForUser(scenario: Scenario?) {
        _levelViewModel = scenario?.let { LevelViewModel(it) }
    }
}

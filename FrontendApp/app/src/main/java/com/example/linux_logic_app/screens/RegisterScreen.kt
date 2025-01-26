package com.example.linux_logic_app.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material.icons.twotone.Close
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material.icons.twotone.Lock
import androidx.compose.material.icons.twotone.PersonAddAlt
import androidx.compose.material.icons.twotone.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.linux_logic_app.R
import com.example.linux_logic_app.navigation.Screen
import com.example.linux_logic_app.ui.theme.LiloMain

@Composable
fun RegisterScreen(navController: NavController) {
    val (email, setEmail) = rememberSaveable { mutableStateOf("") }
    val (username, setUsername) = rememberSaveable { mutableStateOf("") }
    val (password, setPassword) = rememberSaveable { mutableStateOf("") }
    val (passwordVisible, setPasswordVisible) = rememberSaveable { mutableStateOf(false) }
    val (confirmPassword, setConfirmPassword) = rememberSaveable { mutableStateOf("") }
    var confirmPasswordErrorMessage by rememberSaveable { mutableStateOf<String?>(null) }
    /*
    ist in Kotlin als Destrukturierungsdeklaration bekannt, mit der man die von bestimmten
    Funktionen zurückgegebenen Werte direkt in separate Variablen auspacken können. Kotlin erlaubt
    es, dieses Objekt in zwei Variablen zu zerlegen: eine zum Lesen des Wertes und eine zum Aktualisieren
     */

    // Ideen: https://medium.com/@ramadan123sayed/comprehensive-guide-to-textfields-in-jetpack-compose-f009c4868c54
    var emailErrorMessage by rememberSaveable { mutableStateOf<String?>(null) }

    var usernameErrorMessage by rememberSaveable { mutableStateOf<String?>(null) }

    val isFormValid = emailErrorMessage == null &&
            usernameErrorMessage == null &&
            password.isNotEmpty() &&
            password.length >= 8 &&
            password == confirmPassword

    // Regular expression to validate email format
    val emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LiloMain),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .weight(0.25f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.linux_logic_pinguin),
                    contentDescription = "Linux Logic Penguin",
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.TwoTone.PersonAddAlt,
                        contentDescription = "PersonAddAlt Icon for Register",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Neu hier?",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .weight(0.75f)
                .padding(16.dp) // Padding hinzufügen für den gesamten Inhalt
                //.clip(RoundedCornerShape(topStart = 1.dp, topEnd = 16.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center), // Vertikale Zentrierung
                horizontalAlignment = Alignment.CenterHorizontally, // Horizontale Zentrierung
            ) {
                Text(
                    text = "Legen Sie ein Konto an",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        setUsername(it)
                        setUsername(it)
                        usernameErrorMessage = when {
                            it.isBlank() -> "Der Benutzername darf nicht leer sein!"
                            it.length !in 3..20 -> "Der Benutzername muss zwischen 3 und 20 Zeichen lang sein!"
                            !it.matches("^[a-zA-Z0-9_-]+$".toRegex()) -> "Nur Buchstaben, Zahlen, _ und - sind erlaubt!"
                            !it.matches("^[a-zA-Z0-9][a-zA-Z0-9_-]*[a-zA-Z0-9]$".toRegex()) -> "Kein Start oder Ende mit Sonderzeichen!"
                            "".any { word -> it.contains(word, ignoreCase = true) } -> "Der Benutzername enthält verbotene Wörter!"
                            else -> null // Benutzername ist gültig
                        }
                    },
                    label = {
                        Text(
                            text = "Benutzername",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Bitte Ihren Benutzernamen eingeben",
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.TwoTone.AccountCircle,
                            contentDescription = "Account Icon for Register",
                            tint = Color(0xFF569191)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(), // Volle Breite der Box
                    shape = RoundedCornerShape(8.dp), // Abgerundete Ecken
                    singleLine = true, // Verhindert den Zeilenumbruch
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text, // Sicherstellen, dass das Textfeld als E-Mail-Input genutzt wird
                        imeAction = ImeAction.Next // Es wird zum nächsten Input weitergeleitet
                    ),
                    isError = usernameErrorMessage != null
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        setEmail(it)
                        emailErrorMessage = if (it.matches(emailPattern.toRegex())) {
                            null
                        } else {
                            "Invalide E-Mail Adresse!"
                        }
                    },
                    label = {
                        Text(
                            text = "E-Mail Adresse",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Bitte Ihre E-Mail Adresse eingeben",
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.TwoTone.Email,
                            contentDescription = "Email Icon for Register",
                            tint = Color(0xFF569191)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(), // Volle Breite der Box
                    shape = RoundedCornerShape(8.dp), // Abgerundete Ecken
                    singleLine = true, // Verhindert den Zeilenumbruch
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email, // Sicherstellen, dass das Textfeld als E-Mail-Input genutzt wird
                        imeAction = ImeAction.Next // Es wird zum nächsten Input weitergeleitet
                    ),
                    isError = emailErrorMessage != null
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = setPassword,
                    label = {
                        Text(
                            text = "Passwort",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Bitte Ihr Passwort eingeben",
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.TwoTone.Lock,
                            contentDescription = "Password Icon for Login",
                            tint = Color(0xFF569191)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(), // Volle Breite der Box
                    shape = RoundedCornerShape(8.dp), // Abgerundete Ecken
                    singleLine = true, // Verhindert den Zeilenumbruch
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password, // Sicherstellen, dass das Textfeld als E-Mail-Input genutzt wird
                        imeAction = ImeAction.Done // Es wird zum nächsten Input weitergeleitet
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    isError = password.isNotEmpty() && password.length < 8,
                    trailingIcon = {
                        val image = if (passwordVisible) Icons.TwoTone.Close else Icons.TwoTone.Check
                        val description = if (passwordVisible) "Hide password" else "Show password"
                        IconButton(onClick = { setPasswordVisible(!passwordVisible) }) {
                            Icon(image, contentDescription = description)
                        }
                    },
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        setConfirmPassword(it)
                        confirmPasswordErrorMessage = if (it == password) {
                            null
                        } else {
                            "Passwörter stimmen nicht überein!"
                        }
                    },
                    label = {
                        Text(
                            text = "Passwort bestätigen",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Bitte Passwort erneut eingeben",
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.TwoTone.Refresh,
                            contentDescription = "Password Confirmation Icon",
                            tint = Color(0xFF569191)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(), // Volle Breite der Box
                    shape = RoundedCornerShape(8.dp), // Abgerundete Ecken
                    singleLine = true, // Verhindert den Zeilenumbruch
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password, // Eingabe als Passwort
                        imeAction = ImeAction.Done // Fertigstellen der Eingabe
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    isError = confirmPasswordErrorMessage != null,
                    trailingIcon = {
                        val image = if (passwordVisible) Icons.TwoTone.Close else Icons.TwoTone.Check
                        val description = if (passwordVisible) "Hide password" else "Show password"
                        IconButton(onClick = { setPasswordVisible(!passwordVisible) }) {
                            Icon(image, contentDescription = description)
                        }
                    },
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        navController.navigate(Screen.Main.route)
                        Log.i("Credentials", "Username: $username; E-Mail: $email; Password: $password")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    contentPadding = PaddingValues(16.dp),
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = Color(0xFFFF8c00),
                        contentColor = Color.White,
                        disabledContainerColor = Color(0xFFCECECE),
                        disabledContentColor = Color(0xFF7F7F7F)
                    ),
                    enabled = isFormValid
                ) {
                    Text(
                        text = "Registrieren",
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Registrieren mit Google:",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Image(
                        painterResource(id = R.drawable.google_logo),
                        contentDescription = "Login mittels Google Account",
                        modifier = Modifier
                            .size(60.dp)
                            .clickable {

                            }
                    )

                    /*Image(
                        painterResource(id = R.drawable.microsoft_logo),
                        contentDescription = "Login mittels Microsoft Account",
                        modifier = Modifier
                            .size(60.dp)
                            .clickable {

                            }
                    )

                    Image(
                        painterResource(id = R.drawable.x_logo),
                        contentDescription = "Login mittels X Account",
                        modifier = Modifier
                            .size(60.dp)
                            .clickable {

                            }
                    )*/
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Sie haben bereits ein Konto?",
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Anmelden",
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.Login.route)
                                Log.i("RegisterScreen", "User is performing - Action: \"Login\" -")
                            },
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}

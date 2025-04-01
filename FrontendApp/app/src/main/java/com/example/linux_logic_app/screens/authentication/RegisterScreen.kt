package com.example.linux_logic_app.screens.authentication

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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material.icons.twotone.Password
import androidx.compose.material.icons.twotone.PermIdentity
import androidx.compose.material.icons.twotone.PersonAddAlt
import androidx.compose.material.icons.twotone.Repeat
import androidx.compose.material.icons.twotone.Visibility
import androidx.compose.material.icons.twotone.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.linux_logic_app.components.viewmodels.UserViewModel
import com.example.linux_logic_app.navigation.Screen
import com.example.linux_logic_app.ui.theme.LiloMain
import com.example.linux_logic_app.ui.theme.LiloOrange

/**
 * Der RegisterScreen - Registrierungsbildschirm der App.
 * Dieses Composable implementiert den Registrierungs-Flow, indem es ein Formular mit Feldern für
 * Benutzername, E-Mail, Passwort und Passwortbestätigung darstellt. Es validiert die Eingaben in Echtzeit
 * und zeigt entsprechende Fehlermeldungen an. Nach erfolgreicher Registrierung wird der Nutzer zur
 * Login-Seite navigiert.
 * Wichtige Aspekte:
 * - Validierung und Anzeige von Fehlermeldungen für alle Eingabefelder.
 * - Dynamische Passwortsichtbarkeit mittels eines Trailing Icons.
 * - Scrollbarkeit des Formulars für kleinere Bildschirme.
 * @param navController Steuert die Navigation zwischen den Screens.
 * @param userViewModel Verwaltet den Zustand und die Logik der Nutzerregistrierung.
 */
@Composable
fun RegisterScreen(navController: NavController, userViewModel: UserViewModel) {
    val username = userViewModel.username
    val email = userViewModel.email
    val password = userViewModel.password
    val confirmPassword = userViewModel.confirmPassword

    val emailErrorMessage = userViewModel.emailErrorMessage
    val usernameErrorMessage = userViewModel.usernameErrorMessage
    val passwordErrorMessage = userViewModel.passwordErrorMessage
    val confPasswordMessage = userViewModel.confPasswordMessage

    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }
    /*
    ist in Kotlin als Destrukturierungsdeklaration bekannt, mit der man die von bestimmten
    Funktionen zurückgegebenen Werte direkt in separate Variablen auspacken können. Kotlin erlaubt
    es, dieses Objekt in zwei Variablen zu zerlegen: eine zum Lesen des Wertes und eine zum Aktualisieren.
    Ideen: https://medium.com/@ramadan123sayed/comprehensive-guide-to-textfields-in-jetpack-compose-f009c4868c54
    */

    val isFormValid = emailErrorMessage == null && usernameErrorMessage == null &&
            passwordErrorMessage == null && confPasswordMessage == null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .imePadding()  // Fügt Platz hinzu, wenn die Tastatur eingeblendet wird.
            .windowInsetsPadding(WindowInsets.navigationBars), // Platz für die Navigationsleiste
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Oberer Bereich mit Bild und Begrüßung
        Box(
            modifier = Modifier
                .weight(0.25f)
                .background(LiloMain)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.linux_logic_pinguin),
                    contentDescription = "Linux Logic Penguin",
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.TwoTone.PersonAddAlt,
                        contentDescription = "PersonAddAlt Icon for Register",
                        tint = Color.White,
                        modifier = Modifier.padding(top = 4.dp)
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

        // Formularbereich in einem scrollbaren Container
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .weight(0.75f)
                .padding(16.dp)
            //.clip(RoundedCornerShape(topStart = 1.dp, topEnd = 16.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .verticalScroll(rememberScrollState()), // Ermöglicht Scrollen bei vielen Eingabefeldern
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Legen Sie ein Konto an",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Benutzername Eingabefeld
                OutlinedTextField(
                    value = username,
                    onValueChange = { userViewModel.onUsernameChange(it) },
                    label = {
                        Text(
                            text = "Benutzername",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Bitte Benutzernamen eingeben",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.TwoTone.PermIdentity,
                            contentDescription = "Identity Icon for Register",
                            tint = LiloMain
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    isError = usernameErrorMessage != null,
                    supportingText = {
                        usernameErrorMessage?.let {
                            Text(
                                text = it,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                )

                // E-Mail Eingabefeld
                OutlinedTextField(
                    value = email,
                    onValueChange = { userViewModel.onEmailChange(it) },
                    label = {
                        Text(
                            text = "E-Mail Adresse",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Bitte E-Mail eingeben",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.TwoTone.Email,
                            contentDescription = "Email Icon for Register",
                            tint = LiloMain
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    isError = emailErrorMessage != null,
                    supportingText = {
                        emailErrorMessage?.let {
                            Text(
                                text = it,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                )

                // Passwort Eingabefeld
                OutlinedTextField(
                    value = password,
                    onValueChange = { userViewModel.onPasswordChange(it) },
                    label = {
                        Text(
                            text = "Passwort",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Bitte Passwort eingeben",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.TwoTone.Password,
                            contentDescription = "Password Icon for Register",
                            tint = LiloMain
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image =
                            if (passwordVisible) Icons.TwoTone.Visibility else Icons.TwoTone.VisibilityOff
                        val description =
                            if (passwordVisible) "Showed password" else "Hidden password"
                        IconButton(onClick = { setPasswordVisible(!passwordVisible) }) {
                            Icon(
                                imageVector = image,
                                contentDescription = description,
                                tint = LiloOrange
                            )
                        }
                    },
                    isError = passwordErrorMessage != null,
                    supportingText = {
                        passwordErrorMessage?.let {
                            Text(
                                text = it,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                )

                // Passwort bestätigen Eingabefeld
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { userViewModel.onConfirmPasswordChange(it) },
                    label = {
                        Text(
                            text = "Passwort bestätigen",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Bitte Passwort bestätigen",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.TwoTone.Repeat,
                            contentDescription = "Password Confirmation Icon for Register",
                            tint = LiloMain
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image =
                            if (passwordVisible) Icons.TwoTone.Visibility else Icons.TwoTone.VisibilityOff
                        val description =
                            if (passwordVisible) "Showed password" else "Hidden password"
                        IconButton(onClick = { setPasswordVisible(!passwordVisible) }) {
                            Icon(
                                imageVector = image,
                                contentDescription = description,
                                tint = LiloOrange
                            )
                        }
                    },
                    isError = confPasswordMessage != null,
                    supportingText = {
                        confPasswordMessage?.let {
                            Text(
                                text = it,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Registrierungs-Button
                Button(
                    onClick = {
                        if (userViewModel.register(
                                username.trim(),
                                email.trim(),
                                password.trim()
                            )
                        ) {
                            Log.i(
                                "New User Credentials",
                                "Username: ${username.trim()}; E-Mail: ${email.trim()}; Password: ${password.trim()}"
                            )
                            // Nach erfolgreicher Registrierung wird zur Login-Seite navigiert
                            navController.navigate(Screen.Login.route)
                            userViewModel.clearErrorMessages()
                            //userViewModel.clearAllFields()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    contentPadding = PaddingValues(16.dp),
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = LiloOrange,
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

                // Row für alternative Registrierungsoptionen
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp)
                        .clickable { },
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Registrieren mit Google:",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Image(
                        painter = painterResource(id = R.drawable.google_logo),
                        contentDescription = "Login mittels Google Account",
                        modifier = Modifier.size(60.dp)
                    )

                    /*Image(
                        painter = painterResource(id = R.drawable.microsoft_logo),
                        contentDescription = "Login mittels Microsoft Account",
                        modifier = Modifier
                            .size(60.dp)
                            .clickable { }
                    )

                    Image(
                        painter = painterResource(id = R.drawable.x_logo),
                        contentDescription = "Login mittels X Account",
                        modifier = Modifier
                            .size(60.dp)
                            .clickable { }
                    )*/
                }

                // Row zur Navigation zum Login-Screen, falls bereits ein Konto besteht
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Sie haben bereits ein Konto?",
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Anmelden",
                        modifier = Modifier.clickable {
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


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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.Login
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material.icons.twotone.Password
import androidx.compose.material.icons.twotone.Visibility
import androidx.compose.material.icons.twotone.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloMain
import com.example.linux_logic_app.ui.theme.LiloOrange

/**
 * LoginScreen - Der Anmeldebildschirm der App.
 * Dieses Composable implementiert den Login-Flow, indem es ein Formular mit E-Mail- und Passwortfeldern darstellt.
 * Es validiert die Eingaben in Echtzeit und zeigt entsprechende Fehlermeldungen an.
 * Zudem ermöglicht es das Umschalten der Passwortsichtbarkeit über ein Toggle-Icon.
 * Bei erfolgreicher Authentifizierung wird der Benutzer zum MainScreen navigiert.
 * Wichtige Aspekte:
 * - Validierung der Eingabefelder mit Anzeige von Fehlertexten.
 * - Dynamische Passwortanzeige (sichtbar/unsichtbar) mittels eines Trailing-Icons.
 * - Navigation basierend auf Login-Erfolg und Integration alternativer Login-Optionen.
 * @param navController Steuert die Navigation zwischen den Screens.
 * @param userViewModel Verwaltet den Zustand und die Logik der Benutzeranmeldung.
 */
@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel) {
    // Ideen: https://medium.com/@ramadan123sayed/comprehensive-guide-to-textfields-in-jetpack-compose-f009c4868c54
    val email = userViewModel.email
    val password = userViewModel.password
    val emailErrorMessage = userViewModel.emailErrorMessage
    val passwordErrorMessage = userViewModel.passwordErrorMessage
    /*
    Folgender Ausdruck ist in Kotlin als Destrukturierungsdeklaration bekannt, mit der man die von bestimmten
    Funktionen zurückgegebenen Werte direkt in separate Variablen auspacken können. Kotlin erlaubt
    es, dieses Objekt in zwei Variablen zu zerlegen: eine zum Lesen des Wertes und eine zum Aktualisieren
     */
    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }
    val isFormValid = emailErrorMessage == null && passwordErrorMessage == null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .imePadding()  // Dieser Modifier fügt weiteren Platz hinzu, falls die Tastatur eingeblendet wird.
            .windowInsetsPadding(WindowInsets.navigationBars), // Fügt Platz für die Navigationsleiste hinzu
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Obere Box mit Bild und Begrüßung
        Box(
            modifier = Modifier
                .weight(0.25f)
                .background(LiloMain)
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
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.Main.route)
                        },
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.TwoTone.Login,
                        contentDescription = "DoorBack Icon for Login",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Willkommen zurück!",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                    )
                }
            }
        }

        // Box für das Formular
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .weight(0.75f)
                .padding(16.dp) // Padding hinzufügen für den gesamten Inhalt
            //.clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center), // Vertikale Zentrierung
                horizontalAlignment = Alignment.CenterHorizontally, // Horizontale Zentrierung
            ) {
                Text(
                    text = "Melden Sie sich bei Ihrem Konto an",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

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
                            contentDescription = "Email Icon for Login",
                            tint = LiloMain
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
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = LiloMain,
                    ),
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
                            contentDescription = "Password Icon for Login",
                            tint = LiloMain
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(), // Volle Breite der Box
                    shape = RoundedCornerShape(8.dp), // Abgerundete Ecken
                    singleLine = true, // Verhindert den Zeilenumbruch
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password, // Sicherstellen, dass das Textfeld als Passwortfeld genutzt wird
                        imeAction = ImeAction.Done // Es wird Das Formular abgeschickt
                    ),
                    // Sichtbarkeit des Passworts wird dynamisch geändert
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

                Spacer(modifier = Modifier.height(2.dp))

                // "Passwort vergessen?" Link
                Text(
                    text = "Passwort vergessen?",
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp)
                        .clickable {

                        },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Anmelde-Button
                Button(
                    onClick = {
                        if (userViewModel.login(email.trim(), password.trim())) {
                            Log.i(
                                "User Credentials",
                                "E-Mail: ${email.trim()}; Password: ${password.trim()}"
                            )
                            navController.navigate(Screen.Main.route)
                            userViewModel.clearErrorMessages()
                            //userViewModel.clearAllFields()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    contentPadding = PaddingValues(16.dp),
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = LiloBlue,
                        contentColor = Color.White,
                        disabledContainerColor = Color(0xFFCECECE),
                        disabledContentColor = Color(0xFF7F7F7F)
                    ),
                    enabled = isFormValid
                ) {
                    Text(
                        text = "Anmelden",
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                // Row für alternative Login-Optionen
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp)
                        .clickable {

                        },
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Anmelden mit Google:",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Image(
                        painterResource(id = R.drawable.google_logo),
                        contentDescription = "Login mittels Google Account",
                        modifier = Modifier
                            .size(60.dp)
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

                // Row für Navigation zur Registrierung
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Sie haben noch kein Konto?",
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Registrieren",
                        modifier = Modifier
                            .clickable {
                                Log.i("LoginScreen", "User is performing - Action: \"Register\" -")
                                navController.navigate(Screen.Register.route)
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

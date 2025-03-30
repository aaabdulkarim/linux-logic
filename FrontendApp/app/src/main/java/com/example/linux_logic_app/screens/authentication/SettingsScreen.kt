package com.example.linux_logic_app.screens.authentication

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.twotone.ArrowBackIosNew
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material.icons.twotone.Password
import androidx.compose.material.icons.twotone.PermIdentity
import androidx.compose.material.icons.twotone.Security
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material.icons.twotone.Visibility
import androidx.compose.material.icons.twotone.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.linux_logic_app.R
import com.example.linux_logic_app.components.UserViewModel
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloDanger
import com.example.linux_logic_app.ui.theme.LiloMain
import com.example.linux_logic_app.ui.theme.LiloOrange
import com.example.linux_logic_app.ui.theme.LiloSuccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController, userViewModel: UserViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, // Row vertikal zentrieren
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.linux_logic_pinguin),
                            contentDescription = "Linux Logic Pinguin",
                        )

                        Text(
                            text = "Einstellungen",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            Icons.TwoTone.ArrowBackIosNew,
                            contentDescription = "ArrowBackIosNew for Settings",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            // empty
                        }
                    ) {
                        Icon(
                            Icons.TwoTone.Settings,
                            contentDescription = "Settings Icon for Settings",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarColors(
                    containerColor = LiloMain,
                    scrolledContainerColor = LiloMain,
                    navigationIconContentColor = Color.White, // Hier setze ich die Farbe Weiß für die Icons ?
                    titleContentColor = Color.White,
                    actionIconContentColor = LiloMain
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Verwalten Sie Ihre Einstellungen",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Hier können Sie Ihre Benutzerinformationen einsehen, ändern und Ihr " +
                            "Passwort aktualisieren, um Ihre Daten stets auf dem neuesten Stand zu halten.",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(8.dp))

                AccountSettingsCard(userViewModel)
            }
        }
    }
}

@Composable
fun AccountSettingsCard(userViewModel: UserViewModel) {
    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Rotation of Arrow-Icon"
    )

    // Diese Werte aus dem ViewModel initialisieren
    var editedUsername by remember { mutableStateOf(userViewModel.user?.username.orEmpty()) }
    var editedEmail by remember { mutableStateOf(userViewModel.user?.email.orEmpty()) }
    var editedPassword by remember { mutableStateOf(userViewModel.user?.password.orEmpty()) }

    val usernameErrorMessage = userViewModel.usernameErrorMessage
    val emailErrorMessage = userViewModel.emailErrorMessage
    val passwordErrorMessage = userViewModel.passwordErrorMessage

    val (editingEnabled, setEditingEnabled) = remember { mutableStateOf(false) }
    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }

    val isFormValid = emailErrorMessage == null && usernameErrorMessage == null &&
            passwordErrorMessage == null

    // Flag (Zustand mit 2 möglichen Werten), um den Passwort-Bestätigungsdialog anzuzeigen
    var showPasswordDialog by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = LiloBlue)
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    // Aktualisiert direkt im ViewModel!
                    //settingsViewModel.updateAccountCardExpanded(!expanded)
                    expanded = !expanded
                }
                .padding(16.dp)
                .animateContentSize()
                .imePadding(),  // Dieser Modifier fügt weiteren Platz hinzu, falls die Tastatur eingeblendet wird.
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Security,
                    contentDescription = "Security Icon for Customization",
                    tint = LiloMain
                )
                Text(
                    text = "Account Informationen",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    modifier = Modifier.graphicsLayer(rotationZ = rotationAngle),
                    tint = Color.White
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = editedUsername,
                    onValueChange = {
                        editedUsername = it
                        userViewModel.onUsernameChange(it) // ViewModel aktualisieren
                    },
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
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp), // Abgerundete Ecken
                    singleLine = true, // Verhindert den Zeilenumbruch
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text, // Sicherstellen, dass das Textfeld als Text-Input genutzt wird
                        imeAction = ImeAction.Next // Es wird zum nächsten Input weitergeleitet
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
                    },
                    enabled = editingEnabled
                )

                OutlinedTextField(
                    value = editedEmail,
                    onValueChange = {
                        editedEmail = it
                        userViewModel.onEmailChange(it) // ViewModel aktualisieren
                    },
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
                    },
                    enabled = editingEnabled
                )

                OutlinedTextField(
                    value = editedPassword,
                    onValueChange = {
                        editedPassword = it
                        userViewModel.onPasswordChange(it) // ViewModel aktualisieren
                    },
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
                    modifier = Modifier
                        .fillMaxWidth(), // Volle Breite der Box
                    shape = RoundedCornerShape(8.dp), // Abgerundete Ecken
                    singleLine = true, // Verhindert den Zeilenumbruch
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password, // Sicherstellen, dass das Textfeld als Passwort-Input genutzt wird
                        imeAction = ImeAction.Done // Es wird zum nächsten Input weitergeleitet
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        if (editingEnabled) {
                            val image =
                                if (passwordVisible) Icons.TwoTone.Visibility else Icons.TwoTone.VisibilityOff
                            val description =
                                if (passwordVisible) "Showed password" else "Hidden password"
                            IconButton(
                                onClick = {
                                    setPasswordVisible(!passwordVisible)
                                }
                            ) {
                                Icon(
                                    imageVector = image,
                                    contentDescription = description,
                                    tint = LiloOrange
                                )
                            }
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
                    },
                    enabled = editingEnabled
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (editingEnabled) {
                    Column {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 32.dp, end = 32.dp),
                            colors = ButtonDefaults.buttonColors().copy(
                                containerColor = LiloSuccess,
                                contentColor = Color.White,
                                disabledContainerColor = Color(0xFFCECECE),
                                disabledContentColor = Color(0xFF7F7F7F)
                            ),
                            contentPadding = PaddingValues(16.dp),
                            onClick = {
                                // Daten updaten, wenn der Button geklickt wird
                                if (userViewModel.updateUserData(
                                        newUsername = editedUsername.trim(),
                                        newEmail = editedEmail.trim(),
                                        newPassword = editedPassword.trim()
                                    )
                                ) {
                                    userViewModel.clearErrorMessages()
                                    Log.i(
                                        "New Credentials",
                                        "Username: ${editedUsername.trim()}; E-Mail: " +
                                                "${editedEmail.trim()}; Password: ${editedPassword.trim()}"
                                    )
                                    // Bearbeitung abbrechen
                                    setEditingEnabled(false)
                                }
                            },
                            enabled = isFormValid
                        ) {
                            Text(
                                text = "Speichern",
                                style = MaterialTheme.typography.labelSmall,
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 32.dp, end = 32.dp),
                            colors = ButtonDefaults.buttonColors().copy(
                                containerColor = LiloDanger,
                                contentColor = Color.White,
                            ),
                            contentPadding = PaddingValues(16.dp),
                            onClick = {
                                userViewModel.cancelChanges()
                                setEditingEnabled(false) // Beendet den Bearbeitungsmodus in der UI
                            },
                        ) {
                            Text(
                                text = "Abbrechen",
                                style = MaterialTheme.typography.labelSmall,
                            )
                        }
                    }
                } else {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp, end = 32.dp),
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = LiloOrange,
                            contentColor = Color.White,
                        ),
                        contentPadding = PaddingValues(16.dp),
                        onClick = {
                            // Bearbeitung genehmigt
                            showPasswordDialog = true
                        },
                    ) {
                        Text(
                            text = "Bearbeiten",
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                }
            }
        }
        // Zeige den Passwort-Bestätigungsdialog als eigenständiges Composable
        if (showPasswordDialog) {
            PasswordConfirmDialog(
                userViewModel = userViewModel,
                onConfirm = {
                    // Passwort validiert, mache weiter mit der Logik
                    showPasswordDialog = false
                    setEditingEnabled(true)
                },
                onDismiss = {
                    userViewModel.cancelVerification()
                    showPasswordDialog = false
                }
            )
        }
    }
}

@Composable
fun PasswordConfirmDialog(
    userViewModel: UserViewModel,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val password = userViewModel.verifyPassword
    val passwordErrorMessage = userViewModel.passwordErrorMessage
    var passwordVisible by remember { mutableStateOf(false) }

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background,
        iconContentColor = LiloMain,
        titleContentColor = MaterialTheme.colorScheme.onBackground,
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Verifizieren Sie sich",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .imePadding(),  // Dieser Modifier fügt weiteren Platz hinzu, falls die Tastatur eingeblendet wird.
            ) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { userViewModel.onVerifyPasswordChange(it) },
                    label = { Text("Passwort", style = MaterialTheme.typography.bodyLarge) },
                    placeholder = {
                        Text(
                            "Bitte Passwort verifizieren",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.TwoTone.Password,
                            contentDescription = "Password Icon",
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
                            if (passwordVisible) "Zeige Passwort" else "Verberge Passwort"
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
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
            }
        },
        icon = {
            Icon(
                imageVector = Icons.TwoTone.PermIdentity,
                contentDescription = "PermIdentity Icon"
            )
        },
        confirmButton = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LiloSuccess,
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFFCECECE),
                    disabledContentColor = Color(0xFF7F7F7F)
                ),
                contentPadding = PaddingValues(16.dp),
                onClick = {
                    if (userViewModel.verifyPassword(password)) {
                        onConfirm(password)
                    }
                },
                enabled = passwordErrorMessage == null
            ) {
                Text(
                    text = "Verifizieren",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        },
        dismissButton = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = LiloDanger,
                    contentColor = Color.White,
                ),
                contentPadding = PaddingValues(16.dp),
                onClick = {
                    onDismiss()
                },
            ) {
                Text(
                    text = "Abbrechen",
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    )
}

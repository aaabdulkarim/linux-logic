package com.example.linux_logic_app.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.linux_logic_app.R

@Composable
fun RegisterScreen() {
    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }  // Hinzufügen für das Bestätigungspasswort
    var passwordMatchError by rememberSaveable { mutableStateOf(false) }  // Flag für Fehleranzeige

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.google_logo),
            contentDescription = "Registration-Screen Image",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Willkommen bei Linux Logic", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Erstellen Sie einen Account", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Benutzername Eingabe
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Benutzername") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        // E-Mail Adresse Eingabe
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "E-Mail Adresse") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Passwort Eingabe
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Passwort") },
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Passwort Bestätigung Eingabe
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(text = "Passwort bestätigen") },
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordMatchError // Fehleranzeige aktivieren, wenn Passwörter nicht übereinstimmen
        )

        // Fehleranzeige für Passwörter, die nicht übereinstimmen
        if (passwordMatchError) {
            Text(
                text = "Die Passwörter stimmen nicht überein.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Registrieren Button
        Button(
            onClick = {
                if (password == confirmPassword) {
                    // Registrierung kann fortgesetzt werden, wenn Passwörter übereinstimmen
                    Log.i("Credentials", "Username: $username; E-Mail: $email; Password: $password")
                } else {
                    // Fehler anzeigen, wenn Passwörter nicht übereinstimmen
                    passwordMatchError = true
                }
            },
        ) {
            Text(text = "Registrieren")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Social Login Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painterResource(id = R.drawable.google_logo),
                contentDescription = "Registrierung mittels Google Account",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        // Google login action
                    }
            )

            Image(
                painterResource(id = R.drawable.microsoft_logo),
                contentDescription = "Registrierung mittels Microsoft Account",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        // Microsoft login action
                    }
            )

            Image(
                painterResource(id = R.drawable.x_logo),
                contentDescription = "Registrierung mittels X Account",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        // X login action
                    }
            )
        }

        // Login-Link für bereits registrierte Nutzer
        Row {
            Text(text = "Sie haben bereits ein Konto?  ")
            Text(
                text = "Login",
                modifier = Modifier.clickable {
                    // Navigate to Login screen
                }
            )
        }
    }
}

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

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.google_logo),
            contentDescription = "Login-Screen Image",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Willkommen bei Linux Logic")

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Erstellen Sie einen Account")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {
                Text(text = "Benutzername")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {
                Text(text = "E-Mail Adresse")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {
                Text(text = "Passwort")
            },
            visualTransformation = PasswordVisualTransformation()
        )

        /*OutlinedTextField(
            value = ,
            onValueChange = ,
            label = {
                Text(text = "Passwort bestätigen")
            },
            visualTransformation = PasswordVisualTransformation()
        )*/

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                Log.i("Credentials", "Username: $username; E-Mail: $email; Password: $password")
            }
        ) {
            Text(text = "Registrieren")
        }

        Spacer(modifier = Modifier.height(32.dp))

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

                    }
            )

            Image(
                painterResource(id = R.drawable.microsoft_logo),
                contentDescription = "Registrierung mittels Microsoft Account",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {

                    }
            )

            Image(
                painterResource(id = R.drawable.x_logo),
                contentDescription = "Registrierung mittels X Account",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {

                    }
            )
        }

        Row {
            Text(text = "Sie haben bereits ein Konto?  ")
            Text(text = "Login",
                modifier = Modifier.clickable {

                }
            )
        }
    }
}

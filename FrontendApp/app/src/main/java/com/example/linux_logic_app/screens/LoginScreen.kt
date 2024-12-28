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
fun LoginScreen() {
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

        Text(text = "Willkommen zurück")

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Melden Sie sich bei Ihrem Konto an")

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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                Log.i("Credentials", "E-Mail: $email; Password: $password")
            }
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Passwort vergessen?", modifier = Modifier.clickable {

        })

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painterResource(id = R.drawable.google_logo),
                contentDescription = "Login mittels Google Account",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {

                    }
            )

            Image(
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
            )
        }

        Row {
            Text(text = "Sie haben noch kein Konto?  ")
            Text(text = "Registrieren",
                modifier = Modifier.clickable {

                }
            )
        }
    }
}

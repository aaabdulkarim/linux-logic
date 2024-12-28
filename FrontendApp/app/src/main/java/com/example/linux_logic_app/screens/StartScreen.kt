package com.example.linux_logic_app.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.linux_logic_app.R

@Composable
fun StartScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF569191))
    ) {
        Image(
            alignment = Alignment.TopCenter,
            painter = painterResource(id = R.drawable.linux_logic_start),
            contentDescription = "Abstraktes Aqua Hintergrundbild",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 150.dp)
        ) {
            Text(
                text = "Willkommen bei LINUX LOGIC",
                color = Color.White,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = "Eine Lernplattform zum Erwerb grundlegender und essenzieller Linux-Kenntnisse",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        Log.i("StartScreen", "User is performing - Action: \"Login\" -")
                    }
                ) {
                    Text(text = "Anmelden")
                }

                Button(
                    onClick = {
                        Log.i("StartScreen", "User is performing - Action: \"Register\" -")
                    }
                ) {
                    Text(text = "Registrieren")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.link_logo),
                    contentDescription = "Ein Link Logo für die Verlinkung zu unserer Webseite",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(24.dp) // Reduziert die Größe des Logos
                        .clickable {
                            Log.i("StartScreen", "User clicked link - Action \"Linux Logic Website\" -")
                        }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Zu unserer Webseite",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.clickable {
                        Log.i("StartScreen", "User clicked link - Action \"Linux Logic Website\" -")
                    }
                )
            }
        }
    }
}


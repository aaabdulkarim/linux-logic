package com.example.linux_logic_app.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
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
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                ,
        ) {
            Text(
                text = "Willkommen bei LINUX LOGIC\nEine Lernplattform zum Erwerb grundlegender und essenzieller Linux-Kenntnisse"
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        Log.i("StartScreen", "User is performing - Action: \"Login\" -")
                    }
                ) {
                    Text(
                        text = "Anmelden"
                    )
                }

                Button(
                    onClick = {
                        Log.i("StartScreen", "User is performing - Action \"Register\" -")
                    }
                ) {
                    Text(
                        text = "Registrieren"
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.link_logo),
                    contentDescription = "Ein Link Logo für die Verlinkung zu unserer Webseite",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .clickable {
                            Log.i("StartScreen", "User clicked link - Action \"Linux Logic Website\" -")
                        }
                )
            }

        }
    }
}

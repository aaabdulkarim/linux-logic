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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Willkommen bei LINUX LOGIC",
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = "Eine Lernplattform zum Erwerb grundlegender und essenzieller Linux-Kenntnisse.\nFür näheres besuchen Sie bitte unsere Webseite unter:",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.size(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.link_logo_transparent),
                        contentDescription = "Ein Link Logo für die Verlinkung zu unserer Webseite",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                Log.i(
                                    "StartScreen",
                                    "User clicked link - Action \"Linux Logic Website LINK\" -"
                                )
                            }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "www.linux-logic.com",
                        color = Color.Blue,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .clickable {
                                Log.i(
                                    "StartScreen",
                                    "User clicked link - Action \"Linux Logic Website TEXT\" -"
                                )
                            }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ExtendedFloatingActionButton(
                        modifier = Modifier
                            .padding(horizontal = 4.dp),
                        onClick = {
                            Log.i("StartScreen", "User is performing - Action: \"Login\" -")
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Anmelden Icon",
                                tint = Color.White
                            )
                        },
                        text = {
                            Text(
                                text = "Anmelden",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White
                            )
                        },
                        containerColor = Color(0xFF445a65),
                    )

                    ExtendedFloatingActionButton(
                        modifier = Modifier
                            .padding(horizontal = 4.dp),
                        onClick = {
                            Log.i("StartScreen", "User is performing - Action: \"Register\" -")
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Register Icon",
                                tint = Color.White
                            )
                        },
                        text = {
                            Text(
                                text = "Registrieren",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White
                            )
                        },
                        containerColor = Color(0xFFFF8c00),
                    )
                }
            }
        }
    }
}


package com.example.linux_logic_app.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.icons.automirrored.twotone.Login
import androidx.compose.material.icons.twotone.PersonAddAlt
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.linux_logic_app.R
import com.example.linux_logic_app.navigation.Screen
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloMain
import com.example.linux_logic_app.ui.theme.LiloOrange

/**
 * StartScreen stellt den Startbildschirm der App dar.
 * Relevante, komplexe Aspekte:
 * - Kombination von Bildern, Text und Buttons, um eine ansprechende Startseite zu gestalten.
 * - Verwendung von LocalContext und Intent, um externe Webseiten zu öffnen.
 * - Navigation zu Login- und Registrierungs-Screens mittels NavController.
 * @param navController Steuert die Navigation zwischen den Screens.
 */
@Composable
fun StartScreen(navController: NavController) {
    val context = LocalContext.current
    val liloWebUrl = "https://www.linux-logic.com"
    // Intent zum Öffnen der Linux Logic Webseite
    val liloIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(liloWebUrl)) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LiloMain)
    ) {
        // Hintergrundbild über den gesamten Bildschirm
        Image(
            alignment = Alignment.TopCenter,
            painter = painterResource(id = R.drawable.linux_logic_start),
            contentDescription = "Abstraktes Aqua Hintergrundbild",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )

        // Container für die unteren Inhalte (Text, Link und Buttons)
        Box(
            modifier = Modifier
                .fillMaxHeight(0.45f)
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                // verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "Willkommen bei LINUX LOGIC",
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = "Eine Lernplattform zum Erwerb grundlegender und essenzieller Linux-Kenntnisse. Für näheres besuchen Sie bitte unsere Webseite unter:",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Start),
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.size(8.dp))

                // Zeile mit einem klickbaren Link, der die Webseite öffnet
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            context.startActivity(liloIntent)
                            Log.i(
                                "StartScreen",
                                "User clicked Link - Action \"open Linux Logic Website\" -"
                            )
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.link_logo_transparent),
                        contentDescription = "Link Logo zur Webseite",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(32.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "www.linux-logic.com",
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Buttons für Navigation zu Login und Registrierung
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ExtendedFloatingActionButton(
                        modifier = Modifier.weight(0.5f),
                        onClick = {
                            navController.navigate(Screen.Login.route)
                            Log.i("StartScreen", "User is performing - Action: \"Login\" -")
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.TwoTone.Login,
                                contentDescription = "Login Icon",
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
                        containerColor = LiloBlue,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    ExtendedFloatingActionButton(
                        modifier = Modifier.weight(0.5f),
                        onClick = {
                            navController.navigate(Screen.Register.route)
                            Log.i("StartScreen", "User is performing - Action: \"Register\" -")
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.TwoTone.PersonAddAlt,
                                contentDescription = "Registrieren Icon",
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
                        containerColor = LiloOrange
                    )
                }
            }
        }
    }
}

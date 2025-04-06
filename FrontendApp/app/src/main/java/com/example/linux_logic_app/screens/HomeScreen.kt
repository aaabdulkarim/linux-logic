package com.example.linux_logic_app.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.History
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.linux_logic_app.components.viewmodels.UserViewModel
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloDanger
import com.example.linux_logic_app.ui.theme.LiloOrange
import com.example.linux_logic_app.ui.theme.LiloSuccess

/**
 * HomeScreen - Zeigt den Fortschritt des Nutzers, Mitteilungen und ermöglicht nahtloses Weiterspielen.
 * Dieses Composable stellt einen Bildschirminhalt dar, der einen Button zum Weiterspielen,
 * einen Fortschrittstitel und eine Terminalanzeige umfasst.
 * Wichtige Aspekte:
 * - Verwendung eines scrollbaren Column-Layouts, um auch auf kleineren Bildschirmen alle Inhalte anzuzeigen.
 * - Der "Nahtlos weiterspielen" Button nutzt Spacer mit weight, um den Inhalt ausgewogen zu zentrieren.
 * - Integration des Terminal-Composables zur Darstellung von Live-Terminal-Inhalten.
 * @param userViewModel Verwaltet den Zustand und die Logik des aktuell angemeldeten Nutzers.
 */
@Composable
fun HomeScreen(userViewModel: UserViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Zu Ihrem letzten Stand:",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(32.dp))
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LiloBlue,
                    contentColor = Color.White,
                ),
                onClick = {
                    // Weiterpspielen redirecten und umsetzen
                },
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.TwoTone.History,
                        contentDescription = "History Icon for Home",
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        tint = LiloOrange
                    )

                    Spacer(modifier = Modifier.weight(1f)) // Lässt den Text mittig ausrichten

                    Text(
                        text = "Nahtlos weiterspielen",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.weight(1f)) // Platzhalter, um den Button-Inhalt ausgewogen zu halten
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Ihr Fortschritt:",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        UserProgressBar(progress = 0.98F, userViewModel = userViewModel)

        /*Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Terminal("ws://10.0.107.7:8000/ws", preview = false, userViewModel = userViewModel)
        }*/
    }
}

@Composable
fun UserProgressBar(progress: Float, userViewModel: UserViewModel) {
    /*
    val scenarioAmount = userViewModel.levelViewModel?.getScenarioAmount()
    Das muss das Backend übernehmen, weil das LevelViewModel auf dem Progress basiert und dieses die Anzahl
    an Szenarien benötigt und der Progress über das Backend gemanaged wird.
    */

    // Farben für den fertigen und den noch ausstehenden Teil
    val finishedColor = LiloSuccess
    val remainingColor = LiloDanger

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(remainingColor) // Hintergrund = verbleibender Teil (rot)
        ) {
            // Fortschrittsbereich in grün
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress.coerceIn(0f, 1f))
                    .background(finishedColor)
            )

            // Prozentanzeige zentriert
            Text(
                text = "${(progress * 100).toInt()}%",
                modifier = Modifier
                    .align(Alignment.Center),
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Spielername: ${userViewModel.user?.username}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Szenarien  gelöst: 6/7",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

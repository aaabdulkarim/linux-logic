package com.example.linux_logic_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.linux_logic_app.R
import com.example.linux_logic_app.ui.theme.*

@Composable
fun StartScreen() {

    Image(
        painter = painterResource(id = R.drawable.linux_logic_aqua),
        contentDescription = "Abstraktes Aqua Hintergrundbild",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    )

    // Hauptfarben und Ressourcen festlegen
    val backgroundColor = Color(0xFF569191) // Hauptfarbe (angepasst auf deine App-Farbe)
    val penguinModifier = Modifier.size(100.dp) // Größe des Pinguin-Images

    Box(
        modifier = Modifier
            .fillMaxSize() // Füllt die gesamte Bildschirmfläche aus
    ) {
        // Abstraktes Aqua-Bild im Hintergrund


        // Rotiertes Rechteck
        Box(
            modifier = Modifier
                .fillMaxWidth() // Passt sich an die gesamte Bildschirmgröße an
                .graphicsLayer {
                    rotationZ = -10f // Drehung gegen den Uhrzeigersinn in Grad
                }
                .background(backgroundColor) // Hintergrundfarbe
        )

        // Inhalt überlagern (Logo, Text, Pinguin)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Linux Logic Logo
            Image(
                painter = painterResource(id = R.drawable.linux_logic_main_transparent),
                contentDescription = "Linux Logic Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Untertitel
            Text(
                text = "Linux lernen leicht gemacht",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.weight(1f))

            // Pinguin Bild
            Image(
                painter = painterResource(id = R.drawable.linux_logic_pinguin),
                contentDescription = "Pinguin Bild",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
package com.example.linux_logic_app.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.Help
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.twotone.ArrowBackIosNew
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.NotificationsActive
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material.icons.twotone.SportsEsports
import androidx.compose.material.icons.twotone.Terminal
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.linux_logic_app.R
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloMain
import com.example.linux_logic_app.ui.theme.LiloOrange

data class HelpMessage(
    val cardTitle: String,
    val cardDesc: String,
    val cardIcon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(navController: NavController) {
    val context = LocalContext.current
    val liloWebUrl = "https://www.linux-logic.com"
    val liloIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(liloWebUrl)) }

    val helpMessages = listOf(
        HelpMessage(
            cardTitle = "Home",
            cardDesc = "Im \"Home\"-Bildschirm finden Sie Ihren Fortschritt in der Form einer Fortschrittsanzeige, " +
                    "welche zeigt, wie viele Szenarien Sie in Linux Logic erledigt haben. Zudem gibt es die Option, " +
                    "mit nur einem Klick dort weiterzuspielen, wo man aufgeört hat.",
            cardIcon = Icons.TwoTone.Home
        ),
        HelpMessage(
            cardTitle = "Terminal",
            cardDesc = "Im \"Terminal\"-Reiter können Sie Ihr ganz individuelles Terminal kreieren. Anhand farblicher Modifikationsoptionen" +
                    "für den Terminal-Kopf, den Text und weitere Komponenten können Sie ihr Terminal personalisieren und während" +
                    "dem Spielen bestaunen. Sie können das Standard-Terminal verwenden oder mittels vordefinierter Farben, sowie" +
                    "einem Farbwähler die gewünschten Farben zusammenstellen.",
            cardIcon = Icons.TwoTone.Terminal
        ),
        HelpMessage(
            cardTitle = "Spielen",
            cardDesc = "Wenn Sie in Linux Logic spielen wollen, wählen Sie ganz einfach im \"Spielen\"-Reiter ein Szenario aus, und beginnen" +
                    "Sie Schritt für Schritt die Sublevel sequenziell zu spielen. Am Ende gibt es natürlich eine Überraschungsnachricht!" +
                    "Folgendermaßen gehen Sie vor in den Sublevels:\n\nSchritt 1: Beschreibung in der Card lesen\nSchritt 2: Mit dem Terminal " +
                    "interagieren\nSchritt 3: Ihre Ergebnisse überprüfen lassen\nSchritt optional: Wenn Sie Probleme bei Ihrem Fortschritt " +
                    "sehen, versuchen Sie es mit einem Hinweis.",
            cardIcon = Icons.TwoTone.SportsEsports
        ),
        HelpMessage(
            cardTitle = "Einstellungen",
            cardDesc = "In den Einstellungen haben Sie die Möglichkeit Ihre Daten nach der gültigen Eingabe Ihres aktuellen Passwortes" +
                    "zu ändern. Zu den Daten zählt Ihr Benutzername, Ihre E-Mail Adresse und Ihr Passwort.",
            cardIcon = Icons.TwoTone.Settings
        ),
        HelpMessage(
            cardTitle = "Mitteilungen",
            cardDesc = "In den Mitteilungen finden Sie einige Nachrichten und kurze Berichte über die Meilensteine die erreicht wurden" +
                    "oder sonstige relevante Informationen zur mobilen Applikation von Linux Logic. Wenn Updates oder Patches eingeführt" +
                    "werden, werden Sie diese Benachrichtigung dort erhalten.",
            cardIcon = Icons.TwoTone.NotificationsActive
        )
    )

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
                            text = "Hilfe",
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
                            contentDescription = "ArrowBackIosNew Icon for Feedback",
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
                            Icons.AutoMirrored.TwoTone.Help,
                            contentDescription = "Help Icon for Help",
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
            ) {
                Text(
                    text = "Benötigen Sie Hilfe bei der Bedienung?",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(helpMessages) { _, message ->
                        HelpCard(
                            cardTitle = message.cardTitle,
                            cardDesc = message.cardDesc,
                            cardIcon = message.cardIcon
                        )
                    }

                    item {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = LiloBlue),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    context.startActivity(liloIntent)
                                    Log.i(
                                        "StartScreen",
                                        "User clicked Link - Action \"open Linux Logic Website\" -"
                                    )
                                },
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
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
                                    color = Color.White,
                                    textDecoration = TextDecoration.Underline
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HelpCard(cardTitle: String, cardDesc: String, cardIcon: ImageVector) {
    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Rotation of Arrow-Icon"
    )

    Card(
        colors = CardDefaults.cardColors(
            containerColor = LiloBlue
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    expanded = !expanded
                }
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = cardIcon,
                    contentDescription = "Specific Help Icon for Help",
                    tint = LiloOrange
                )

                Text(
                    text = cardTitle,
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

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    HorizontalDivider(thickness = 1.dp, color = LiloMain)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = cardDesc,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        textAlign = TextAlign.Justify,
                    )
                }
            }
        }
    }
}
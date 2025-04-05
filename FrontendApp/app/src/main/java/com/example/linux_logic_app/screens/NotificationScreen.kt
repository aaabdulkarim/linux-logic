package com.example.linux_logic_app.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.twotone.ArrowBackIosNew
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material.icons.twotone.DoDisturbOn
import androidx.compose.material.icons.twotone.NotificationsActive
import androidx.compose.material.icons.twotone.Report
import androidx.compose.material.icons.twotone.ReportProblem
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.linux_logic_app.R
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloDanger
import com.example.linux_logic_app.ui.theme.LiloMain
import com.example.linux_logic_app.ui.theme.LiloOrange
import com.example.linux_logic_app.ui.theme.LiloPending
import com.example.linux_logic_app.ui.theme.LiloSuccess

data class NotifMessage(
    val cardTitle: String,
    val cardDesc: String,
    val cardDate: String,
    val cardIcon: ImageVector,
    val cardIconColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController) {
    val notifications = listOf(
        NotifMessage(
            cardTitle = "Meilenstein 1",
            cardDesc = "Projektstart:\nFür den Projektstart befand sich das Team Linux Logic auf Projektwoche am Turner See und konnte" +
                    "dort die ersten Schritte zum Projektsstart einleiten. Im Fokus stand dabei die Einreichung des Projektantrags.",
            cardDate = "09. September 2024",
            cardIcon = Icons.TwoTone.CheckCircle,
            cardIconColor = LiloSuccess
        ),
        NotifMessage(
            cardTitle = "Meilenstein 2",
            cardDesc = "Machbarkeitsstudie abgeschlossen:\nSchon sehr früh sollte nach dem Projektstart sollten wir uns einen Gefallen tun," +
                    "indem wir eine Machbarkeitsstudie vorschreiben sollten, um es in der Entwicklungsphase einfacher zu haben." +
                    "Diese beinhaltet einen Vergleich infrage kommender Technologien.",
            cardDate = "14. Oktober 2024",
            cardIcon = Icons.TwoTone.CheckCircle,
            cardIconColor = LiloSuccess
        ),
        NotifMessage(
            cardTitle = "Meilenstein 3",
            cardDesc = "Prototyp fertiggestellt:\nFür den ersten tag der offenen Tür im gesamten TGM nach der COVID-19 Krise sollte ein Protoytp" +
                    "bereit sein und unsere kommende Idee visuell unterstützen. Dazu hat der UI/UX-Designer sowohl für die mobile App, als auch für" +
                    "das Web einen Prototypen in Figma erstellt.",
            cardDate = "08. November 2024",
            cardIcon = Icons.TwoTone.CheckCircle,
            cardIconColor = LiloSuccess
        ),
        NotifMessage(
            cardTitle = "Probleme: Technologieauswahl",
            cardDesc = "Der Fokus lag schon immer auf das Android-OS, jedoch wollte der App-Entwickler vom Linux Logic Team eine Herausforderung und" +
                    "einen größeren Lerneffekt erzielen, weshalb die ganze Zeit bis zu den Weihnachtsferien über das Cross-Platform-Framework Flutter" +
                    "recherchiert wurde. Flutter bot sogar ein vorgefertigtes Terminal an (xterm.dart), jedoch kam es dazu, dass Flutter ihre API überarbeitet" +
                    "hat und es zu Inkonsistenzen in der Technologie kam.\nFür die Techniker: Die Klasse RawKeyEvent war nun deprecated und wurde durch KeyEvent" +
                    "ersetzt, wodurch man ein Plugin (pluto-grid-plus) installieren müsste um die Applikation zu kompilieren.",
            cardDate = "23. Dezember 2024",
            cardIcon = Icons.TwoTone.Report,
            cardIconColor = LiloDanger
        ),
        NotifMessage(
            cardTitle = "Meilenstein 4",
            cardDesc = "Beta-Version entwickelt:\nEs kam zur Entscheidung für die mobile Anwendung nativ zu entwicklen mit der Wahl von Google's Jetpack Compose." +
                    "Bis zum zweiten Tag der offenen Tür war es nun so weit, wodurch eine bestehende Beta-Version gezeigt werden konnte und was wir alles geleistet haben" +
                    "zum ende des 9.Semesters.",
            cardDate = "27. Jänner 2025",
            cardIcon = Icons.TwoTone.CheckCircle,
            cardIconColor = LiloSuccess
        ),
        NotifMessage(
            cardTitle = "Status: Inkonsistenzen",
            cardDesc = "Im Team gibt es Inkonsistenzen im Fortschritt, wodurch die Entwicklung der mobilen App verzögert wird. Um Funktionen nicht unnötig" +
                    "zu kodieren, muss ein Kompromiss (noch besser ein Konsens) gefunden werden.",
            cardDate = "09. Februar 2025",
            cardIcon = Icons.TwoTone.ReportProblem,
            cardIconColor = LiloOrange
        ),
        NotifMessage(
            cardTitle = "Meilenstein 5",
            cardDesc = "Testphase abgeschlossen:\nIn der mobilen Linux Logic Applikation wurde stets Debugging und Big-Fixing betrieben, sodass während der Entwicklungsphase " +
                    "immer getestet wurde, ob es Fehler in der Funktionalität gibt. Für UI/UX-bezogene Tests ist der UI/UX-Designer des Teams zuständig.",
            cardDate = "21. Februar 2025",
            cardIcon = Icons.TwoTone.CheckCircle,
            cardIconColor = LiloSuccess
        ),
        NotifMessage(
            cardTitle = "Meilenstein 6",
            cardDesc = "Diplomarbeit vollständig geschrieben:\nDie letzte Abgabe nach der Literaturrechere und Methodenteil, sowie die State Of The Art-Abgabe, war " +
                    "die Implementierung. Diese war mit einer harten Deadline am 14. März abzugeben.",
            cardDate = "01. April 2025",
            cardIcon = Icons.TwoTone.CheckCircle,
            cardIconColor = LiloSuccess
        ),
        NotifMessage(
            cardTitle = "Meilenstein 7",
            cardDesc = "Projektabschluss:\nBis zum 08. April ist die harte Abschlussabgabe der Diplomarbeit. Bis zu diesem Datum muss der gesamte wissenschaftliche Teil " +
                    "geschrieben sein und fusioniert abgegeben werden (alle individuellen Teilbereiche zusammen).",
            cardDate = "08. April 2025",
            cardIcon = Icons.TwoTone.DoDisturbOn,
            cardIconColor = LiloPending
        ),
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
                            text = "Mitteilungen",
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
                            contentDescription = "ArrowBackIosNew Icon for Notifications",
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
                            Icons.TwoTone.NotificationsActive,
                            contentDescription = "NotificationsActive Icon for Notifications",
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
                    itemsIndexed(notifications.reversed()) { _, notification ->
                        NotificationCard(
                            cardTitle = notification.cardTitle,
                            cardDesc = notification.cardDesc,
                            cardDate = notification.cardDate,
                            cardIcon = notification.cardIcon,
                            cardIconColor = notification.cardIconColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NotificationCard(
    cardTitle: String,
    cardDesc: String,
    cardDate: String,
    cardIcon: ImageVector,
    cardIconColor: Color
) {
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
                    contentDescription = "Icon for Notifications",
                    tint = cardIconColor,
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = cardTitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = cardDate,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White
                    )
                }

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

package com.example.linux_logic_app.screens.gamification

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.twotone.ArrowBackIosNew
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material.icons.twotone.EmojiEvents
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.Lightbulb
import androidx.compose.material.icons.twotone.SportsEsports
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.linux_logic_app.R
import com.example.linux_logic_app.components.terminal.Terminal
import com.example.linux_logic_app.components.viewmodels.UserViewModel
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloMain
import com.example.linux_logic_app.ui.theme.LiloOrange
import com.example.linux_logic_app.ui.theme.LiloSuccess
import kotlinx.coroutines.launch

/**
 * LevelScreen - Zeigt den aktuellen Lernfortschritt (Sublevel) eines ausgewählten Scenarios an.
 * Dieses Composable zeigt in einer Scaffold-Struktur:
 * - Eine TopAppBar, die den aktuellen Sublevel-Namen anzeigt (mit animiertem Übergang).
 * - Den LevelCard, der die Details des aktuellen Sublevels (Name und Beschreibung) darstellt.
 * - Ein Terminal, das als interaktives Element den Fortschritt visualisiert.
 * - Zwei ExtendedFloatingActionButtons für "Hinweise" und "Prüfen".
 * - Beim Drücken von "Prüfen" wird der nächste Sublevel geladen; falls es keinen mehr gibt, erscheint ein CompletionDialog.
 * Wichtige Aspekte:
 * - Verwendung von animateContentSize, AnimatedContent und animateFloatAsState für fließende Animationen.
 * - Nutzung eines SnackbarHost zur Anzeige von Erfolgsmeldungen.
 * @param navController Steuert die Navigation zwischen den Screens.
 * @param userViewModel Verwaltet den Zustand des aktuell angemeldeten Nutzers.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelScreen(navController: NavController, userViewModel: UserViewModel) {
    val levelViewModel = userViewModel.levelViewModel!!  // Ist garantiert nicht null
    val sublevelName = levelViewModel.getCurrentLevelName()
    val sublevelDesc = levelViewModel.getCurrentSublevelDescription()

    var showCompletionDialog by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }  // Snackbar State
    val coroutineScope = rememberCoroutineScope()  // Coroutine für Snackbar

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                CustomSnackbar(data)
            }
        },  // Snackbar im Scaffold einfügen
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
                        AnimatedContent(
                            targetState = sublevelName,
                            label = "Sublevel Name Transition"
                        ) { sublevelName ->
                            Text(
                                text = sublevelName,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
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
                            Icons.TwoTone.SportsEsports,
                            contentDescription = "SportsEsports Icon for Level",
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
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(8.dp))

                // LevelCard zeigt die aktuelle Sublevel-Information (Name und Beschreibung)
                LevelCard(sublevelName, sublevelDesc)

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Terminal(
                        "ws://10.0.107.7:8000/ws",
                        preview = false,
                        userViewModel = userViewModel
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ExtendedFloatingActionButton(
                        modifier = Modifier.weight(0.5f),
                        onClick = {
                            // ToDo: Hinweise anzeigen
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.TwoTone.Lightbulb,
                                contentDescription = "Login Icon for Start",
                                tint = Color.White
                            )
                        },
                        text = {
                            Text(
                                text = "Hinweise",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White
                            )
                        },
                        containerColor = LiloOrange,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    ExtendedFloatingActionButton(
                        modifier = Modifier.weight(0.5f),
                        onClick = {
                            // Checken ob die Eingabe valide ist.
                            if (!levelViewModel.nextSublevel()) {
                                showCompletionDialog = true
                            } else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "$sublevelName geschafft!",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.TwoTone.CheckCircle,
                                contentDescription = "PersonAdd Icon for Start",
                                tint = Color.White
                            )
                        },
                        text = {
                            Text(
                                text = "Prüfen",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White
                            )
                        },
                        containerColor = LiloMain
                    )
                }
            }
        }
    }
    if (showCompletionDialog) {
        CompletionDialog {
            showCompletionDialog = false
            navController.navigateUp()  // Navigation erst nach Bestätigung
        }
    }
}

/**
 * LevelCard - Zeigt die Details des aktuellen Sublevels an.
 * Dieses Composable stellt eine Card dar, die den Namen und die Beschreibung des aktuellen Sublevels zeigt.
 * Über eine Klickaktion kann der Card-Content erweitert bzw. reduziert werden, wobei ein Pfeilsymbol
 * animiert rotiert, um den Zustand (erweitert/kompakt) anzuzeigen.
 * Wichtige Aspekte:
 * - Animierte Rotation des Pfeilsymbols mittels animateFloatAsState.
 * - AnimateContentSize sorgt für einen fließenden Übergang bei der Größenänderung der Card.
 * @param sublevelName Name des aktuellen Sublevels.
 * @param sublevelDesc Beschreibung des aktuellen Sublevels.
 */
@Composable
fun LevelCard(sublevelName: String, sublevelDesc: String) {
    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Rotation of Arrow-Icon"
    )

    Card(
        colors = CardDefaults.cardColors(
            containerColor = LiloBlue
        )
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    // Aktualisiert direkt im ViewModel!
                    //settingsViewModel.updateAccountCardExpanded(!expanded)
                    expanded = !expanded
                }
                .padding(16.dp)
                .animateContentSize()
                .imePadding(),  // Dieser Modifier fügt weiteren Platz hinzu, falls die Tastatur eingeblendet wird.
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Info,
                    contentDescription = "SportsEsports Icon for Level",
                    tint = LiloOrange
                )

                Text(
                    text = "$sublevelName: Beschreibung",
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

            if (expanded) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = sublevelDesc,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        }
    }
}

/**
 * CompletionDialog - Zeigt einen Dialog an, wenn alle Sublevels abgeschlossen sind.
 * Dieses Composable zeigt einen AlertDialog, der dem Nutzer gratuliert und eine Bestätigung bietet.
 * Nach Bestätigung wird der Dialog geschlossen und die Navigation erfolgt.
 * Wichtige Aspekte:
 * - Der Dialog verwendet eine vollständige Breite mit Padding und ist in der Farbe an das Thema angepasst.
 * @param onDismiss Callback, der aufgerufen wird, wenn der Dialog bestätigt wird.
 */
@Composable
fun CompletionDialog(onDismiss: () -> Unit) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background,
        titleContentColor = MaterialTheme.colorScheme.onBackground,
        textContentColor = MaterialTheme.colorScheme.onBackground,
        onDismissRequest = {},
        confirmButton = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp),
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = LiloSuccess,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Juhu!",
                    color = Color.White
                )
            }
        },
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.TwoTone.EmojiEvents,
                    contentDescription = "Completion Icon",
                    tint = LiloMain,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Glückwunsch!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        },
        text = {
            Text(
                text = "Sie haben alle Sublevels erfolgreich abgeschlossen, super gemacht! \uD83C\uDF89",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        modifier = Modifier.padding(16.dp)
    )
}

/**
 * CustomSnackbar - Ein benutzerdefinierter Snackbar-Stil.
 * Dieses Composable rendert eine Snackbar innerhalb einer Card, die
 * eine Erfolgsmeldung anzeigt. Es wird als Host im Scaffold verwendet.
 * Wichtige Aspekte:
 * - Nutzung eines Row-Layouts zur Anzeige eines Icons und des Nachrichten-Textes.
 * @param data Enthält die Snackbar-Daten, wie die Nachricht.
 */
@Composable
fun CustomSnackbar(data: SnackbarData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.TwoTone.CheckCircle,
                    contentDescription = "Success Icon for Level",
                    tint = LiloSuccess,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = data.visuals.message,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

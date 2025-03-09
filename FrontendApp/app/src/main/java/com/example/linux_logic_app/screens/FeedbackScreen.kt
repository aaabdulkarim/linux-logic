package com.example.linux_logic_app.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBackIosNew
import androidx.compose.material.icons.twotone.Feedback
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.linux_logic_app.R
import com.example.linux_logic_app.ui.theme.LiloMain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(navController: NavController) {
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
                            text = "Feedback",
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
                            Icons.TwoTone.Feedback,
                            contentDescription = "Feedback Icon for Feedback",
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

        }
    }
}

/*
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailDefaults
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.linux_logic_app.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsScreen() {
    val items = listOf("Home", "Settings", "Logout")
    var selectedItem by remember { mutableIntStateOf(0) }

    var showNavRail by remember { mutableStateOf(true) }

    var showAccountDialog by remember { mutableStateOf(false) }

    var showInfoDialog by remember { mutableStateOf(false) }

    var hideCards by remember { mutableStateOf(false) }

    var showAddCardDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var cards by remember { mutableStateOf(listOf<Pair<String, String>>()) }

    val rotationAngle by animateFloatAsState(
        targetValue = if (showNavRail) 0f else 180f,
        label = "Rotation of Arrow-Icon"
    )
    val userTags by remember {
        mutableStateOf(
            listOf(
                "Homework",
                "Labor",
                "Theory",
                "DoTo"
            )
        )
    }

    val context = LocalContext.current
    val liloWebUrl = "https://www.linux-logic.com"
    val liloIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(liloWebUrl)) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var currentTagIndex by remember { mutableStateOf(0) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = Color(0xFF1E88E5), // Blaue Snackbar
                    contentColor = Color.White, // Weißer Text
                    actionColor = Color.Yellow, // Gelber Button
                    shape = RoundedCornerShape(12.dp),
                )
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.linux_logic_pinguin),
                            contentDescription = "Jetpack Compose Logo",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(70.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            showNavRail = !showNavRail
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                            contentDescription = "Arrow Icon",
                            tint = Color.White,
                            modifier = Modifier
                                .graphicsLayer(rotationZ = rotationAngle),
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        showAccountDialog = true // Öffnet den Dialog
                    }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Open Account Dialog",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF092A39),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = "Add",
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Icon"
                    )
                },
                onClick = {
                    showAddCardDialog = true
                },
                shape = CircleShape,
                containerColor = Color(0xFF3DDC84),
                contentColor = Color.White
            )
        }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            /**
             * NavigationRail:
             * - Ermöglicht die Navigation zwischen Hauptzielen der App.
             * - Sollte zwischen 3-7 Ziele enthalten.
             * - Kann optional ein FloatingActionButton oder Logo-Header enthalten.
             * - Verwendet NavigationRailItems für jedes Ziel.
             *
             * Parameter:
             * - `modifier`: Ermöglicht Anpassungen am Layout.
             * - `containerColor`: Hintergrundfarbe der NavigationRail (Standard: `NavigationRailDefaults.ContainerColor`).
             * - `contentColor`: Farbe für Icons und Labels (wird automatisch aus `containerColor` abgeleitet).
             * - `header`: Optionaler Bereich für ein Logo oder Button.
             * - `windowInsets`: Bestimmt die Abstände innerhalb der NavigationRail.
             * - `content`: Enthält die NavigationRailItems.
             */
            Box(
                modifier = Modifier
                    .animateContentSize() // Fügt eine weiche Animation hinzu
            ) {
                if (showNavRail) {
                    NavigationRail(
                        modifier = Modifier.fillMaxHeight(),
                        containerColor = Color(0xFF092A39),
                        contentColor = Color.White,
                        windowInsets = NavigationRailDefaults.windowInsets,
                        header = {
                            SmallFloatingActionButton(
                                onClick = {
                                    showInfoDialog = true
                                },
                                shape = CircleShape,
                                containerColor = Color(0xFF3DDC84),
                                contentColor = Color.White
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Info Icon"
                                )
                            }
                        }
                    ) {
                        items.forEachIndexed { index, item ->
                            /**
                             * NavigationRailItem:
                             * - Repräsentiert eine einzelne Zielseite.
                             * - Kann ein Icon und eine optionale Beschriftung enthalten.
                             *
                             * Parameter:
                             * - `selected`: Gibt an, ob das Item ausgewählt ist.
                             * - `onClick`: Wird aufgerufen, wenn das Item angeklickt wird.
                             * - `icon`: Definiert das Symbol des Items.
                             * - `label`: Optionaler Text unter dem Icon.
                             * - `alwaysShowLabel`: Falls `false`, wird das Label nur bei Auswahl angezeigt.
                             * - `enabled`: Falls `false`, wird das Item deaktiviert.
                             * - `colors`: Legt Farben für verschiedene Zustände des Items fest.
                             * - `interactionSource`: Ermöglicht das Reagieren auf Benutzerinteraktionen.
                             */
                            NavigationRailItem(
                                selected = index == selectedItem,
                                onClick = {
                                    selectedItem = index
                                },
                                icon = {
                                    Icon(
                                        imageVector = when (item) {
                                            "Home" -> Icons.Default.Home
                                            "Settings" -> Icons.Default.Settings
                                            "Logout" -> Icons.AutoMirrored.Default.Logout
                                            else -> Icons.Default.Info
                                        },
                                        contentDescription = item
                                    )
                                },
                                label = {
                                    Text(
                                        text = item,
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                },
                                alwaysShowLabel = false,
                                enabled = true,
                                colors = NavigationRailItemDefaults.colors(
                                    indicatorColor = Color(0xFF4285F4),
                                    selectedIconColor = Color.White,
                                    selectedTextColor = Color.White,
                                    unselectedIconColor = Color(0xFF3DDC84),
                                    unselectedTextColor = Color.White,
                                    disabledTextColor = Color.Red,
                                    disabledIconColor = Color.Red
                                ),
                                //interactionSource = remember { MutableInteractionSource() },
                                //modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /**
                 * Assist-Chip: Unterstützt den Nutzer während einer Aufgabe, indem er hilfreiche
                 * Hinweise oder Aktionen anbietet. Er erscheint oft vorübergehend als Reaktion auf
                 * eine Benutzereingabe.
                 *
                 * Filter-Chip: Ermöglicht das Filtern von Inhalten, indem er eine Auswahl bietet,
                 * die ein- oder ausgeschaltet werden kann. Häufig zeigt er eine Markierung an, wenn
                 * er aktiviert ist.
                 *
                 * Input-Chip: Stellt eine Benutzereingabe dar, beispielsweise eine Auswahl in einem
                 * Menü. Er kann ein Symbol und Text enthalten und verfügt über ein „X“, mit dem er
                 * entfernt werden kann.
                 *
                 * Suggestion-Chip: Macht dem Nutzer Vorschläge basierend auf bisherigen Eingaben
                 * oder Aktivitäten. Er wird oft unter einem Eingabefeld angezeigt, um mögliche
                 * Optionen oder Antworten zu präsentieren.
                 */

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Erste Zeile mit Assist und Filter Chip
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Assist Chip (führt eine Aktion aus, aber kein Toggle)
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                        ) {
                            /**
                             * AssistChip
                             * - Wird verwendet, um Benutzer bei einer Aufgabe zu unterstützen.
                             * - Ist klickbar, aber nicht auswählbar.
                             * - Hat eine Leading Icon zur besseren Visualisierung.
                             * - Beispiel: Führt zu einer Webseite mit weiteren Informationen.
                             *
                             * Attribute:
                             * - onClick: Lambda-Funktion, die bei Klick ausgeführt wird.
                             * - label: Beschriftung des Chips.
                             * - leadingIcon: Symbol vor dem Label.
                             * - modifier: Anpassung des Layouts.
                             * - enabled: Aktiviert oder deaktiviert den Chip.
                             * - shape: Form des Chips.
                             * - colors: Ändert die Farben des Chips.
                             * - elevation: Bestimmt die Höhe/Schatten.
                             * - border: Fügt einen Rand hinzu.
                             * - interactionSource: Steuert Interaktionen.
                             */
                            AssistChip(
                                onClick = {
                                    context.startActivity(liloIntent)
                                },
                                label = {
                                    Text(
                                        text = "More",
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Filled.Web,
                                        contentDescription = "Web Icon",
                                        Modifier.size(AssistChipDefaults.IconSize)
                                    )
                                },
                                modifier = Modifier.fillMaxSize(),
                                enabled = true, // Deaktivieren möglich
                                shape = RoundedCornerShape(16.dp), // Runde Ecken
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = Color(0xFF3DDC84),
                                    labelColor = Color.White,
                                    leadingIconContentColor = Color.White,
                                    trailingIconContentColor = Color.White
                                ),
                                elevation = AssistChipDefaults.assistChipElevation(
                                    elevation = 8.dp
                                ),
                                border = BorderStroke(1.dp, Color.DarkGray),
                                interactionSource = remember { MutableInteractionSource() } // Interaktionseffekt
                            )
                        }

                        // Filter Chip (kann ein- oder ausgeschaltet werden)
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                        ) {
                            /**
                             * FilterChip
                             * - Ermöglicht das Filtern von Inhalten.
                             * - Kann ausgewählt oder abgewählt werden (Toggle).
                             * - Ändert die Farbe basierend auf dem Zustand.
                             *
                             * Attribute:
                             * - onClick: Steuert, ob die Karten ausgeblendet werden.
                             * - label: Beschriftung des Chips.
                             * - selected: Gibt an, ob der Chip aktiv ist.
                             * - leadingIcon: Zeigt ein Symbol bei Aktivierung.
                             * - colors: Ändert die Farben je nach Zustand.
                             */
                            FilterChip(
                                onClick = { hideCards = !hideCards },
                                label = {
                                    Text(
                                        text = "Hide Cards",
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                },
                                selected = hideCards, // Status verwalten
                                leadingIcon = if (hideCards) {
                                    {
                                        Icon(
                                            Icons.Filled.Done,
                                            contentDescription = "Selected",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                },
                                modifier = Modifier.fillMaxSize(),
                                enabled = true,
                                shape = RoundedCornerShape(16.dp),
                                colors = FilterChipDefaults.filterChipColors(
                                    containerColor = if(hideCards) Color(0xFF4285F4) else Color(0xFF3DDC84),
                                    labelColor = Color.White,
                                    iconColor = Color.White
                                ),
                                elevation = FilterChipDefaults.filterChipElevation(
                                    elevation = 8.dp
                                ),
                                border = BorderStroke(1.dp, Color.DarkGray),
                                interactionSource = remember { MutableInteractionSource() }
                            )
                        }
                    }

                    // Zweite Zeile mit Input und Suggestion Chip
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Input Chip (zeigt Benutzereingaben und kann entfernt werden)
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                        ) {
                            if (userTags.isNotEmpty()) {
                                /**
                                 * InputChip
                                 * - Wird für Benutzereingaben verwendet, z. B. Tags.
                                 * - Kann verschiedene Werte durchklicken.
                                 * - Kann mit einem Close-Icon entfernt werden.
                                 *
                                 * Attribute:
                                 * - onClick: Wechsel des angezeigten Tags.
                                 * - label: Zeigt den aktuellen Tag an.
                                 * - avatar: Symbol vor dem Label.
                                 * - colors: Ändert die Farben.
                                 */
                                InputChip(
                                    onClick = {
                                        currentTagIndex = (currentTagIndex + 1) % userTags.size
                                    },
                                    label = {
                                        Text(
                                            text = userTags[currentTagIndex],
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    },
                                    selected = false,
                                    avatar = {
                                        Icon(
                                            Icons.Filled.Tag,
                                            contentDescription = "Tag Icon",
                                            Modifier.size(InputChipDefaults.AvatarSize)
                                        )
                                    },
                                    modifier = Modifier.fillMaxSize(),
                                    enabled = true,
                                    shape = RoundedCornerShape(16.dp),
                                    colors = InputChipDefaults.inputChipColors(
                                        containerColor = Color(0xFF4285F4),
                                        labelColor = Color.White
                                    ),
                                    elevation = InputChipDefaults.inputChipElevation(
                                        elevation = 8.dp
                                    ),
                                    border = BorderStroke(1.dp, Color.DarkGray),
                                    interactionSource = remember { MutableInteractionSource() }
                                )
                            }
                        }

                        // Suggestion Chip (macht Vorschläge, aber ist kein Toggle)
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                        ) {
                            /**
                             * SuggestionChip
                             * - Macht Vorschläge basierend auf der Nutzeraktion.
                             * - Zeigt eine Snackbar mit Informationen an.
                             *
                             * Attribute:
                             * - onClick: Zeigt die Snackbar.
                             * - label: Textinhalt des Chips.
                             * - icon: Symbol für den Vorschlag.
                             * - colors: Ändert das Design des Chips.
                             */
                            SuggestionChip(
                                onClick = {
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("Das sind Chips mit unterschiedlichen Funktionen. Klicke sie um die Wirkung zu sehen!")
                                    }
                                },
                                label = {
                                    Text(
                                        text = "Show hint",
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = "Info Icon",
                                        modifier = Modifier.size(InputChipDefaults.AvatarSize)
                                    )
                                },
                                modifier = Modifier.fillMaxSize(),
                                enabled = true,
                                shape = RoundedCornerShape(16.dp),
                                colors = SuggestionChipDefaults.suggestionChipColors(
                                    containerColor = Color(0xFF4285F4),
                                    labelColor = Color.White,
                                    iconContentColor = Color.White
                                ),
                                elevation = SuggestionChipDefaults.elevatedSuggestionChipElevation(
                                    elevation = 8.dp
                                ),
                                border = BorderStroke(1.dp, Color.DarkGray),
                                interactionSource = remember { MutableInteractionSource() }
                            )
                        }
                    }
                }

                if(!hideCards) {
                    cards.forEach { (cardTitle, cardDesc) ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    text = cardTitle,
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                                Text(
                                    text = cardDesc,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    if (showInfoDialog) {
        /**
         * Dialog-Komponente:
         * - Erstellt eine modale Popup-Oberfläche.
         * - `onDismissRequest`: Callback für das Schließen der Dialogs.
         * - `properties`: Zusätzliche Einstellungen wie `dismissOnClickOutside` oder `dismissOnBackPress`.
         * - `content`: Enthält das UI-Layout des Dialogs.
         */
        Dialog(
            onDismissRequest = { showInfoDialog = false },
            properties = DialogProperties(
                dismissOnClickOutside = false, // Verhindert das Schließen durch Tippen außerhalb
                dismissOnBackPress = true, // Erlaubt das Schließen durch den Zurück-Button
                usePlatformDefaultWidth = true // Verhindert eine benutzerdefinierte Breite für den Dialog
            )
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E1E1E),
                    contentColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(81.dp),
                border = BorderStroke(1.dp, Color.Gray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Info Icon",
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterHorizontally),
                        tint = Color(0xFF3DDC84)
                    )
                    Text(
                        "Information",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "In this navigation rail you will find several entries, each leading to a different page. This is information for you.",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { showInfoDialog = false },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4)),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Got it",
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }

    if (showAccountDialog) {
        /**
         * Dialog-Komponente:
         * - Erstellt eine modale Popup-Oberfläche.
         * - `onDismissRequest`: Callback für das Schließen der Dialogs.
         * - `properties`: Zusätzliche Einstellungen wie `dismissOnClickOutside` oder `dismissOnBackPress`.
         * - `content`: Enthält das UI-Layout des Dialogs.
         */
        Dialog(
            onDismissRequest = { showAccountDialog = false },
            properties = DialogProperties(
                dismissOnClickOutside = false, // Verhindert das Schließen durch Tippen außerhalb
                dismissOnBackPress = true, // Erlaubt das Schließen durch den Zurück-Button
                usePlatformDefaultWidth = true // Verhindert eine benutzerdefinierte Breite für den Dialog
            )
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E1E1E),
                    contentColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(8.dp),
                border = BorderStroke(1.dp, Color.Gray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "AccountCircle Icon",
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterHorizontally),
                        tint = Color(0xFF3DDC84)
                    )
                    Text(
                        "Hello Admin",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.padding(vertical = 4.dp)) {
                            Text("Email:", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("admin@test.com")
                        }
                        Row(modifier = Modifier.padding(vertical = 4.dp)) {
                            Text("Username:", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Admin")
                        }
                        Row(modifier = Modifier.padding(vertical = 4.dp)) {
                            Text("Password:", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("*********")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { showAccountDialog = false },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4)),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Close",
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }

    if (showAddCardDialog) {
        /**
         * AlertDialog-Komponente:
         * - Wird für wichtige Entscheidungsdialoge genutzt.
         * - Enthält einen Titel, eine Beschreibung und Aktionen.
         * - Nutzt `onDismissRequest`, um das Dialogfenster zu schließen.
         * - Unterstützt anpassbare Farben, Formen und Inhalte.
         *
         * Parameter:
         * - `modifier`: Anpassung des Layouts.
         * - `shape`: Bestimmt die Form des Dialogs (z. B. `RoundedCornerShape`).
         * - `containerColor`: Hintergrundfarbe des Dialogs.
         * - `iconContentColor`: Farbe des Icons (falls vorhanden).
         * - `titleContentColor`: Farbe des Titels.
         * - `textContentColor`: Farbe des Beschreibungstextes.
         * - `tonalElevation`: Bestimmt den Schatteneffekt des Dialogs.
         * - `properties`: Zusätzliche Einstellungen wie `dismissOnClickOutside`.
         *
         * Dialog: Wenn man volle Kontrolle über das Design und Layout benötigt.
         * AlertDialog: Wenn man eine standardisierte Warnmeldung, Bestätigung oder Benutzereingabe benötigt
         * nach dem Material3-Schema.
         */
        AlertDialog(
            modifier = Modifier,
            shape = RoundedCornerShape(16.dp),
            containerColor = Color(0xFF1E1E1E),
            iconContentColor = Color(0xFF3DDC84),
            titleContentColor = Color.White,
            textContentColor = Color.Gray,
            tonalElevation = 8.dp,
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = true,
                usePlatformDefaultWidth = true
            ),
            icon = {

            },
            title = {
                Text(
                    text = "Add a new Card",
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                FloatingActionButton(
                    onClick = {
                        if (title.isNotBlank() && description.isNotBlank()) {
                            cards = cards + (title to description)
                            title = ""
                            description = ""
                            showAddCardDialog = false
                        }
                    },
                    shape = CircleShape,
                    containerColor = Color.Green,
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Check Icon"
                    )
                }
            },
            onDismissRequest = { showAddCardDialog = false },
            dismissButton = {
                FloatingActionButton(
                    onClick = {
                        showAddCardDialog = false
                    },
                    shape = CircleShape,
                    containerColor = Color.Red,
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Icon"
                    )
                }
            }
        )
    }
}
*/
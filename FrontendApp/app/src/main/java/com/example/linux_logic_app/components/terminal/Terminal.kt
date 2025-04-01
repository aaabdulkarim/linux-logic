package com.example.linux_logic_app.components.terminal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linux_logic_app.components.viewmodels.UserViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WebSocketClient(url: String) {
    private val client = OkHttpClient()
    private val request = Request.Builder().url(url).build()
    private lateinit var webSocket: WebSocket
    var output = ""
    private var messageReceived = false

    fun connect() {
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                println("Connected to WebSocket")
                output = "Connected to"
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                println("Received message: $text")
                messageReceived = true
                output = text

            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                println("Received bytes: $bytes")
                output = bytes.toString()
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                println("Closing WebSocket: $code / $reason")
                webSocket.close(code, reason)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                println("WebSocket error: ${t.message}")
            }
        })
    }

    fun sendMessage(message: String) {
        webSocket.send(message)
        messageReceived = false
    }

    fun waiting(): Boolean {
        return !messageReceived
    }

    fun disconnect() {
        webSocket.close(1000, "Goodbye!")
    }
}

/**
 * Composable-Funktion, die ein Terminal-Interface darstellt.
 * Diese Funktion implementiert ein benutzerdefiniertes Terminal, das sowohl im Live- als auch im
 * Preview-Modus verwendet werden kann. Im Live-Modus wird ein WebSocket-Client erstellt, der
 * Benutzereingaben sendet und Antworten empfängt. Im Preview-Modus werden feste, beispielhafte
 * Ausgaben angezeigt, um das Layout zu demonstrieren.
 * @param socketUrl URL des WebSocket-Servers.
 * @param preview Flag, das angibt, ob die Funktion im Preview-Modus ausgeführt wird.
 * Ist true, wird kein WebSocket-Client erstellt und es erfolgt keine echte Kommunikation.
 * @param userViewModel ViewModel, welches u.a. Terminal-Farben und Logik bereitstellt.
 */
@Composable
fun Terminal(socketUrl: String, preview: Boolean = false, userViewModel: UserViewModel) {
    // Terminal-Farben aus dem UserViewModel beziehen
    val terminalColors = userViewModel.terminalViewModel.terminalColors

    // Statusvariablen: terminalOutput enthält alle bisherigen Ausgaben, userInput speichert die aktuelle Eingabe
    var terminalOutput by remember { mutableStateOf(listOf("Welcome to logic terminal!")) }
    var userInput by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    // WebSocketClient wird nur erstellt, wenn nicht im Preview-Modus, um unnötige Verbindungen zu vermeiden
    val webSocketClient = remember { if (!preview) WebSocketClient(socketUrl) else null }

    // Im Live-Modus wird der WebSocket-Client beim Start verbunden
    if (!preview) {
        LaunchedEffect(Unit) {
            webSocketClient?.connect()
        }
    }

    // Hauptcontainer des Terminals
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                // Hintergrund und abgerundete Ecken für das Terminal-Fenster
                .background(
                    color = terminalColors.bodyColor,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            // Header-Bereich des Terminals
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    // Header mit eigener Hintergrundfarbe und abgerundeten oberen Ecken
                    .background(
                        terminalColors.headerColor,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = "logic terminal",
                    color = terminalColors.headerTextColor,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
            }

            // Container für Terminal-Ausgabe und Eingabe
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        terminalColors.bodyColor,
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
                    .padding(8.dp)
                    .verticalScroll(scrollState)
            ) {
                Column {
                    // Anzeige der bisherigen Terminal-Ausgaben
                    terminalOutput.forEach { line ->
                        Text(
                            text = line,
                            color = terminalColors.commandColor,
                            fontSize = 14.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    // Eingabezeile: Hier wird zwischen Preview- und Live-Modus unterschieden
                    Row {
                        if (preview) {
                            // Im Preview-Modus: Fester Text zur Darstellung des Layouts
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = terminalColors.shellPromptColor)) {
                                        append("lilo@beta:~$ ")
                                    }
                                    withStyle(style = SpanStyle(color = terminalColors.commandColor)) {
                                        append("ls\n__pycache__    scenario_x.txt\n")
                                    }
                                },
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        } else {
                            // Anzeige des Shell-Prompts im Live-Modus
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = terminalColors.shellPromptColor)) {
                                        append("lilo@beta:")
                                    }
                                    withStyle(style = SpanStyle(color = terminalColors.shellPromptColor)) {
                                        append("~")
                                    }
                                    withStyle(style = SpanStyle(color = terminalColors.shellPromptColor)) {
                                        append("$ ")
                                    }
                                },
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            // Eingabefeld zur Verarbeitung der Benutzereingabe
                            BasicTextField(
                                value = userInput,
                                onValueChange = { userInput = it },
                                textStyle = TextStyle(
                                    color = terminalColors.commandColor,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Monospace
                                ),
                                cursorBrush = SolidColor(terminalColors.cursorColor),
                                //readOnly = preview,  Im Preview-Modus werden Eingaben nicht verarbeitet
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        // Verarbeitung der Eingabe, falls nicht leer
                                        if (userInput.isNotBlank()) {
                                            if (userInput == "clear") {
                                                // Bei "clear" wird der Terminal-Output geleert
                                                terminalOutput = listOf("")
                                            } else {
                                                // Sende die Benutzereingabe an den WebSocket-Client
                                                webSocketClient?.sendMessage(userInput)

                                                // Temporärer Fix: Warte, bis der Client keine weitere Nachricht erwartet
                                                while (webSocketClient?.waiting() == true) {
                                                    continue
                                                }

                                                // Füge die Eingabe und die Antwort des Servers dem Terminal-Output hinzu
                                                terminalOutput = terminalOutput +
                                                        "lilo@beta:~$ $userInput" +
                                                        (webSocketClient?.output ?: "")

                                                // Leere das Eingabefeld
                                                userInput = ""
                                            }
                                        }
                                    }
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Preview-Composable für das Terminal.
 * Dieses Composable stellt das Terminal im Preview-Modus dar, wobei feste Daten verwendet werden, um
 * das Layout ohne aktive Backend-Kommunikation zu visualisieren.
 * @param userViewModel ViewModel, das zur Darstellung der Terminal-Farben und weiterer Einstellungen genutzt wird.
 */
@Composable
fun PreviewTerminal(userViewModel: UserViewModel) {
    Terminal("ws://172.20.10.2:8000/ws", preview = true, userViewModel = userViewModel)
}

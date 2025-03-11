package com.example.linux_logic_app.components

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

@Composable
fun Terminal(socketUrl: String, preview: Boolean = false, userViewModel: UserViewModel) {
    val terminalColors = userViewModel.terminalViewModel.terminalColors
    var terminalOutput by remember { mutableStateOf(listOf("Welcome to logic terminal!")) }
    var userInput by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    // Im Preview-Modus wird kein WebSocket-Client erstellt
    val webSocketClient = remember { if (!preview) WebSocketClient(socketUrl) else null }

    if (!preview) {
        LaunchedEffect(Unit) {
            webSocketClient?.connect()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = terminalColors.bodyColor,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        terminalColors.headerColor,
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
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

            // Terminal Output und Input
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        terminalColors.bodyColor,
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    )
                    .padding(8.dp)
                    .verticalScroll(scrollState)
            ) {
                Column {
                    // Ausgabe
                    terminalOutput.forEach { line ->
                        Text(
                            text = line,
                            color = terminalColors.commandColor,
                            fontSize = 14.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    // Eingabezeile
                    Row {
                        if (preview) {
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
                            // Im Preview-Modus schalten wir den TextField auf readOnly
                            BasicTextField(
                                value = userInput,
                                onValueChange = { userInput = it },
                                textStyle = TextStyle(
                                    color = terminalColors.commandColor,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Monospace
                                ),
                                cursorBrush = SolidColor(terminalColors.cursorColor),
                                readOnly = preview,  // verhindert, dass im Preview-Modus Eingaben verarbeitet werden
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        if (userInput.isNotBlank()) {
                                            if (userInput == "clear") {
                                                terminalOutput = listOf("")
                                            } else {
                                                webSocketClient?.sendMessage(userInput)
                                                // Temporärer Fix: Warte, falls der Client noch Message erwartet
                                                while (webSocketClient?.waiting() == true) {
                                                    continue
                                                }

                                                terminalOutput = terminalOutput +
                                                        "lilo@beta:~$ $userInput" +
                                                        (webSocketClient?.output ?: "")

                                                userInput = "" // Eingabe leeren
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

@Composable
fun PreviewTerminal(userViewModel: UserViewModel) {
    Terminal("ws://172.20.10.2:8000/ws", preview = true, userViewModel = userViewModel)
}

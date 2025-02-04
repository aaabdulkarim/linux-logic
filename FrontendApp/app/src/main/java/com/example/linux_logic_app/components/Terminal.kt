package com.example.linux_logic_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.example.linux_logic_app.ui.theme.LiloDark
import okhttp3.*
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
fun Terminal(socketUrl: String, preview: Boolean = false) {
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
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = LiloDark,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.Black,
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = "logic terminal",
                    color = Color.White,
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
                        LiloDark,
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
                            color = Color.White,
                            fontSize = 14.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    // Eingabezeile
                    Row {
                        if (preview) {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color.Green)) {
                                        append("lilo@beta:~$ ")
                                    }
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append("ls\n__pycache__    scenario_x.txt\n")
                                    }
                                },
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        } else {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color.Green)) {
                                        append("lilo@beta:")
                                    }
                                    withStyle(style = SpanStyle(color = Color.Green)) {
                                        append("~")
                                    }
                                    withStyle(style = SpanStyle(color = Color.Green)) {
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
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Monospace
                                ),
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


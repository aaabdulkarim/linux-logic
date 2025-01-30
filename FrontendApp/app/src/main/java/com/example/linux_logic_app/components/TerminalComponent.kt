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
fun Terminal(socketUrl : String) {
    var terminalOutput by remember { mutableStateOf(listOf("Welcome to logic terminal!")) }
    var userInput by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val socketState by remember {
        mutableStateOf("Message")
    }

    val webSocketClient = remember { WebSocketClient(socketUrl)}

    LaunchedEffect(Unit){
        webSocketClient.connect()

    }

    // Terminal UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF404242), shape = RoundedCornerShape(16.dp)) // Rounded terminal box
        ) {
            // Header (separater Bereich oben)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.Black,
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    ) // Rounded header top
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

            // Terminal Output and Input
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        Color(0xFF404242),
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    ) // Rounded terminal bottom
                    .padding(8.dp)
                    .verticalScroll(scrollState)
            ) {
                Column {
                    // Terminal output
                    terminalOutput.forEach { line ->
                        Text(
                            text = line,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    // User Input
                    Row {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.Green)) {
                                    append("lilo@beta:")
                                }
                                withStyle(style = SpanStyle(color = Color(0xFF0073FF))) {
                                    append("~")
                                }
                                withStyle(style = SpanStyle(color = Color.Green)) {
                                    append("$ ")
                                }
                            },
                            fontSize = 14.sp,
                            fontFamily = FontFamily.Monospace
                        )
                        BasicTextField(
                            value = userInput,
                            onValueChange = { userInput = it },
                            textStyle = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    if (userInput.isNotBlank()) {
                                        if (userInput == "clear") {
                                            terminalOutput = listOf("")
                                        }else{
                                            webSocketClient.sendMessage(userInput)

                                            // Temporärer Fix
                                            // Wenn der Client nie eine Message erhält, kann es sein, dass die App freezed
                                            while (webSocketClient.waiting()) {
                                                continue
                                            }
                                            terminalOutput = terminalOutput + "lilo@beta:~$ ${userInput}" + webSocketClient.output
                                            userInput = "" // Clear input
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

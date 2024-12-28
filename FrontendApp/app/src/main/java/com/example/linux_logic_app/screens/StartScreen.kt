package com.example.linux_logic_app.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.linux_logic_app.R

@Composable
fun StartScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF569191))
    ) {
        Image(
            alignment = Alignment.TopCenter,
            painter = painterResource(id = R.drawable.linux_logic_start),
            contentDescription = "Abstraktes Aqua Hintergrundbild",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = {
                Log.i("$LocalContext Action", "User performing \"Login\"")
            }
        ) {
            Text(text = "Anmelden")
        }
    }

}
package com.example.linux_logic_app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun ColorPicker(onColorSelected: (Color) -> Unit) {
    var selectedColor by remember { mutableStateOf(Color.Transparent) }
    val controller = rememberColorPickerController()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AlphaTile(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .clip(RoundedCornerShape(6.dp)),
                controller = controller
            )
        }
        HsvColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.7f)
                .padding(16.dp),
            controller = controller,
            onColorChanged = { colorEnvelope ->
                val color = colorEnvelope.color
                onColorSelected(color)
                selectedColor = color
            }
        )
        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(32.dp),
            controller = controller,
            tileOddColor = Color.White,
            tileEvenColor = Color.Black
        )
        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(32.dp),
            controller = controller,
        )
    }
}

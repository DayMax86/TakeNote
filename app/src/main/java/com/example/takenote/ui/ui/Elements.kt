package com.example.takenote.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.takenote.enums.Notes

@Composable
fun KeyRectangle(
    note: Notes,
    noteWidth: Int,
) {
    Box(
        modifier = Modifier
            .border(1.dp, Color.Yellow)
            .width(noteWidth.dp)
            .fillMaxHeight(0.8f)
            .background(color = Color.Gray)
            .clip(RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = note.toString())
    }
}

@Composable
fun StaveBar(
    clefBuffer: Int,
) {
    Box(
        modifier = Modifier
            .padding(top = 18.dp, start = clefBuffer.dp)
            .fillMaxWidth()
            .height(5.dp)
            .background(Color.Black)
    ) {

    }
}

@Composable
fun HitZone(
    zoneWidth: Int,
    zoneHeight: Int,
    clefBuffer: Int,
) {
    Box(
        modifier = Modifier
            .padding(top = 18.dp, start = clefBuffer.dp)
            .height(zoneHeight.dp)
            .width(zoneWidth.dp)
            .background(Color.Green)
    ) {

    }
}
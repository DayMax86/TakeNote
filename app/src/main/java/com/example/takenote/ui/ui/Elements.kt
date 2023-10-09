package com.example.takenote.ui.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.takenote.enums.Notes


@Composable
fun NoteBox(
    dimensions: Int,
    modifier: Modifier,
) {

    Box(
        modifier = modifier
            .border(1.dp, Color.Red)
            .size(dimensions.dp, dimensions.dp)
            .clip(RoundedCornerShape(1.dp))
    )
}

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
            .padding(top = 18.dp)
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
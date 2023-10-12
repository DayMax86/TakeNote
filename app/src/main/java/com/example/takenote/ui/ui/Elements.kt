package com.example.takenote.ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.takenote.R
import com.example.takenote.classes.Note
import com.example.takenote.enums.NoteNames


@Composable
fun NoteBox(
    noteName: NoteNames,
    modifier: Modifier,
) {

    Box(
        modifier = modifier
            .wrapContentSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = R.drawable.transparent_note,
            contentDescription = null,
        )
    }
}

@Composable
fun Clef(modifier: Modifier) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(R.drawable.treble_clef)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = null,
        alignment = Alignment.Center,
        )
}

@Composable
fun KeyRectangle(
    note: NoteNames,
    noteWidth: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .border(1.dp, Color.Yellow)
            .width(noteWidth.dp)
            .fillMaxHeight(0.8f)
            .background(color = Color.Gray)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onClick()
            },
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
            .padding()
            .fillMaxWidth()
            .height(3.dp)
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
            .padding(start = clefBuffer.dp)
            .height(zoneHeight.dp)
            .width(zoneWidth.dp)
            .background(Color.Green),
    )
}
package com.example.takenote.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.takenote.classes.Note
import com.example.takenote.enums.Notes
import com.example.takenote.ui.ui.HitZone
import com.example.takenote.ui.ui.KeyRectangle
import com.example.takenote.ui.ui.NoteBox
import com.example.takenote.ui.ui.StaveBar
import com.example.takenote.viewmodels.GameViewModel

@Composable
fun DisplayGame(
    viewModel: GameViewModel,
    whiteKeyWidth: Int,
    clefBuffer: Int,
    zoneWidth: Int,
    staveHeight: Int,
) {

    Box(
        modifier = Modifier
            //.border(2.dp, Color.Red)
    ) {
        HitZone(
            zoneWidth = zoneWidth,
            zoneHeight = staveHeight,
            clefBuffer = clefBuffer
        )

    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        DisplayStave(
            clefBuffer = clefBuffer,
            staveHeight = staveHeight
        )
        DisplayKeys(
            whiteKeyWidth = whiteKeyWidth
        )
    }

    DisplayNotes(
        viewModel.activeNotes
    )

}

@Composable
fun DisplayNotes(
    activeNotes: ArrayList<Note>,
) {
    activeNotes.forEach {
        NoteBox(it.dimensions)
    }
}


@Composable
fun DisplayStave(
    clefBuffer: Int,
    staveHeight: Int,
) {
    Row( //This is where the stave will go
        modifier = Modifier
            .fillMaxWidth()
            .height(staveHeight.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //Stave goes in here
            //Column composed of 5 stave bars
            StaveBar(clefBuffer)
            StaveBar(clefBuffer)
            StaveBar(clefBuffer)
            StaveBar(clefBuffer)
            StaveBar(clefBuffer)
        }
    }
}

@Composable
fun DisplayKeys(
    whiteKeyWidth: Int,
) {
    Row(
        //This will contain the keys
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom,
    ) {
        KeyRectangle(Notes.C, whiteKeyWidth)
        KeyRectangle(Notes.D, whiteKeyWidth)
        KeyRectangle(Notes.E, whiteKeyWidth)
        KeyRectangle(Notes.F, whiteKeyWidth)
        KeyRectangle(Notes.G, whiteKeyWidth)
        KeyRectangle(Notes.A, whiteKeyWidth)
        KeyRectangle(Notes.B, whiteKeyWidth)
    }
}
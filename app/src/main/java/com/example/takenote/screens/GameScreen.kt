package com.example.takenote.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.takenote.enums.Notes
import com.example.takenote.ui.ui.KeyRectangle
import com.example.takenote.viewmodels.GameViewModel

@Composable
fun DisplayGame(
    viewModel: GameViewModel,
    whiteNoteWidth: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Row( //This is where the stave will go
            modifier = Modifier
                .border(1.dp, Color.Black)
                .fillMaxWidth()
        ) {
            //Stave goes in here
        }

        Row(
            //This will contain the keys
            modifier = Modifier
                .border(1.dp, Color.Blue)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom,
        ) {
            KeyRectangle(Notes.C, whiteNoteWidth)
            KeyRectangle(Notes.D, whiteNoteWidth)
            KeyRectangle(Notes.E, whiteNoteWidth)
            KeyRectangle(Notes.F, whiteNoteWidth)
            KeyRectangle(Notes.G, whiteNoteWidth)
            KeyRectangle(Notes.A, whiteNoteWidth)
            KeyRectangle(Notes.B, whiteNoteWidth)
        }
    }
}
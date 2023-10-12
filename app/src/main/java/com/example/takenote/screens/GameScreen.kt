package com.example.takenote.screens

import android.graphics.drawable.shapes.RectShape
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.takenote.classes.Note
import com.example.takenote.enums.NoteNames
import com.example.takenote.ui.ui.Clef
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
    staveHeight: Float,
    backBehaviour: () -> Unit,
) {
    Column {

        Spacer(
            modifier = Modifier
                .padding(20.dp)
                .height(50.dp)
                //.border(2.dp, Color.Cyan)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Clef(
                modifier = Modifier
                    //.border(2.dp, Color.Red)
                    .height((staveHeight * 1.5).dp)
                    .width(clefBuffer.dp)
                    .offset(y = -((staveHeight / 10) * 2).dp),
            )

            Box(
                modifier = Modifier
                //.border(2.dp, Color.Red)
            ) {
                HitZone(
                    zoneWidth = zoneWidth,
                    zoneHeight = staveHeight.toInt(),
                    clefBuffer = clefBuffer,
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                DisplayStave(
                    clefBuffer = clefBuffer,
                    staveHeight = staveHeight.toInt()
                )
                DisplayKeys(
                    whiteKeyWidth = whiteKeyWidth,
                    viewModel = viewModel,
                )
            }

            DisplayNotes(
                viewModel.activeNotes,
            )

            BackHandler {
                backBehaviour()
                viewModel.navigateBack()
            }
        }
    }
}

@Composable
fun DisplayNotes(
    activeNotes: ArrayList<Note>,
) {
    activeNotes.forEach {
        val modifier = Modifier
            .offset(x = it.xPos.dp, y = it.yPos.dp)
            .border(
                1.dp,
                if (!it.inZone) {
                    Color.Unspecified
                } else {
                    Color.Red
                }
            )
            .size((it.dimensions).dp, (it.dimensions).dp)
        //.clip(RoundedCornerShape(13.dp))
        //.background(Color.Black)
        NoteBox(it.noteName, modifier)
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
            verticalArrangement = Arrangement.SpaceAround
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
    viewModel: GameViewModel,
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
        KeyRectangle(NoteNames.C, whiteKeyWidth) { viewModel.onKeyPress(NoteNames.C) }
        KeyRectangle(NoteNames.D, whiteKeyWidth) { viewModel.onKeyPress(NoteNames.D) }
        KeyRectangle(NoteNames.E, whiteKeyWidth) { viewModel.onKeyPress(NoteNames.E) }
        KeyRectangle(NoteNames.F, whiteKeyWidth) { viewModel.onKeyPress(NoteNames.F) }
        KeyRectangle(NoteNames.G, whiteKeyWidth) { viewModel.onKeyPress(NoteNames.G) }
        KeyRectangle(NoteNames.A, whiteKeyWidth) { viewModel.onKeyPress(NoteNames.A) }
        KeyRectangle(NoteNames.B, whiteKeyWidth) { viewModel.onKeyPress(NoteNames.B) }
    }
}
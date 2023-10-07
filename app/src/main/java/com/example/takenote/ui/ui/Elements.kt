package com.example.takenote.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .fillMaxWidth(noteWidth)
//            .padding(1.dp),
//        verticalArrangement = Arrangement.SpaceEvenly,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    )
//    {
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
//    }
}
package com.example.takenote.classes

import androidx.compose.runtime.IntState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import com.example.takenote.enums.NoteNames

data class Note(val noteName: NoteNames, val screenWidth: Int) {
    var travelSpeed: Int = 2
    private val note = noteName
    var xPos by mutableIntStateOf(screenWidth)
    var yPos: Float = 0f
    var dimensions: Int = 20
    var inZone: Boolean = false

    fun travel(speed: Int) {
        this.xPos -= speed
    }

    fun getBounds(): Rect {
        return Rect(
            left = xPos.toFloat(),
            right = (xPos + dimensions).toFloat(),
            top = (yPos + dimensions).toFloat(),
            bottom = yPos.toFloat(),
        )
    }


}
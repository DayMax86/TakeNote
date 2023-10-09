package com.example.takenote.classes

import androidx.compose.ui.geometry.Rect
import com.example.takenote.enums.NoteNames

data class Note(val noteName: NoteNames) {
    var travelSpeed: Int = 10
    private val note = noteName
    var xPos: Int = 0
    var yPos: Int = 0
    var dimensions: Int = 10

    fun travel() {
        this.xPos -= travelSpeed
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
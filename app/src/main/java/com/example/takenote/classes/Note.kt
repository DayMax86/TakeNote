package com.example.takenote.classes

import com.example.takenote.enums.Notes

class Note (noteName: Notes) {
    var travelSpeed: Int = 0
    private val note = noteName
    var xPos: Int = 0
    var yPos: Int = 0
    var dimensions: Int = 10

    fun travel() {
        this.xPos -= travelSpeed
    }

}
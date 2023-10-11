package com.example.takenote.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.takenote.classes.Note
import com.example.takenote.enums.NoteNames
import com.example.takenote.ui.ui.HitZone
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel(
    private val navController: NavHostController,
    scrWidth: Int,
    scrHeight: Int,
) : ViewModel() {

    var screenWidth: Int = scrWidth
    var screenHeight: Int = scrHeight
    private var difficultyWidthMultiplier =
        1 //reduce this value (always > 0) to increase difficulty
    val staveHeight: Int = scrHeight / 150 * 10
    var whiteKeyWidth: Int = scrWidth / 150 * 7
    val clefBuffer: Int = scrWidth / 12
    var zoneWidth: Int =
        whiteKeyWidth * difficultyWidthMultiplier //By default the hitbox width is the same as a key width
    var zoneHeight: Int = staveHeight

    var gameRunning: Boolean = true

    val activeNotes = ArrayList<Note>()
    var deadNotes = ArrayList<Note>()

    private val hitZone by mutableStateOf(
        HitZone(
            zoneWidth,
            zoneHeight,
            clefBuffer,
            staveHeight,
        )
    )


    init {
        viewModelScope.launch {
            while (gameRunning) {
                //Manually force recompose of notes

                playGame(hitZone)
                delay(500) //TODO()
            }
        }
    }

    private fun playGame(zone: HitZone) {

        activeNotes.forEach { note ->
            //Check if any note is currently in the success area
            note.inZone = checkNoteInZone(note, zone)
            //Move the note
            note.travel()
            //Check for any missed notes
            if (checkMissedNote(note, zone)) {
                deadNotes.add(note)
            }

        }


        //Remove dead notes from activeNotes list
        deadNotes.forEach { note ->
            if (activeNotes.contains(note)) {
                activeNotes.remove(note)
            }
        }
        //Spawn another note if activeNotes is empty
        if (activeNotes.isEmpty()) {
            spawnNote(chooseRandomNote())
        }
        //Affect score
        //TODO()

        //Clear dead notes list
        deadNotes.clear()
    }

    private fun checkMissedNote(note: Note, zone: HitZone): Boolean {
        return (note.xPos < zone.getBounds().left)
    }

    private fun checkNoteInZone(note: Note, zone: HitZone): Boolean {
        return note.getBounds().overlaps(zone.getBounds())
    }

    private fun chooseRandomNote(
        //flats: Boolean,
        //sharps: Boolean,
    ): NoteNames {
        //Choose a random note from the enum
        return NoteNames.values().random()
    }

    private fun spawnNote(
        noteName: NoteNames
    ) {
        //move note in from the right at a certain speed by updating its coordinates
        //make sure coordinates are screen size independent and match the success hitbox
        val note = Note(noteName, screenWidth)
        note.xPos = (screenWidth + note.dimensions)   //spawn off-screen to the right
        //note.yPos needs to know where the stave lines are and match them with the note names
        //Temporarily hardcode a yPos value
        note.yPos = screenHeight / 2
        //---------------------------------
        activeNotes.add(note)
    }


}

data class HitZone(
    var zoneWidth: Int,
    var zoneHeight: Int,
    var clefBuffer: Int,
    var staveHeight: Int,
) {
    fun getBounds(): Rect {
        return Rect(
            left = clefBuffer.toFloat(),
            right = (clefBuffer + zoneWidth).toFloat(),
            top = (staveHeight + zoneHeight).toFloat(),
            bottom = staveHeight.toFloat(),
        )
    }
}
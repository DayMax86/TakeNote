package com.example.takenote.viewmodels

import androidx.compose.ui.geometry.Rect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.takenote.classes.Note
import com.example.takenote.enums.NoteNames
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

    var gameRunning: Boolean = true

    var activeNotes = ArrayList<Note>()

    init {
        viewModelScope.launch {
            while (gameRunning) {
                playGame()
                delay(1000)
            }
        }
    }

    private fun playGame() {

        //Check if any note is currently in the success area
        checkNoteInZone()
        //Move the note
        //activeNotes.forEach {it.travel}
        //Remove the note if required
        //if noteToBeDeleted...
        //Affect score


    }

    fun checkNoteInZone(note: Note, zone: Zone): Boolean {
        return if (note.getBounds().overlaps())
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
        val note = Note(noteName)
        note.xPos = screenWidth + note.dimensions //spawn off-screen to the right
        //note.yPos needs to know where the stave lines are and match them with the note names
        //Temporarily hardcode a yPos value
        note.yPos = screenHeight / 2
        //---------------------------------
        activeNotes.add(note)
    }


}

data class HitZone (
    var zoneWidth: Int,
    var zoneHeight: Int,
    var clefBuffer: Int,
) {
    fun getBounds(): Rect {
        return Rect(
            left = clefBuffer.toFloat(),
            right = (clefBuffer + zoneWidth).toFloat(),
            top = .toFloat(),
            bottom = .toFloat(),
        )
    }
}
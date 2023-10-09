package com.example.takenote.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.takenote.classes.Note
import com.example.takenote.enums.Notes
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
        //viewModelScope.launch {
        var noteToBeDeleted = false

        if (activeNotes.isEmpty()) {
            spawnNote(chooseRandomNote())
        } else {
            activeNotes.forEach {
                it.travel()
                //New notes only spawn when the last one is removed
                //That way the spawn delay is controlled by the speed of the notes
                if (it.xPos < clefBuffer) {
                    noteToBeDeleted = true
                }
            }
        }
        if (noteToBeDeleted) {
            activeNotes.clear()
        }

        //}

    }

    private fun chooseRandomNote(
        //flats: Boolean,
        //sharps: Boolean,
    ): Notes {
        //Choose a random note from the enum
        return Notes.values().random()
    }

    private fun spawnNote(
        noteName: Notes
    ) {
        //move note in from the right at a certain speed by updating its coordinates
        //make sure coordinates are screen size independent and match the success hitbox
        val note = Note(noteName)
        note.xPos = screenWidth + note.dimensions
        //note.yPos needs to know where the stave lines are and match them with the note names
        //Temporarily hardcode a yPos value
        note.yPos = screenHeight / 2
        //---------------------------------
        activeNotes.add(note)
    }


}
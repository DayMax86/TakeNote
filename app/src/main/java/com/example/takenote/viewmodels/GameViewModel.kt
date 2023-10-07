package com.example.takenote.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.takenote.classes.Note
import com.example.takenote.enums.Notes
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.timerTask

class GameViewModel(
    private val navController: NavHostController,
    scrWidth: Int,
    scrHeight: Int,
) : ViewModel() {

    private var screenWidth: Int = scrWidth
    private var difficultyWidthMultiplier =
        1 //reduce this value (always > 0) to increase difficulty
    val staveHeight: Int = scrHeight / 150 * 10
    var whiteKeyWidth: Int = scrWidth / 150 * 7
    val clefBuffer: Int = scrWidth / 12
    var zoneWidth: Int =
        whiteKeyWidth * difficultyWidthMultiplier //By default the hitbox width is the same as a key width

    var gameRunning: Boolean = false

    val timer: Timer = Timer()
    var timeBetweenNotes: Long = 5000L //5 seconds for now

    var activeNotes = ArrayList<Note>()

    init {
        gameRunning = true
        timer.schedule(
            timerTask { chooseRandomNote() },
            timeBetweenNotes,
        )

    }

    private fun chooseRandomNote(
        //flats: Boolean,
        //sharps: Boolean,
    ): Notes {
        //Choose a random note from the enum
        return Notes.values().random()
    }

    fun spawnNote(
        noteName: Notes
    ) {
        //move note in from the right at a certain speed by updating its coordinates
        //make sure coordinates are screen size independent and match the success hitbox
        val note = Note(noteName)
        note.xPos = screenWidth + note.dimensions
        //note.yPos needs to know where the stave lines are and match them with the note names
        activeNotes.add(note)
    }

    fun cycleGameLoop() {
        viewModelScope.launch {
            while (gameRunning) {
                activeNotes.forEach { note ->
                    note.travel()
                }
            }
        }

    }

}
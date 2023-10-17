package com.example.takenote.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
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
    toggleTopBar: () -> Unit,
) : ViewModel() {

    var screenWidth: Int = scrWidth
    var screenHeight: Int = scrHeight
    private var difficultyWidthMultiplier =
        1 //reduce this value (always > 0) to increase difficulty
    val staveHeight: Float = (scrHeight.toFloat() / 200f * 10f)
    var whiteKeyWidth: Int = scrWidth / 135 * 7
    var keyBackgroundColor by mutableStateOf(Color.White)
    val clefBuffer: Int = scrWidth / 12
    var zoneWidth: Int =
        whiteKeyWidth * difficultyWidthMultiplier //By default the hitbox width is the same as a key width
    var zoneHeight: Int = staveHeight.toInt()

    var travelSpeed: Int = 3 //Make this proportionate to note/stave dimensions TODO()

    var gameRunning: Boolean = true

    var keysArray: MutableList<Key> = mutableStateListOf()

    val activeNotes = ArrayList<Note>()
    var deadNotes = ArrayList<Note>()

    val hitZone by mutableStateOf( //Temporarily public for testing //TODO()
        HitZone(
            zoneWidth,
            zoneHeight,
            clefBuffer,
            staveHeight,
        )
    )

    init {
        populateKeysArray()
        toggleTopBar()
        viewModelScope.launch {
            while (gameRunning) {
                //Manually force recompose of notes

                playGame(hitZone)
                delay(10)
            }
        }
    }

    private fun populateKeysArray() {
        NoteNames.values().forEach {
            keysArray.add(
                Key (
                    note = it,
                    width = whiteKeyWidth,
                )
            )
        }

//        keysArray.add(Key(NoteNames.E))
//        keysArray.add(Key(NoteNames.F))
//        keysArray.add(Key(NoteNames.G))
//        keysArray.add(Key(NoteNames.C))
//        keysArray.add(Key(NoteNames.D))
//        keysArray.add(Key(NoteNames.A))
//        keysArray.add(Key(NoteNames.B))
//        //Sharps/flats can be added later
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    private fun playGame(zone: HitZone) {

        activeNotes.forEach { note ->
            //Check if any note is currently in the success area
            note.inZone = checkNoteInZone(note, zone)
            //Move the note
            note.travel(travelSpeed)
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
        return note.xPos <= zone.getBounds().right
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
        note.xPos = (screenWidth)   //spawn off-screen to the right
        //note.yPos needs to know where the stave lines are and match them with the note names
        note.dimensions = ((staveHeight / 10) * 2).toInt()

        note.yPos = when (noteName) {
            NoteNames.C ->
                ((staveHeight / 10) * 2)

            NoteNames.D ->
                ((staveHeight / 10) * 3)

            NoteNames.E ->
                ((staveHeight / 10) * 8)

            NoteNames.F ->
                ((staveHeight / 10) * 7)

            NoteNames.G ->
                ((staveHeight / 10) * 6)

            NoteNames.A ->
                ((staveHeight / 10) * 5)

            NoteNames.B ->
                ((staveHeight / 10) * 4)
        }

        activeNotes.add(note)
    }


    fun onKeyPress(key: Key) {
        val index = keysArray.indexOf(key)

        //Change the colour of the key so the user can see that it was pressed
        viewModelScope.launch {
            activeNotes.forEach { activeNote ->
                if (activeNote.inZone) {
                    if (activeNote.noteName == key.note) {
                        //Successful hit of the note! Celebrate()!!
                        Log.v("gvm", "Successful hit")
                        keysArray[index] = key.copy(backgroundColor = Color.Green)
                    } else {
                        //Note was in zone but wrong key pressed
                        Log.v("gvm", "In zone but missed")
                        keysArray[index] = key.copy(backgroundColor = Color.Red)
                    }
                } else {
                    //Note wasn't in zone
                    Log.v("gvm", "Not even in the zone")
                    keysArray[index] = key.copy(backgroundColor = Color.Blue)
                }
            }
            delay(100)
            keysArray[index] = key.copy(backgroundColor = Color.White)
        }
    }
}

data class Key(
    val note: NoteNames,
    val backgroundColor: Color = Color.White,
    val width: Int = 10,
)

data class HitZone(
    var zoneWidth: Int,
    var zoneHeight: Int,
    var clefBuffer: Int,
    var staveHeight: Float,
) {
    fun getBounds(): Rect {
        return Rect(
            left = clefBuffer.toFloat(),
            right = (clefBuffer + zoneWidth).toFloat(),
            top = staveHeight,
            bottom = 0f,
        )
    }
}
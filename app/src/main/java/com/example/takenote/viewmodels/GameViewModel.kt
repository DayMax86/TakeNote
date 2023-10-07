package com.example.takenote.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class GameViewModel (
    private val navController: NavHostController,
    screenWidth: Int,
    screenHeight: Int,
) : ViewModel() {

    private var difficultyWidthMultiplier = 1
    val staveHeight: Int = screenHeight/150*10
    var whiteKeyWidth: Int = screenWidth/150*7
    val clefBuffer: Int = screenWidth/12
    var zoneWidth: Int = whiteKeyWidth * difficultyWidthMultiplier //By default the hitbox width is the same as a key width

}
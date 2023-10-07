package com.example.takenote.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class GameViewModel (
    private val navController: NavHostController,
    screenWidth: Int
) : ViewModel() {

    var whiteNoteWidth: Int = screenWidth/150*7

}
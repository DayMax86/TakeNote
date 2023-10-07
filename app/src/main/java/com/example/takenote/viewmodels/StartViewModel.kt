package com.example.takenote.viewmodels

import android.util.DisplayMetrics
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.takenote.enums.ComposableDestinations

class StartViewModel(
    private val navController: NavHostController,
) : ViewModel() {



    fun handleSettingsButtonPress() {
        navController.navigate(ComposableDestinations.SETTINGS)
    }

    fun handlePlayButtonPress() {
        navController.navigate(ComposableDestinations.GAME)
    }

}
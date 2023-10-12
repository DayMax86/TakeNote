package com.example.takenote.activities

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.takenote.screens.DisplayGame
import com.example.takenote.screens.StartScreen
import com.example.takenote.ui.ui.TakeNoteTheme
import com.example.takenote.viewmodels.GameViewModel
import com.example.takenote.viewmodels.StartViewModel

class StartActivity : AppCompatActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val viewModel = remember { StartViewModel(navController) }
            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
            val showTopBar = rememberSaveable { mutableStateOf(true) }

            fun toggleTopBar() {
                showTopBar.value = !showTopBar.value
            }

            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)

            val screenWidth = displayMetrics.widthPixels
            val screenHeight = displayMetrics.heightPixels

            TakeNoteTheme() {
                supportActionBar?.hide()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        if (showTopBar.value) {
                            CenterAlignedTopAppBar(
                                modifier = Modifier.background(Color.Transparent),
                                title = { Text("My Title") },
                            )
                        }
                    }
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .padding(paddingValues)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screens.START,
                        ) {
                            composable(Screens.START) {
                                StartScreen(
                                    viewModel = viewModel,
                                    navController = navController,
                                    viewModel::handlePlayButtonPress,
                                    viewModel::handleSettingsButtonPress,
                                )
                            }
                            composable(Screens.GAME) {
                                val gameViewModel = remember {
                                    GameViewModel(
                                        navController,
                                        screenWidth,
                                        screenHeight,
                                    ) { toggleTopBar() }
                                }
                                DisplayGame(
                                    gameViewModel,
                                    gameViewModel.whiteKeyWidth,
                                    gameViewModel.clefBuffer,
                                    gameViewModel.zoneWidth,
                                    gameViewModel.staveHeight,
                                ) { toggleTopBar() }

                            }
                        }
                    }

                }
            }
        }

    }

    object Screens {
        const val START = "start"
        const val GAME = "game"
    }


}
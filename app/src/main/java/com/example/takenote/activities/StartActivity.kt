package com.example.takenote.activities

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
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

            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)

            val screenWidth = displayMetrics.widthPixels
            //var height = displayMetrics.heightPixels

            TakeNoteTheme() {
                supportActionBar?.hide()
                Scaffold(scaffoldState = scaffoldState, topBar = {
                    CenterAlignedTopAppBar(
                        modifier = Modifier.background(Color.Transparent),
                        title = { Text("My Title") },
                    )
                }) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxWidth(),
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
                                    )
                                }
                                DisplayGame(gameViewModel, gameViewModel.whiteNoteWidth)

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
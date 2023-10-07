package com.example.takenote.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.takenote.viewmodels.StartViewModel

@Composable
fun StartScreen(
    viewModel: StartViewModel,
    navController: NavController,
    playButtonPress: () -> Unit,
    settingsButtonPress: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Button(
            modifier = Modifier
                .padding(0.5f.dp),
            onClick = settingsButtonPress
        )
        {
            Text(text = "Settings")
        }

        Button(
            modifier = Modifier
                .padding(0.5f.dp),
            onClick = playButtonPress
        ) {
            Text(text = "Press to play!")
        }
    }
}
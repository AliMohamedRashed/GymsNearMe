package com.ali.gymsnearme.gyms.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ali.gymsnearme.gyms.presentation.gymslist.DefaultIcon
import com.ali.gymsnearme.gyms.presentation.gymslist.GymDetails

@Composable
fun GymsDetailsScreen(
    state: GymDetailsScreenState
){
    state.let {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            DefaultIcon(
                icon = Icons.Filled.Place,
                modifier = Modifier.padding(bottom = 32.dp, top = 32.dp),
                contentDescription = "Location Icon"
            )
            GymDetails(
                gym = it.gym,
                modifier = Modifier.padding(bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            Text(
                text = if ( it.gym.isOpen) "Gym is open" else "Gym is closed",
                color = if ( it.gym.isOpen) Color.Green else Color.Red,
            )
        }
    }
}
package com.ali.gymsnearme.gyms.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.gymsnearme.gyms.model.GetGymByIdUseCase
import com.ali.gymsnearme.gyms.model.Gym
import kotlinx.coroutines.launch

class GymsDetailsViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private var _state by mutableStateOf(
        GymDetailsScreenState(
            gym = Gym.empty,
        )
    )
    val state: State<GymDetailsScreenState>
        get() = derivedStateOf { _state }

    val getGymByIdUseCase = GetGymByIdUseCase()

    //var state = mutableStateOf<Gym?>(null)

    init {
        val gymId = savedStateHandle.get<Int>("gym_id")?:0
        getGym(gymId)
    }

    private fun getGym(id:Int){
        viewModelScope.launch{
            val gym = getGymByIdUseCase(id)
            _state.gym = gym
        }
    }
}
package com.ali.gymsnearme.gyms.presentation.gymslist

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.gymsnearme.gyms.model.GetInitialGymsUseCase
import com.ali.gymsnearme.gyms.model.ToggleFavouriteStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf


@HiltViewModel
class GymsViewModel @Inject constructor(
    private val getInitialGymsUseCase: GetInitialGymsUseCase,
    private val toggleFavouriteStateUseCase: ToggleFavouriteStateUseCase
): ViewModel(){

    private var _state by mutableStateOf(
        GymsScreenState(
            gyms = emptyList(),
            isLoading = true
        )
    )
    val state: State<GymsScreenState>
        get() = derivedStateOf { _state }

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _state = _state.copy(
            isLoading = false,
            error = throwable.message
        )
    }
    init {
        getGyms()
    }

    private fun getGyms() {
        viewModelScope.launch( errorHandler) {
            val receivedGyms = getInitialGymsUseCase()
            _state = _state.copy(
                gyms = receivedGyms,
                isLoading = false
            )
        }
    }

    fun toggleFavouriteState(gymId: Int, oldValue: Boolean){
        viewModelScope.launch {
            val updatedGymsList = toggleFavouriteStateUseCase(gymId, oldValue)
            _state = _state.copy(
                gyms = updatedGymsList
            )
        }
    }

}
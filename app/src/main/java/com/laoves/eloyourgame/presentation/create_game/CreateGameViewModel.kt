package com.laoves.eloyourgame.presentation.create_game

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laoves.eloyourgame.common.Resource
import com.laoves.eloyourgame.domain.model.elo_your_game.Game
import com.laoves.eloyourgame.domain.model.elo_your_game.GameCreate
import com.laoves.eloyourgame.domain.use_case.create_game.CreateGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CreateGameViewModel @Inject constructor(private val createGameUseCase: CreateGameUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(CreateGameState())
    val state: State<CreateGameState> = _state

    fun createGame(game: GameCreate) {
        createGameUseCase(game).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CreateGameState(game = result.data)
                }

                is Resource.Loading -> {
                    _state.value = CreateGameState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = CreateGameState(error = result.message ?: "Unknown error")
                }
            }
        }.launchIn(viewModelScope)
    }
}
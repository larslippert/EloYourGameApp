package com.laoves.eloyourgame.presentation.create_game

import android.util.Log
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
            println("RESULT: $result")
            when (result) {
                is Resource.Success -> {
                    when (result.code) {
                        200 -> {
                            _state.value = CreateGameState(gameAlreadyExists = true)
                        }
                        201 -> {
                            try {
                                val game = result.data as Game
                                _state.value = CreateGameState(game = game)
                            } catch (e: Exception) {
                                _state.value =
                                    CreateGameState(error = "Cannot convert to game: ${e.message}")
                            }
                        }
                    }
                }

                is Resource.Loading -> {
                    _state.value = CreateGameState(isLoading = true)
                }

                is Resource.Error -> {
                    Log.d("CREATE GAME ERROR", result.message.toString())
                    _state.value = CreateGameState(error = result.message ?: "Unknown error")
                }
            }
        }.launchIn(viewModelScope)
    }
}
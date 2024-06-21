package com.laoves.eloyourgame.presentation.select_game_type

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laoves.eloyourgame.common.Resource
import com.laoves.eloyourgame.domain.use_case.get_games.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SelectGameTypeViewModel @Inject constructor(private val getGamesUseCase: GetGamesUseCase) : ViewModel() {

    private val _state = mutableStateOf(SelectGameTypeState())
    val state: State<SelectGameTypeState> = _state

//    init {
//        Log.d("SELECT GAME TYPE", "Getting games...")
//        getGames()
//    }

    fun getGames() {
        getGamesUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = SelectGameTypeState(games = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = SelectGameTypeState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = SelectGameTypeState(error = result.message ?: "Unknown error")
                }
            }
        }.launchIn(viewModelScope)
    }
}
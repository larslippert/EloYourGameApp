package com.laoves.eloyourgame.presentation.select_game_type

import com.laoves.eloyourgame.domain.model.elo_your_game.Game

data class SelectGameTypeState(
    val isLoading: Boolean = false,
    val games: List<Game> = emptyList(),
    val error: String = ""
)
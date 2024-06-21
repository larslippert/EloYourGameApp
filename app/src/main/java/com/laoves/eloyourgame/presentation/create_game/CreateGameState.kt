package com.laoves.eloyourgame.presentation.create_game

import com.laoves.eloyourgame.domain.model.elo_your_game.Game
import com.laoves.eloyourgame.domain.model.elo_your_game.GameCreate

data class CreateGameState(
    val isLoading: Boolean = false,
    val game: Game? = null,
    val error: String? = null
)

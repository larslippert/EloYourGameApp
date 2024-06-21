package com.laoves.eloyourgame.data.remote.elo_your_game

import com.laoves.eloyourgame.domain.model.elo_your_game.Game

data class GameDto(
    val id: String,
    val name: String,
    val maxPlayersPerGame: Int,
    val keepScore: Boolean,
)

fun GameDto.toGame(): Game {
    return Game(
        id = id,
        name = name,
        maxPlayersPerGame = maxPlayersPerGame,
        keepScore = keepScore,
    )
}
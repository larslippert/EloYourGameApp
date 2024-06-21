package com.laoves.eloyourgame.domain.model.elo_your_game

data class Game(
    val id: String,
    val name: String,
    val maxPlayersPerGame: Int,
    val keepScore: Boolean,
)

data class GameCreate(
    val name: String,
    val maxPlayersPerGame: Int,
    val keepScore: Boolean,
)
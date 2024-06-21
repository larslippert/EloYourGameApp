package com.laoves.eloyourgame.data.repository

import com.laoves.eloyourgame.data.remote.elo_your_game.EloYourGameApi
import com.laoves.eloyourgame.data.remote.elo_your_game.GameDto
import com.laoves.eloyourgame.domain.model.elo_your_game.Game
import com.laoves.eloyourgame.domain.model.elo_your_game.GameCreate
import com.laoves.eloyourgame.domain.repository.EloYourGameRepository

class EloYourGameRepositoryImpl(private val api: EloYourGameApi) : EloYourGameRepository {
    override suspend fun getGames(): List<GameDto> {
        return api.getGames()
    }

    override suspend fun createGame(game: GameCreate): GameDto {
        return api.createGame(game)
    }
}
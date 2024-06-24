package com.laoves.eloyourgame.domain.repository

import com.laoves.eloyourgame.data.remote.elo_your_game.GameDto
import com.laoves.eloyourgame.domain.model.elo_your_game.Game
import com.laoves.eloyourgame.domain.model.elo_your_game.GameCreate
import okhttp3.ResponseBody
import retrofit2.Response

interface EloYourGameRepository {
    suspend fun getGames(): List<GameDto>
    suspend fun createGame(game: GameCreate): Response<ResponseBody>
}
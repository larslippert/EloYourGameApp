package com.laoves.eloyourgame.data.remote.elo_your_game

import com.laoves.eloyourgame.domain.model.elo_your_game.Game
import com.laoves.eloyourgame.domain.model.elo_your_game.GameCreate
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EloYourGameApi {

    @GET("/api/games")
    suspend fun getGames(): List<GameDto>

    @POST("/api/games/create")
    suspend fun createGame(
        @Body game: GameCreate
    ): Response<ResponseBody>
}
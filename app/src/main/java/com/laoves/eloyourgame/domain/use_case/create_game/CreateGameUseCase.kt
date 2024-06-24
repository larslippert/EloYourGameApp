package com.laoves.eloyourgame.domain.use_case.create_game

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import com.laoves.eloyourgame.common.Resource
import com.laoves.eloyourgame.data.remote.elo_your_game.GameDto
import com.laoves.eloyourgame.data.remote.elo_your_game.toGame
import com.laoves.eloyourgame.domain.model.elo_your_game.Game
import com.laoves.eloyourgame.domain.model.elo_your_game.GameCreate
import com.laoves.eloyourgame.domain.repository.EloYourGameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreateGameUseCase @Inject constructor(private val repository: EloYourGameRepository) {
    operator fun invoke(gameCreate: GameCreate): Flow<Resource<Any>> = flow {
        try {
            emit(Resource.Loading())
            val resp = repository.createGame(gameCreate)
            val data = try {
                if (resp.code() == 201) {
                    Gson().fromJson(resp.body()?.string(), GameDto::class.java).toGame()
                } else {
                    Any()
                }
            } catch (e: JsonSyntaxException) {
                // Handle JSON parsing error here
                Any() // or any default value/error object
            } catch (e: JsonIOException) {
                // Handle JSON IO error here
                Any() // or any default value/error object
            }
            emit(Resource.Success(data = data, code = resp.code()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown http error."))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown IO error."))
        }
    }
}
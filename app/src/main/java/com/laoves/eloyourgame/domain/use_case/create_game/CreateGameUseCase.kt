package com.laoves.eloyourgame.domain.use_case.create_game

import com.laoves.eloyourgame.common.Resource
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
    operator fun invoke(game: GameCreate): Flow<Resource<Game>> = flow {
        try {
            emit(Resource.Loading())
            val game = repository.createGame(game).toGame()
            emit(Resource.Success(game))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown http error."))
        } catch(e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown IO error."))
        }
    }
}
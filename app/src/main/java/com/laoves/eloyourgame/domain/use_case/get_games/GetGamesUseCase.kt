package com.laoves.eloyourgame.domain.use_case.get_games

import com.laoves.eloyourgame.common.Resource
import com.laoves.eloyourgame.data.remote.elo_your_game.toGame
import com.laoves.eloyourgame.domain.model.elo_your_game.Game
import com.laoves.eloyourgame.domain.repository.EloYourGameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(private val repository: EloYourGameRepository) {
    operator fun invoke(): Flow<Resource<List<Game>>> = flow {
        try {
            emit(Resource.Loading())
            val games = repository.getGames().map { it.toGame() }
            emit(Resource.Success(games))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown http error."))
        } catch(e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown IO error."))
        }
    }
}
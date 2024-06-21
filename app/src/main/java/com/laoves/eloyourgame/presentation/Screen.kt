package com.laoves.eloyourgame.presentation

sealed class Screen(val route: String) {
    object SelectGameTypeScreen: Screen("select_game_type_screen")
    object CreateGameScreen: Screen("create_game_screen")
}
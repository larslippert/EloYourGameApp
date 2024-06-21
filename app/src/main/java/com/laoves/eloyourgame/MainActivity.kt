package com.laoves.eloyourgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.laoves.eloyourgame.presentation.Screen
import com.laoves.eloyourgame.presentation.create_game.CreateGameScreen
import com.laoves.eloyourgame.presentation.select_game_type.SelectGameTypeScreen
import com.laoves.eloyourgame.presentation.ui.theme.EloYourGameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EloYourGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.SelectGameTypeScreen.route
                    ) {
                        composable(
                            route = Screen.SelectGameTypeScreen.route
                        ) {
                            SelectGameTypeScreen(navController = navController)
                        }
                        composable(
                            route = Screen.CreateGameScreen.route
                        ) {
                            CreateGameScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

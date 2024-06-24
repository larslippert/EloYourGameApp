package com.laoves.eloyourgame.presentation.create_game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.laoves.eloyourgame.R
import com.laoves.eloyourgame.domain.model.elo_your_game.Game
import com.laoves.eloyourgame.domain.model.elo_your_game.GameCreate
import com.laoves.eloyourgame.presentation.components.AppBar
import com.laoves.eloyourgame.presentation.components.CustomSwitch
import com.laoves.eloyourgame.presentation.components.NumberInput
import com.laoves.eloyourgame.presentation.components.PrimaryButton
import com.laoves.eloyourgame.presentation.components.TextInput
import com.laoves.eloyourgame.presentation.components.snackbar.CustomSnackBar
import com.laoves.eloyourgame.presentation.select_game_type.SelectGameTypeViewModel
import com.laoves.eloyourgame.presentation.ui.theme.lightBlue
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun CreateGameScreen(
    navController: NavController,
    viewModel: CreateGameViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val interactionSource = remember { MutableInteractionSource() }
    val snackState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var hideKeyboard by remember {
        mutableStateOf(false)
    }

    var gameName by remember {
        mutableStateOf("")
    }
    var maxPlayersPerTeam by remember {
        mutableIntStateOf(2)
    }
    var keepScore by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                hideKeyboard = true
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {
            AppBar(
                title = stringResource(R.string.create_game).uppercase(),
                onBack = { navController.navigateUp() },
                onClose = { navController.navigateUp() }
            )
            if (state.isLoading) {
                Text("Loading...")
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(R.string.game_name),
                            fontSize = 14.sp,
                            color = lightBlue
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextInput(
                            initialText = gameName,
                            hintText = stringResource(R.string.game_name).uppercase(),
                            hideKeyboard = hideKeyboard,
                            onFocusClear = {
                                hideKeyboard = false
                            },
                            onTextChanged = { name ->
                                gameName = name
                            },
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Max players per team",
                                fontSize = 14.sp,
                                color = lightBlue
                            )
                            NumberInput(
                                initialNumber = maxPlayersPerTeam,
                                onNumberChange = {
                                    maxPlayersPerTeam = it
                                },
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Points",
                                fontSize = 14.sp,
                                color = lightBlue
                            )
                            CustomSwitch(
                                checked = keepScore,
                                onCheckedChange = {
                                    keepScore = it
                                    hideKeyboard = true
                                },
                            )
                        }
                    }
                    Box(modifier = Modifier.padding(bottom = 32.dp)) {
                        PrimaryButton(
                            text = stringResource(R.string.create_game).uppercase(),
                            onClick = {
                                val game = GameCreate(
                                    name = gameName,
                                    maxPlayersPerGame = maxPlayersPerTeam,
                                    keepScore = keepScore,
                                )
                                viewModel.createGame(game)

                                if (state.game != null) navController.navigateUp()
                                else if (state.gameAlreadyExists) {
                                    coroutineScope.launch {
                                        snackState.currentSnackbarData?.dismiss()
                                        snackState.showSnackbar(
                                            message = "The name \"${game.name}\" already exists.",
                                        )
                                    }
                                } else if (!state.error.isNullOrBlank()) {
                                    coroutineScope.launch {
                                        snackState.currentSnackbarData?.dismiss()
                                        snackState.showSnackbar(
                                            message = state.error,
                                        )
                                    }
                                } else {
                                    coroutineScope.launch {
                                        snackState.currentSnackbarData?.dismiss()
                                        snackState.showSnackbar(
                                            message = "An unexpected error occurred.",
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
        SnackbarHost(
            modifier = Modifier.align(Alignment.TopCenter),
            hostState = snackState
        ) { snackbarData: SnackbarData ->
            CustomSnackBar(
                message = snackbarData.visuals.message,
                onDismiss = { snackbarData.dismiss() }
            )
        }
    }
}
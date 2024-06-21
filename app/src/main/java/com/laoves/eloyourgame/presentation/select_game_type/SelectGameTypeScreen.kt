package com.laoves.eloyourgame.presentation.select_game_type

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.laoves.eloyourgame.R
import com.laoves.eloyourgame.presentation.Screen
import com.laoves.eloyourgame.presentation.components.PrimaryButton
import com.laoves.eloyourgame.presentation.components.SecondaryButton
import com.laoves.eloyourgame.presentation.ui.theme.backgroundBlack
import com.laoves.eloyourgame.presentation.ui.theme.darkBlue
import com.laoves.eloyourgame.presentation.ui.theme.darkTurquoise
import com.laoves.eloyourgame.presentation.ui.theme.lightBlue
import com.laoves.eloyourgame.presentation.ui.theme.lightTurquoise
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectGameTypeScreen(
    navController: NavController,
    viewModel: SelectGameTypeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val buttonHeight = 50.dp

    var showGameTypes by remember {
        mutableStateOf(false)
    }

    // Retrieve games every time this screen is launched
    LaunchedEffect(Unit) {
        viewModel.getGames()
    }

    // Animate game types entrance
    LaunchedEffect(state) {
        if (state.games.isNotEmpty()) showGameTypes = true
        if (state.isLoading) showGameTypes = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        if (state.error.isNotBlank()) {
            Text("Der skete en fejl. ${state.error}")
        } else if (state.isLoading) {
            Text("Loading...")
        } else if (state.games.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Spacer(modifier = Modifier.height(64.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            stringResource(R.string.select_game),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(lightBlue, darkBlue)
                                ),
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(128.dp))
                    Box(
                        modifier = Modifier.height(((buttonHeight.value + 16) * 5 - 16).dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Box(
                            modifier = Modifier.height(((buttonHeight.value + 16) * 5 - 16).dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Column {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(((buttonHeight.value + 16) * 5 - 16).dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    item {
                                        Spacer(modifier = Modifier.height((buttonHeight.value * 0.4).dp))
                                    }
                                    itemsIndexed(state.games) { index, game ->
                                        AnimatedVisibility(
                                            visible = showGameTypes, enter = slideInHorizontally(
                                                animationSpec = spring(
                                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                                    stiffness = Spring.StiffnessMedium,
                                                ),
                                                initialOffsetX = { if (index % 2 == 0) -it / 2 else it / 2 }
                                            )
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .animateItemPlacement(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                PrimaryButton(
                                                    text = game.name.uppercase(Locale.ROOT),
                                                    height = buttonHeight,
                                                    onClick = {
                                                        println("Clicked on ${game.name}")
                                                    }
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(16.dp))
                                    }
                                    item {
                                        Spacer(modifier = Modifier.height((buttonHeight.value * 0.4).dp))
                                    }
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height((buttonHeight.value * 0.4).dp)
                                    .background(
                                        Brush.verticalGradient(
                                            listOf(
                                                Color.Transparent,
                                                backgroundBlack
                                            )
                                        )
                                    )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height((buttonHeight.value * 0.4).dp)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            backgroundBlack,
                                            Color.Transparent
                                        )
                                    )
                                )
                        )
                    }
                }
                Box(modifier = Modifier.padding(bottom = 32.dp)) {
                    SecondaryButton(
                        iconId = R.drawable.add,
                        text = stringResource(R.string.add_game),
                        onClick = {
                            navController.navigate(Screen.CreateGameScreen.route)
                        }
                    )
                }
            }
        }
    }
}
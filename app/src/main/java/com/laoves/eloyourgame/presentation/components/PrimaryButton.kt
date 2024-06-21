package com.laoves.eloyourgame.presentation.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.laoves.eloyourgame.presentation.ui.theme.backgroundBlack
import com.laoves.eloyourgame.presentation.ui.theme.darkBlue
import com.laoves.eloyourgame.presentation.ui.theme.darkTurquoise
import com.laoves.eloyourgame.presentation.ui.theme.lightBlue
import com.laoves.eloyourgame.presentation.ui.theme.lightTurquoise

@Composable
fun PrimaryButton(
    text: String,
    brush: Brush = Brush.verticalGradient(
        startY = 75f,
        colors = listOf(lightTurquoise, darkTurquoise),
    ),
    textColor: Color = backgroundBlack,
    width: Dp = 250.dp,
    height: Dp = 50.dp,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(50.dp))
            .width(width)
            .height(height)
            .background(brush)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            color = textColor,
        )
    }
}
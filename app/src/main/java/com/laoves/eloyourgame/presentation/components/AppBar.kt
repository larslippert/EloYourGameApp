package com.laoves.eloyourgame.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.laoves.eloyourgame.R
import com.laoves.eloyourgame.presentation.ui.theme.darkBlue
import com.laoves.eloyourgame.presentation.ui.theme.lightBlue

@Composable
fun AppBar(
    title: String,
    brush: Brush = Brush.verticalGradient(listOf(lightBlue, darkBlue)),
    color: Color = darkBlue,
    height: Dp = 50.dp,
    onBack: () -> Unit,
    onClose: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 8.dp)
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(brush, blendMode = BlendMode.SrcAtop)
                    }
                }
                .size(24.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onBack()
                },
        )
        Text(text = title, fontSize = 16.sp, color = color)
        Icon(
            painter = painterResource(id = R.drawable.close),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(brush, blendMode = BlendMode.SrcAtop)
                    }
                }
                .size(24.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onClose()
                },
        )
    }
}
package com.laoves.eloyourgame.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.laoves.eloyourgame.presentation.ui.theme.backgroundBlack
import com.laoves.eloyourgame.presentation.ui.theme.lightTurquoise

@Composable
fun SecondaryButton(
    iconId: Int,
    text: String,
    color: Color = lightTurquoise,
    background: Color = backgroundBlack,
    width: Dp = 250.dp,
    height: Dp = 50.dp,
    shape: Shape = RoundedCornerShape(50.dp),
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(shape = shape)
            .border(1.dp, color = color, shape = shape)
            .width(width)
            .height(height)
            .background(background)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = color,
                modifier = Modifier
                    .size(16.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text,
                color = color
            )
        }
    }
}
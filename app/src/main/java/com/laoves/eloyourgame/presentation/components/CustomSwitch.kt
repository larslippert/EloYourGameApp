package com.laoves.eloyourgame.presentation.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.laoves.eloyourgame.R
import com.laoves.eloyourgame.presentation.ui.theme.backgroundBlack
import com.laoves.eloyourgame.presentation.ui.theme.darkBlue
import com.laoves.eloyourgame.presentation.ui.theme.lightBlue

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    switchWidth: Dp = 100.dp,
    switchHeight: Dp = 50.dp,
    borderRadius: Dp = 2.dp,
    switchPadding: Dp = 4.dp,
    iconPadding: Dp = 16.dp,
    thumbRadius: Dp = switchHeight / 2 - (switchPadding * 2),
    borderBrush: Brush = Brush.verticalGradient(listOf(lightBlue, darkBlue)),
    background: Color = backgroundBlack,
    iconChecked: Int = R.drawable.add,
    iconUnchecked: Int = R.drawable.close,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val animatedPosition = animateFloatAsState(
        targetValue =
        if (checked) (switchWidth - thumbRadius * 2 - switchPadding * 2).value
        else switchPadding.value * 2,
        label = "switch-position"
    )

    Box(
        modifier = modifier
            .width(switchWidth)
            .height(switchHeight)
            .clip(RoundedCornerShape(switchHeight / 2))
            .border(borderRadius, borderBrush, RoundedCornerShape(switchHeight / 2))
            .background(background)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onCheckedChange(!checked) },
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(thumbRadius * 2)
                .offset(x = animatedPosition.value.dp)
                .clip(RoundedCornerShape(thumbRadius))
                .background(borderBrush),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = if (checked) iconChecked else iconUnchecked),
                contentDescription = null,
                tint = backgroundBlack,
                modifier = Modifier
                    .padding(switchPadding)
                    .size(thumbRadius * 2 - iconPadding)
            )
        }
    }
}
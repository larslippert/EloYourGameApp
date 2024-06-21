package com.laoves.eloyourgame.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.laoves.eloyourgame.R
import com.laoves.eloyourgame.presentation.ui.theme.backgroundBlack
import com.laoves.eloyourgame.presentation.ui.theme.darkBlue
import com.laoves.eloyourgame.presentation.ui.theme.lightBlue

@Composable
fun NumberInput(
    initialNumber: Int,
    fontSize: TextUnit = 18.sp,
    brush: Brush = Brush.verticalGradient(listOf(lightBlue, darkBlue)),
    width: Dp = 150.dp,
    height: Dp = 50.dp,
    onNumberChange: (Int) -> Unit,
) {
    val shape = RoundedCornerShape(50.dp)
    var number by remember {
        mutableIntStateOf(initialNumber)
    }

    Box(
        modifier = Modifier
            .clip(shape = shape)
            .width(width)
            .height(height)
            .background(backgroundBlack)
            .border(width = 2.dp, brush = brush, shape = shape)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.padding(4.dp),
                enabled = number > 1,
                onClick = {
                    if (number > 1) {
                        number--
                        onNumberChange(number)
                    }
                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .graphicsLayer(alpha = 0.99f)
                        .drawWithCache {
                            onDrawWithContent {
                                drawContent()
                                drawRect(brush, blendMode = BlendMode.SrcAtop)
                            }
                        },
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = null,
                )
            }
            Text(
                text = number.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    brush = brush,
                    fontSize = fontSize,
                )
            )
            IconButton(
                modifier = Modifier.padding(4.dp),
                enabled = number < 11,
                onClick = {
                    if (number < 11) {
                        number++
                        onNumberChange(number)
                    }
                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .graphicsLayer(alpha = 0.99f)
                        .drawWithCache {
                            onDrawWithContent {
                                drawContent()
                                drawRect(brush, blendMode = BlendMode.SrcAtop)
                            }
                        },
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = null,
                )
            }
        }
    }
}
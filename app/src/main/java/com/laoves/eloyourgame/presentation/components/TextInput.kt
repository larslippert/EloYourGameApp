package com.laoves.eloyourgame.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.laoves.eloyourgame.presentation.ui.theme.backgroundBlack
import com.laoves.eloyourgame.presentation.ui.theme.darkBlue
import com.laoves.eloyourgame.presentation.ui.theme.lightBlue
import com.laoves.eloyourgame.presentation.ui.theme.lightGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
    initialText: String = "",
    hintText: String = "",
    fontSize: TextUnit = 18.sp,
    brush: Brush = Brush.verticalGradient(listOf(lightBlue, darkBlue)),
    hideKeyboard: Boolean = false,
    onFocusClear: () -> Unit,
    onTextChanged: (String) -> Unit,
) {
    val shape = RoundedCornerShape(50.dp)
    var text by remember {
        mutableStateOf(initialText)
    }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(hideKeyboard) {
        if (hideKeyboard) {
            focusManager.clearFocus()
            onFocusClear()
        }
    }

    Box(
        modifier = Modifier
            .clip(shape = shape)
            .fillMaxWidth()
            .background(backgroundBlack)
            .border(width = 2.dp, brush = brush, shape = shape)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            value = text,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = fontSize,
                color = darkBlue
            ),
            placeholder = { Text(text = hintText, fontSize = fontSize, color = lightGrey) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                onFocusClear()
            }),
            onValueChange = {
                text = it
                onTextChanged(text)
            }
        )
    }
}
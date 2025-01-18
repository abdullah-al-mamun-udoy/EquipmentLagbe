package com.metamamun.equipment.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun AnnotadCustomText(
    text: Any = "",
    color: Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 14.sp,
    maxLines: Int = Int.MAX_VALUE,
    overFlow: TextOverflow = TextOverflow.Clip,
    style: TextStyle = MaterialTheme.typography.displaySmall,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    when (text) {
        is String -> {
            Text(
                text = text,
                color = color,
                textAlign = textAlign,
                maxLines = maxLines,
                overflow = overFlow,
                modifier = modifier,
                style = style,
                fontWeight = fontWeight
            )
        }
        is AnnotatedString -> {
            Text(
                text = text,
                color = color,
                textAlign = textAlign,
                maxLines = maxLines,
                overflow = overFlow,
                modifier = modifier,
                style = style
            )
        }
        else -> {
            throw IllegalArgumentException("Unsupported text type: Only String and AnnotatedString are supported.")
        }
    }
}

package com.metamamun.equipment.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun CustomText(
    text: String = "",
    color: Color = MaterialTheme.colorScheme.tertiary,
//    lineHeight: TextUnit = 16.sp,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal,
//    fontSize: TextUnit = 14.sp,
    maxLines: Int = Int.MAX_VALUE,
    overFlow: TextOverflow = TextOverflow.Clip,
    style: TextStyle = MaterialTheme.typography.displaySmall,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overFlow,
        modifier = modifier,
//        fontSize = fontSize,
        style = style,
        fontWeight = fontWeight
    )
}
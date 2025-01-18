package com.metamamun.equipment.presentation.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.metamamun.equipment.R
import com.metamamun.equipment.ui.theme.White

@Preview(showBackground = false)
@Composable
fun CustomButton(
    buttonText: String = "Button",
    fontSize: TextUnit = 24.sp,
    modifier: Modifier = Modifier.fillMaxWidth(),
    @DrawableRes iconId: Int = R.drawable.ic_home,
    style: TextStyle = MaterialTheme.typography.titleLarge,
    buttonTextColor: Color? = null,
    showIcon: Boolean = true,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center)
        ) {
//            if (showIcon) {
//                CustomImage(
//                    imageId = iconId,
//                    contentDescription = "Button Icon",
//                    modifier = Modifier
//                        .size(24.dp)
//                        .align(Alignment.CenterVertically)
//                    ,
//                    contentScale = ContentScale.Crop
//                )
//            }
//            Spacer(modifier = Modifier.size(10.dp))
            CustomText(
                text = buttonText,
                color = buttonTextColor ?: White,
                style = style,
                modifier = Modifier
                    .padding(1.dp)
                    .align(Alignment.CenterVertically)
            )

        }
    }
}

@Preview(showBackground = false)
@Composable
fun HomeCustomButton(
    buttonText: String = "Button",
    fontSize: TextUnit = 6.sp, // Smaller text size for smaller button
    modifier: Modifier,
    buttonTextColor: Color? = null,
    onClick: () -> Unit = {},
    style: TextStyle
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(
                text = buttonText,
                color = buttonTextColor ?: White,
                style = style.copy(fontSize = fontSize),
                modifier = Modifier
                    .padding(0.dp) // Minimal padding
                    .align(Alignment.CenterVertically)
            )
        }
    }
}
package com.metamamun.equipment.presentation.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun CustomImage(
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null
) {
    Image(
        painterResource(id = imageId),
        modifier = modifier,
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}

@Composable
fun CustomImageTint(
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null,
    tint: Color
) {
    Image(
        painterResource(id = imageId),
        modifier = modifier,
        contentScale = contentScale,
        contentDescription = contentDescription,
        colorFilter = ColorFilter.tint(tint)
    )
}

package com.metamamun.equipment.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.metamamun.equipment.R

@Composable
fun CustomImageAsync(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    size: Int = 512,
    contentDescription: String? = null,
//    fallbackDrawableResId: Int = R.drawable.ic_shimmer_carousal
) {
    val context = LocalContext.current
    val imageData = if (imageUrl.isBlank()) null else imageUrl
    val imageRequest = ImageRequest.Builder(context)
        .data(imageData)
        .memoryCacheKey(imageData)
        .diskCacheKey(imageData)
//        .placeholder(placeholder)
//        .fallback(placeholder)
//        .fallback(fallbackDrawableResId)
        .error(R.drawable.ic_launcher_background)
        .size(size)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    Box {
        Image(
            painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = ContentScale.Crop
        )

        AsyncImage(
            model = imageRequest,
            modifier = modifier,
            contentScale = ContentScale.Crop,
            contentDescription = contentDescription,
        )
    }
}
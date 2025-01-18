package com.metamamun.equipment.presentation.screen.livetv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.metamamun.equipment.presentation.composables.CustomText

@Composable
fun LiveTvScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = androidx.compose.ui.graphics.Color(0xFFC0C0C0)
            )
    ){
        CustomText(
            text = "Live TV",
            color = androidx.compose.ui.graphics.Color(0xFFC43535)
        )
    }
}
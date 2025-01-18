package com.metamamun.equipment.presentation.screen.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.metamamun.equipment.R
import com.metamamun.equipment.navigation.Graph
import com.metamamun.equipment.navigation.Screen
import com.metamamun.equipment.presentation.composables.CustomImage
import com.metamamun.equipment.ui.theme.padding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun OnboardingPager(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    navController: NavHostController?
) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = true,
        modifier = Modifier.height(440.dp)
    ) { page ->
        when (page) {
            0 -> ScreenOneContent(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                }
            )
            1 -> ScreenTwoContent(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(2)
                    }
                }
            )
            2 -> ScreenThreeContent(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(3)
                    }
                }
            )
            3 -> ScreenFourContent(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(4)
                    }
                }
            )
            4-> ScreenFiveContent(
                onClick = {
                    navController?.navigate(Graph.HomeGraph) {
                        popUpTo(Screen.OnboardingScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun ScreenOneContent(
    onClick: () -> Unit = {}
){
    val color = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(padding.large)
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        CustomImage(imageId = R.drawable.ic_sp1,
            contentDescription = "Onboarding Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds)
    }
}

@Preview
@Composable
fun ScreenTwoContent(
    onClick: () -> Unit = {}
){
    val color = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(padding.large)
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        CustomImage(imageId = R.drawable.ic_sp2,
            contentDescription = "Onboarding Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
fun ScreenThreeContent(
    onClick: () -> Unit = {}
){
    val color = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(padding.large)
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        CustomImage(imageId = R.drawable.ic_sp3,
            contentDescription = "Onboarding Image",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillBounds)
    }
}
@Preview
@Composable
fun ScreenFourContent(
    onClick: () -> Unit = {}
){
    val color = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(padding.large)
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        CustomImage(imageId = R.drawable.ic_sp_4,
            contentDescription = "Onboarding Image",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillBounds)
    }
}
@Preview
@Composable
fun ScreenFiveContent(
    onClick: () -> Unit = {}
){
    val color = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(padding.large)
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        CustomImage(imageId = R.drawable.ic_sp5,
            contentDescription = "Onboarding Image",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillBounds)
    }
}
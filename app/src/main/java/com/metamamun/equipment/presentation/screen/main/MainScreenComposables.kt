package com.metamamun.equipment.presentation.screen.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.metamamun.equipment.R
import com.metamamun.equipment.navigation.BottomNavigation
import com.metamamun.equipment.navigation.MainNavGraph
import com.metamamun.equipment.navigation.Screen
import com.metamamun.equipment.preference.SessionPreference
import com.metamamun.equipment.presentation.composables.CustomImage
import com.metamamun.equipment.presentation.composables.CustomImageAsync
import com.metamamun.equipment.presentation.composables.CustomImageTint
import com.metamamun.equipment.presentation.composables.CustomText
import com.metamamun.equipment.ui.theme.padding

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainScreenBody(
    currentPageState: MutableState<String>,
    navController: NavHostController,
    title: String,
    screenPath: String,
    preference: SessionPreference
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        AnimatedVisibility(
            visible = currentPageState.value == "$screenPath${Screen.HomeScreen}"
                    || currentPageState.value == "$screenPath${Screen.VideosScreen}"
                    || currentPageState.value == "$screenPath${Screen.MyPlanScreen}"

        )
        {
            TopAppBar(
                navController = navController,
                preference = preference
            )
        }
        AnimatedVisibility(
            visible = currentPageState.value == "${screenPath}${Screen.MenuScreen}"
                    || currentPageState.value == "${screenPath}${Screen.EditProfileScreen}"
                    || currentPageState.value == "${screenPath}${Screen.TermsAndConditionsScreen}"
                    || currentPageState.value == "${screenPath}${Screen.PrivacyPolicyScreen}"
                    || currentPageState.value == "${screenPath}OrderSummeryScreen/{id}/{name}"
                    || currentPageState.value == "$screenPath${Screen.SearchScreen}"
                    || currentPageState.value == "${screenPath}PlanDetailsScreen/{item}"
        )
        {
            TopAppBarTwo(
                title = title,
                navController = navController,
                preference = preference
            )
        }
        MainNavGraph(
            navController = navController,
            preference = preference
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBar(
    navController: NavHostController? = null,
    preference: SessionPreference? = null
){
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.medium)
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                CustomImage(
                    imageId = R.drawable.ic_logo,
                    contentDescription = "logo",
                    modifier = Modifier
                        .height(padding.largeMedium)
                        .padding(padding.doubleExtraSmall)
                )
                CustomText(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(id = R.color.app_color),
                    modifier = Modifier
                        .padding(padding.small)
                )
            }
            Row(
                modifier = Modifier
                    .width(padding.doubleExtraLarge)
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomImage(
                    imageId = R.drawable.ic_notification,
                    contentDescription = "notification",
                    modifier = Modifier
                        .height(padding.mediumLarge)
                )
                Box(
                    modifier = Modifier
                        .size(padding.mediumLarge)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .border(
                            padding.lightSmall,
                            MaterialTheme.colorScheme.onTertiary,
                            CircleShape
                        )
                ){
                    CustomImageAsync(
                        imageUrl = preference?.subscriberPhoto ?: "",
                        contentDescription = "profile",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                navController?.navigate(Screen.MenuScreen)
                            }
                    )
//                CustomImage(
//                    imageId  = R.drawable.profile,
//                    contentDescription = "profile",
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .clickable {
//                            navController?.navigate(Screen.MenuScreen)
//                        }
//                )
                }
//                CustomImageAsync(
//                    imageUrl = preference?.subscriberPhoto ?: "",
//                    contentDescription = "profile",
//                    modifier = Modifier
//                        .height(padding.mediumLarge)
//                        .clip(CircleShape)
//                        .clickable {
//                            navController?.navigate(Screen.MenuScreen)
//                        }
//                )
//                CustomImage(
//                    imageId = R.drawable.ic_profile,
//                    contentDescription = "profile",
//                    modifier = Modifier
//                        .height(padding.mediumLarge)
//                        .clip(CircleShape)
//                        .clickable {
//                            navController?.navigate(Screen.MenuScreen)
//                        }
//                )
            }
        }
        Box(modifier = Modifier
            .height(padding.lightSmall)
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .background(colorResource(R.color.app_color).copy(alpha = 0.2f))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBarTwo(
    title: String? = "",
    navController: NavHostController? = null,
    preference: SessionPreference? = null
){
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.medium)
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomImage(
                    imageId = R.drawable.ic_back,
                    contentDescription = "back",
                    modifier = Modifier
                        .height(padding.large)
                        .padding(padding.doubleExtraSmall)
                        .clickable { navController?.popBackStack() }
                )
                CustomText(
                    text = title?: "",
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(id = R.color.app_color),
                    modifier = Modifier
                        .padding(padding.small)
                )
            }
            Box(
                modifier = Modifier
                    .size(padding.mediumLarge)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .border(
                        padding.lightSmall,
                        MaterialTheme.colorScheme.onTertiary,
                        CircleShape
                    )
            ){
                CustomImageAsync(
                    imageUrl = preference?.subscriberPhoto ?: "",
                    contentDescription = "profile",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            navController?.navigate(Screen.MenuScreen)
                        }
                )
//                CustomImage(
//                    imageId  = R.drawable.profile,
//                    contentDescription = "profile",
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .clickable {
//                            navController?.navigate(Screen.MenuScreen)
//                        }
//                )
            }
        }
        Box(modifier = Modifier
            .height(padding.lightSmall)
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .background(colorResource(R.color.app_color).copy(alpha = 0.2f))
        )
    }
}

@Composable
fun BottomAppBar(navController: NavHostController? = null) {
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(initialOffsetY = { it / 2 }),
        exit = slideOutVertically(targetOffsetY = { it / 2 })
    ) {
        if (navController != null) {
            StandardBottomAppBar(navController = navController)
        }
    }
}

@Preview
@Composable
fun StandardBottomAppBar(navController: NavController? = null) {
    var selectedItemIndex by remember { mutableStateOf(0) } // Track selected index

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadow(4.dp)
            .background(colorResource(id = R.color.white))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavigation.entries.forEachIndexed { index, entry ->  // Use forEachIndexed for index tracking
                if (entry.icon != 0 && entry.route != null) {
                    Column(
                        modifier = Modifier
                            .size(padding.extraLarge)
                            .clip(CircleShape)
                            .clickable {
                                selectedItemIndex = index
                                navController?.popBackStack(Screen.MenuScreen, true)
                                navController?.navigate(entry.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CustomImageTint(
                            imageId = entry.icon,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = if (selectedItemIndex == index)
                                colorResource(R.color.app_color)  // Selected color
                            else
                                colorResource(R.color.black)  // Default color
                        )

                        Spacer(modifier = Modifier.height(padding.extraSmall))
                        CustomText(
                            text = entry.title,
                            color = if (selectedItemIndex == index)
                                colorResource(R.color.app_color)
                            else
                                colorResource(R.color.black),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                } else {
                    CustomText(
                        text = entry.title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = padding.doubleLargeMedium)
                            .width(padding.doubleExtraLarge),
                        color = colorResource(id = R.color.app_color),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

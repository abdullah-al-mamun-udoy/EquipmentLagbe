package com.metamamun.equipment.presentation.screen.main

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.metamamun.equipment.navigation.Screen
import com.metamamun.equipment.preference.SessionPreference


@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController,
    preference: SessionPreference,
) {
    val context = LocalContext.current
    val activity = context as Activity
//    val scaffoldState = rememberScaffoldState()

    // Remember a SystemUiController
//    val systemUiController = rememberSystemUiController()
    val screenPath = "com.lazydeveloper.fit4u2.navigation.Screen."
    val currentPageState = remember {
        mutableStateOf(Screen.HomeScreen.toString())
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        currentPageState.value = destination.route.toString()

    }

    Log.d("currentPage", "currentPage: ${currentPageState.value}")

    val title = when (currentPageState.value) {
        "${screenPath}${Screen.PrivacyPolicyScreen}" -> "Privacy Policy"
        "${screenPath}${Screen.TermsAndConditionsScreen}" -> "Terms & Conditions"
        "${screenPath}${Screen.MenuScreen}" -> "Menu"
        "${screenPath}${Screen.EditProfileScreen}" -> "Edit Profile"
        "${screenPath}OrderSummeryScreen/{id}/{name}" -> "My Plan"
        "${screenPath}${Screen.SearchScreen}" -> "Search"
        "${screenPath}PlanDetailsScreen/{item}" -> "Plan Details"
        else -> ""
    }

    Scaffold(
        bottomBar = { BottomAppBar(navController = navController) }
    ) {
        MainScreenBody(
            title = title,
            screenPath = screenPath,
            currentPageState = currentPageState,
            navController = navController,
            preference = preference
        )
    }
}



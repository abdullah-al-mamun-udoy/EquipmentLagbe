package com.metamamun.equipment.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.metamamun.equipment.preference.SessionPreference
import com.metamamun.equipment.presentation.screen.main.MainScreen
import com.metamamun.equipment.presentation.screen.onboarding.OnboardingScreen
import com.metamamun.equipment.presentation.screen.signup.SignUpScreen
import com.metamamun.equipment.presentation.screen.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    preference: SessionPreference,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.SplashScreen,
//        enterTransition = {
//            fadeIn(tween(300))
//        },
//        exitTransition = {
//            fadeOut(tween(300))
//        },
//        popEnterTransition = {
//            fadeIn(tween(300))
//        },
//        popExitTransition = {
//            fadeOut(tween(300))
//        }
    ){
        composable<Screen.SplashScreen> {
            SplashScreen(
                navController = navHostController,
                preference = preference,
            )
        }
        composable<Screen.OnboardingScreen> {
            OnboardingScreen(
                navController = navHostController,
                preference = preference,
            )
        }
        composable<Screen.SignUpScreen> {
            SignUpScreen(
                navController = navHostController,
                preference = preference,
            )
        }
        composable<Graph.HomeGraph> {
            MainScreen(
                navController = rememberNavController(),
                preference = preference,
            )
        }
    }

}
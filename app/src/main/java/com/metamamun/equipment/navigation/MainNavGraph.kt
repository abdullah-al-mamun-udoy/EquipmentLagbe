package com.metamamun.equipment.navigation

import HomeScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.metamamun.equipment.preference.SessionPreference
import com.metamamun.equipment.presentation.screen.privacy.PrivacyPolicyScreen
import com.metamamun.equipment.presentation.screen.livetv.LiveTvScreen
import com.metamamun.equipment.presentation.screen.menu.MenuScreen
//import com.lazydeveloper.fit4u2.presentation.screen.home.HomeScreen
import com.metamamun.equipment.presentation.screen.myplan.MyPlanScreen
import com.metamamun.equipment.presentation.screen.order_summery.OrderSummeryScreen
import com.metamamun.equipment.presentation.screen.menu.EditProfileScreen
import com.metamamun.equipment.presentation.screen.plandetails.PlanDetailsScreen
import com.metamamun.equipment.presentation.screen.search.SearchScreen
import com.metamamun.equipment.presentation.screen.signin.SignInScreen
import com.metamamun.equipment.presentation.screen.terms.TermsAndConditionsScreen
import com.metamamun.equipment.presentation.screen.videos.VideosScreen

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    preference: SessionPreference
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen,
        modifier = modifier,
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
    ) {
        composable<Screen.SignInScreen> {
            SignInScreen(navController = navController)
        }
        composable<Screen.HomeScreen> {
            HomeScreen()
        }
        composable<Screen.MyPlanScreen> {
            MyPlanScreen(navController = navController)
        }
//        composable(
//            route = "PlanDetailsScreen/{item}",
//            arguments = listOf(navArgument("item") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val json = backStackEntry.arguments?.getString("item")
//            val planDataDTO = json?.let { Json.decodeFromString<PlanDataDTO>(it) }
//            PlanDetailsScreen(planDataDTO = planDataDTO)
//        }
        composable<Screen.PlanDetailsScreen> {
            val arg = it.toRoute<Screen.PlanDetailsScreen>()
            PlanDetailsScreen(navController = navController, arg = arg)
        }

        composable<Screen.VideosScreen> {
            VideosScreen()
        }
        composable<Screen.LiveTvScreen> {
            LiveTvScreen()
        }
        composable<Screen.MyPlanScreen> {
            MyPlanScreen(navController = navController)
        }
        composable<Screen.SearchScreen> {
            SearchScreen()
        }
        composable<Screen.MenuScreen> {
            MenuScreen(
                navController = navController,
                preference = preference
            )
        }
        composable<Screen.EditProfileScreen> {
            EditProfileScreen(navController = navController)
        }
        composable<Screen.PrivacyPolicyScreen> {
            PrivacyPolicyScreen(
                navController = navController,
                preference = preference
            )
        }
        composable<Screen.TermsAndConditionsScreen> {
            TermsAndConditionsScreen(
                navController = navController,
                preference = preference
            )
        }
//        composable<Screen.SignUpScreen> {
//            SignUpScreen(navController = navController)
//        }
        composable<Screen.OrderSummeryScreen> {
            val arg = it.toRoute<Screen.OrderSummeryScreen>()
            OrderSummeryScreen(arg)
        }
    }
}
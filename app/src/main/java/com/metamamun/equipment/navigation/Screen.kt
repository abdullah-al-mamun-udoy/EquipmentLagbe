package com.metamamun.equipment.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object SplashScreen : Screen()
    @Serializable
    data object OnboardingScreen : Screen()
    @Serializable
    data object SignInScreen : Screen()
    @Serializable
    data object HomeScreen : Screen()
    @Serializable
    data object VideosScreen : Screen()
    @Serializable
    data object LiveTvScreen : Screen()
    @Serializable
    data object MyPlanScreen : Screen()
    @Serializable
    data class PlanDetailsScreen(
        val item: String
    )
    @Serializable
    data object SearchScreen : Screen()
    @Serializable
    data object MenuScreen : Screen()
    @Serializable
    data object EditProfileScreen : Screen()
    @Serializable
    data object PrivacyPolicyScreen : Screen()
    @Serializable
    data object TermsAndConditionsScreen : Screen()
    @Serializable
    data object SignUpScreen : Screen()

    @Serializable
    data class OrderSummeryScreen(
        val id: Int,
        val name: String
    )

}



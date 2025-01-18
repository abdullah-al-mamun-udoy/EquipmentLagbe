package com.metamamun.equipment.navigation

import androidx.annotation.DrawableRes
import com.metamamun.equipment.R

enum class BottomNavigation(
    @DrawableRes val icon: Int,
    val route: Screen?,
    val title: String = ""
) {
    HOME(
        R.drawable.ic_home,
        Screen.HomeScreen,
        "Home"
    ),
    VIDEOS(
        R.drawable.ic_diet,
        Screen.VideosScreen,
        "Diet"
    ),
//    ACTION_BUTTON(
//        0,
//        null,
//        "Live TV"
//    ),
    MYP_PLAN(
        R.drawable.ic_plan,
        Screen.MyPlanScreen,
        "My Plan"
    ),
    SEARCH(
        R.drawable.ic_menu,
        Screen.MenuScreen,
        "Menu"
    ),
}
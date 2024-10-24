package com.example.composeapiconsume.android.core.resources.route

sealed class AppScreen(val route: String) {
    data object HomeScreen : AppScreen(ConstantAppScreenName.HOME_SCREEN)
    data object DetailsScreen : AppScreen(ConstantAppScreenName.DETAILS_SCREEN)
}


object ConstantAppScreenName {
    const val HOME_SCREEN = "home_screen"
    const val DETAILS_SCREEN = "details_screen"
}
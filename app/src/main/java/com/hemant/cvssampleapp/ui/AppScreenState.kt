package com.hemant.cvssampleapp.ui

sealed class AppScreenState(
    val route: String
) {
    object Home : AppScreenState(
        route = "home_screen"
    )

    object Detail : AppScreenState(
        route = "detail_screen"
    )
}

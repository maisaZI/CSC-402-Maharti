package com.example.maharti.navigation

object Routes {
    const val LOGIN = "login"
    const val SIGN_UP = "signup"
    const val FORGOT_PASSWORD = "forgot_password"
    const val BUILD_PROFILE = "skill_profile"
    const val HOME = "home"
    const val SEARCH = "search"
    const val FILTERS = "filters"
    const val RECEIVER_PROFILE = "student_profile/{studentId}"
    const val MY_PROFILE = "profile"
    const val EXCHANGE = "exchange"
    const val EXCHANGE_REQUEST = "exchange_request"
    const val MY_EXCHANGES = "exchanges"
    const val NOTIFICATIONS = "notifications"
    const val REVIEW = "review/{exchangeId}"

    fun receiverProfile(studentId: String) = "student_profile/$studentId"
    fun review(exchangeId: String) = "review/$exchangeId"

    val bottomBarRoutes = setOf(HOME, MY_PROFILE, MY_EXCHANGES, SEARCH)

    fun tabToRoute(tab: String): String = when (tab) {
        "Home" -> HOME
        "Profile" -> MY_PROFILE
        "Exchanges" -> MY_EXCHANGES
        "Search" -> SEARCH
        "Requests" -> EXCHANGE_REQUEST
        else -> HOME
    }

    fun routeToTab(route: String?): String = when (route) {
        MY_PROFILE -> "Profile"
        MY_EXCHANGES -> "Exchanges"
        SEARCH -> "Search"
        EXCHANGE_REQUEST -> "Requests"
        else -> "Home"
    }
}

package com.example.maharti.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.maharti.data.ExchangeRequest
import com.example.maharti.data.ExchangeStatus
import com.example.maharti.data.sampleExchanges
import com.example.maharti.ui.screens.BuildProfileScreen
import com.example.maharti.ui.screens.ExchangeRequestReceiverScreen
import com.example.maharti.ui.screens.FiltersScreen
import com.example.maharti.ui.screens.ForgotPasswordScreen
import com.example.maharti.ui.screens.HomeScreen
import com.example.maharti.ui.screens.LoginScreen
import com.example.maharti.ui.screens.MyExchangesScreen
import com.example.maharti.ui.screens.NotificationsScreen
import com.example.maharti.ui.screens.ReceiverProfileScreen
import com.example.maharti.ui.screens.ReviewExchangeScreen
import com.example.maharti.ui.screens.SearchScreen
import com.example.maharti.ui.screens.SenderProfileScreen
import com.example.maharti.ui.screens.SignUpScreen

@Composable
fun MahartiNavGraph(navController: NavHostController) {

    var exchangeStatus by remember { mutableStateOf(ExchangeStatus.IDLE) }
    var exchangeRequest by remember { mutableStateOf<ExchangeRequest?>(null) }

    NavHost(navController = navController, startDestination = Routes.LOGIN) {

        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onForgotPasswordClick = { navController.navigate(Routes.FORGOT_PASSWORD) },
                onSignUpClick = { navController.navigate(Routes.SIGN_UP) }
            )
        }

        composable(Routes.SIGN_UP) {
            SignUpScreen(
                onBackClick = { navController.popBackStack() },
                onCreateAccountClick = { _, _, _, _, _ ->
                    navController.navigate(Routes.BUILD_PROFILE) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onLoginClick = { navController.popBackStack(Routes.LOGIN, inclusive = false) }
            )
        }

        composable(Routes.FORGOT_PASSWORD) {
            ForgotPasswordScreen(
                onBackToLogin = { navController.popBackStack() }
            )
        }

        composable(Routes.BUILD_PROFILE) {
            BuildProfileScreen(
                onBack = {
                    if (!navController.popBackStack()) navController.navigate(Routes.HOME)
                },
                onSave = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.BUILD_PROFILE) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onNavigateToSearch = { navController.navigateToTab(Routes.SEARCH) },
                onNotificationsClick = { navController.navigate(Routes.NOTIFICATIONS) },
                onEditSkillsClick = { navController.navigate(Routes.BUILD_PROFILE) }
            )
        }

        composable(Routes.SEARCH) {
            SearchScreen(
                onOpenFilters = { navController.navigate(Routes.FILTERS) },
                onOpenStudent = { studentId -> navController.navigate(Routes.receiverProfile(studentId)) }
            )
        }

        composable(Routes.FILTERS) {
            FiltersScreen(
                onBack = { navController.popBackStack() },
                onApply = { _, _, _, _ -> navController.popBackStack() }
            )
        }

        composable(
            route = Routes.RECEIVER_PROFILE,
            arguments = listOf(navArgument("studentId") { type = NavType.StringType })
        ) {
            ReceiverProfileScreen(
                onBackClick = { navController.popBackStack() },
                onRequestExchange = { navController.navigate(Routes.EXCHANGE) }
            )
        }

        composable(Routes.EXCHANGE) {
            MahartiExchangeFlow(
                status = exchangeStatus,
                request = exchangeRequest,
                wantedSkills = listOf("Logo Design", "Python", "Public Speaking"),
                offeredSkills = listOf("PowerPoint", "Excel", "Photography"),
                availableDates = listOf("October 4, 2026", "October 5, 2026", "October 6, 2026"),
                availableTimes = listOf("4:00 PM", "5:30 PM", "7:00 PM"),
                onSend = { wanted, offered ->
                    exchangeRequest = ExchangeRequest(id = "request-1", wanted = wanted, offered = offered)
                    exchangeStatus = ExchangeStatus.PENDING
                },
                onConfirm = { _, _, onResult -> onResult(true) },
                onReset = {
                    exchangeRequest = null
                    exchangeStatus = ExchangeStatus.IDLE
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.EXCHANGE_REQUEST) {
            ExchangeRequestReceiverScreen(
                onBackClick = { navController.popBackStack() },
                onAcceptExchangeClick = {
                    if (exchangeRequest == null) {
                        exchangeRequest = ExchangeRequest(id = "request-1", wanted = "Logo Design", offered = "PowerPoint")
                    }
                    exchangeStatus = ExchangeStatus.ACCEPTED
                    navController.navigate(Routes.EXCHANGE)
                },
                onConfirmDeclineClick = { navController.popBackStack() },
                onBottomNavClick = { tabName ->
                    navController.navigateToTab(Routes.tabToRoute(tabName.replaceFirstChar { it.uppercase() }))
                }
            )
        }

        composable(Routes.MY_EXCHANGES) {
            MyExchangesScreen(
                exchanges = sampleExchanges,
                onExploreSkills = { navController.navigateToTab(Routes.SEARCH) },
                onExchangeClick = { item -> navController.navigate(Routes.review(sampleExchanges.indexOf(item).toString())) }
            )
        }

        composable(Routes.MY_PROFILE) {
            SenderProfileScreen(
                onEditProfile = { navController.navigate(Routes.BUILD_PROFILE) }
            )
        }

        composable(Routes.NOTIFICATIONS) {
            NotificationsScreen(
                onBackClick = { navController.popBackStack() },
                onExchangeRequestClick = { navController.navigate(Routes.EXCHANGE_REQUEST) },
                onReviewClick = { navController.navigate(Routes.review("2")) }
            )
        }

        composable(
            route = Routes.REVIEW,
            arguments = listOf(navArgument("exchangeId") { type = NavType.StringType })
        ) {
            ReviewExchangeScreen(
                onBackClick = { navController.popBackStack() },
                onReviewSubmitted = { navController.popBackStack() }
            )
        }
    }
}

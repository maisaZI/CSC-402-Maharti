package com.example.maharti.navigation
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.maharti.ui.theme.Cream
import com.example.maharti.ui.theme.Purple
import com.example.maharti.ui.theme.Ink
import com.example.maharti.ui.theme.Lavender
import com.example.maharti.ui.theme.Mint
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.maharti.data.ExchangeRequest
import com.example.maharti.data.ExchangeStatus
import com.example.maharti.data.SessionChoice
import com.example.maharti.ui.screens.ExchangeSkillsScreen
import com.example.maharti.ui.screens.SessionSchedulingScreen

/**
 * Host owns status and request. onSend must create a request and update status to PENDING
 * only on success; observe the recipient's acceptance and update it to ACCEPTED.
 * onConfirm must call onResult(true) only after the booking succeeds; call false on failure.
 * onReset clears host state.
 */
@Composable
fun MahartiExchangeFlow(
    status: ExchangeStatus,
    request: ExchangeRequest?,
    wantedSkills: List<String>,
    offeredSkills: List<String>,
    availableDates: List<String>,
    availableTimes: List<String>,
    onSend: (wanted: String, offered: String) -> Unit,
    onConfirm: (ExchangeRequest, SessionChoice, onResult: (Boolean) -> Unit) -> Unit,
    onReset: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val nav = rememberNavController()
    var confirmed by rememberSaveable { mutableStateOf(false) }
    var confirming by rememberSaveable { mutableStateOf(false) }
    var bookingFailed by rememberSaveable { mutableStateOf(false) }
    // A lost or revoked acceptance must immediately make the schedule inaccessible.
    LaunchedEffect(status, request?.id) {
        if ((status != ExchangeStatus.ACCEPTED || request == null) && !confirmed) {
            nav.popBackStack("exchange", false)
        }
    }
    NavHost(navController = nav, startDestination = "exchange", modifier = modifier) {
        composable("exchange") {
            ExchangeSkillsScreen(status, wantedSkills, offeredSkills, onBack, onSend,
                onSchedule = { if (status == ExchangeStatus.ACCEPTED && request != null) nav.navigate("schedule") { launchSingleTop = true } })
        }
        composable("schedule") {
            val active = request
            if (active != null && (status == ExchangeStatus.ACCEPTED || confirmed)) {
                SessionSchedulingScreen(active, availableDates, availableTimes, confirmed, confirming, bookingFailed,
                    onBack = { nav.popBackStack() },
                    onConfirm = { choice ->
                        if (!confirming && status == ExchangeStatus.ACCEPTED) {
                            confirming = true
                            bookingFailed = false
                            onConfirm(active, choice) { success -> confirming = false; confirmed = success; bookingFailed = !success }
                        }
                    },
                    onAnother = {
                        confirmed = false
                        confirming = false
                        bookingFailed = false
                        onReset()
                        nav.popBackStack("exchange", false)
                    })
            }
        }
    }
}

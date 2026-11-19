package com.example.maharti
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

/** Acceptance must come from the recipient/backend for the current request ID. */
enum class ExchangeStatus { IDLE, SENDING, PENDING, ACCEPTED, FAILED }
data class ExchangeRequest(val id: String, val wanted: String, val offered: String)
data class SessionChoice(val date: String, val time: String, val location: String)

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
                ScheduleScreen(active, availableDates, availableTimes, confirmed, confirming, bookingFailed,
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

@Composable
private fun ExchangeSkillsScreen(status: ExchangeStatus, wantedSkills: List<String>, offeredSkills: List<String>, onBack: () -> Unit, onSend: (String, String) -> Unit, onSchedule: () -> Unit) {
    var wanted by rememberSaveable(wantedSkills) { mutableStateOf(wantedSkills.firstOrNull().orEmpty()) }
    var offered by rememberSaveable(offeredSkills) { mutableStateOf(offeredSkills.firstOrNull().orEmpty()) }
    Page {
        Header("Exchange skills", "Select your skills, then send a request.", onBack)
        Spacer(Modifier.height(24.dp))
        PrimaryButton(if (status == ExchangeStatus.SENDING) "Sending…" else "Send Request", status in listOf(ExchangeStatus.IDLE, ExchangeStatus.FAILED) && wanted.isNotBlank() && offered.isNotBlank()) { onSend(wanted, offered) }
        Spacer(Modifier.height(18.dp))
        CardBlock {
            SkillMenu("Skill I want", wanted, wantedSkills, status == ExchangeStatus.IDLE || status == ExchangeStatus.FAILED) { wanted = it }
            SkillMenu("Skill I offer", offered, offeredSkills, status == ExchangeStatus.IDLE || status == ExchangeStatus.FAILED) { offered = it }
            Text("Options are limited to profile skills.", color = Ink.copy(alpha = .7f), fontSize = 12.sp)
        }
        Spacer(Modifier.height(18.dp))
        when (status) {
            ExchangeStatus.PENDING -> Badge("✓ Request sent — awaiting acceptance")
            ExchangeStatus.ACCEPTED -> { Badge("✓ Accepted — choose an available slot"); Spacer(Modifier.height(16.dp)); PrimaryButton("Schedule session", true, onSchedule) }
            ExchangeStatus.FAILED -> Text("Could not send request. Please try again.", color = MaterialTheme.colorScheme.error)
            else -> Unit
        }
    }
}

@Composable
private fun ScheduleScreen(request: ExchangeRequest, dates: List<String>, times: List<String>, confirmed: Boolean, confirming: Boolean, bookingFailed: Boolean, onBack: () -> Unit, onConfirm: (SessionChoice) -> Unit, onAnother: () -> Unit) {
    var date by rememberSaveable(request.id) { mutableStateOf("") }
    var time by rememberSaveable(request.id) { mutableStateOf("") }
    var location by rememberSaveable(request.id) { mutableStateOf("Online") }
    Page {
        Header("Schedule session", null, onBack)
        Spacer(Modifier.height(8.dp)); Badge("✓ Accepted — choose an available slot")
        Spacer(Modifier.height(16.dp)); Text("${request.wanted} ⇄ ${request.offered}", color = Ink.copy(alpha = .75f), fontSize = 16.sp)
        Spacer(Modifier.height(8.dp))
        CardBlock {
            Text("EXCHANGE SESSION PLAN", color = Purple, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            Spacer(Modifier.height(8.dp))
            Text("① Layan teaches you: ${request.wanted}", color = Ink, fontWeight = FontWeight.SemiBold)
            Text("② You teach Layan: ${request.offered}", color = Ink, fontWeight = FontWeight.SemiBold)
        }
        Spacer(Modifier.height(24.dp)); SectionTitle("Choose a date")
        Spacer(Modifier.height(12.dp))
        CardBlock {
            Text("Partner availability", color = Ink, fontWeight = FontWeight.SemiBold)
            if (dates.isEmpty()) Text("No dates available yet.", color = Ink)
            dates.forEach { option -> Choice(option, date == option, !confirmed && !confirming) { date = option } }
        }
        Spacer(Modifier.height(24.dp)); SectionTitle("Available times")
        if (times.isEmpty()) Text("No times available yet.", color = Ink)
        times.forEach { option -> Choice(option, time == option, !confirmed && !confirming) { time = option } }
        Spacer(Modifier.height(16.dp)); SectionTitle("Where will you meet?")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Online", "Campus").forEach { option -> Choice(option, location == option, !confirmed && !confirming) { location = option } }
        }
        Spacer(Modifier.height(20.dp))
        if (!confirmed) {
            if (bookingFailed) Text("Could not confirm session. Please try again.", color = MaterialTheme.colorScheme.error)
            PrimaryButton(if (confirming) "Confirming…" else "Confirm Session", !confirming && date in dates && time in times) { onConfirm(SessionChoice(date, time, location)) }
        } else {
            Badge("✓ Session confirmed!")
            Spacer(Modifier.height(16.dp))
            PrimaryButton("Make Another Request", true, onAnother)
        }
    }
}

@Composable private fun Page(content: @Composable ColumnScope.() -> Unit) {
    Column(Modifier.fillMaxSize().background(Cream).verticalScroll(rememberScrollState()).padding(horizontal = 28.dp, vertical = 32.dp), content = content)
}
@Composable private fun Header(title: String, subtitle: String?, onBack: () -> Unit) {
    Row(verticalAlignment = Alignment.Top) {
        Text("←", Modifier.background(Lavender, RoundedCornerShape(14.dp)).clickable(onClick = onBack).padding(horizontal = 14.dp, vertical = 8.dp), color = Purple, fontSize = 25.sp)
        Spacer(Modifier.width(18.dp))
        Column { Text(title, color = Ink, fontWeight = FontWeight.Bold, fontSize = 26.sp, lineHeight = 30.sp); if (subtitle != null) Text(subtitle, color = Ink.copy(alpha = .75f), fontSize = 13.sp) }
    }
}
@Composable private fun CardBlock(content: @Composable ColumnScope.() -> Unit) { Column(Modifier.fillMaxWidth().background(Lavender, RoundedCornerShape(20.dp)).padding(16.dp), content = content) }
@Composable private fun Badge(label: String) { Text(label, Modifier.background(Mint, RoundedCornerShape(50)).padding(horizontal = 12.dp, vertical = 8.dp), color = Purple, fontSize = 13.sp, fontWeight = FontWeight.SemiBold) }
@Composable private fun SectionTitle(label: String) { Text(label, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 18.sp) }
@Composable private fun PrimaryButton(label: String, enabled: Boolean, onClick: () -> Unit) { Button(onClick = onClick, enabled = enabled, modifier = Modifier.fillMaxWidth().heightIn(min = 54.dp), shape = RoundedCornerShape(18.dp), colors = ButtonDefaults.buttonColors(containerColor = Purple)) { Text(label, fontSize = 16.sp, fontWeight = FontWeight.SemiBold) } }
@Composable private fun Choice(label: String, selected: Boolean, enabled: Boolean, onClick: () -> Unit) { Text(label, Modifier.padding(top = 8.dp, end = 8.dp).background(if (selected) Purple else Lavender, RoundedCornerShape(50)).clickable(enabled = enabled, onClick = onClick).padding(horizontal = 14.dp, vertical = 9.dp), color = if (selected) Color.White else Purple, fontWeight = FontWeight.SemiBold) }
@Composable private fun SkillMenu(label: String, selected: String, choices: List<String>, enabled: Boolean, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Text("$label: ${selected.ifBlank { "Select a skill" }} ▾", Modifier.clickable(enabled = enabled) { expanded = true }.padding(vertical = 4.dp), color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) { choices.forEach { skill -> DropdownMenuItem(text = { Text(skill) }, onClick = { onSelect(skill); expanded = false }) } }
    }
}

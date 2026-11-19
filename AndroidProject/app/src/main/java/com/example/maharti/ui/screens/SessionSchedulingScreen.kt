package com.example.maharti.ui.screens
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
import com.example.maharti.ui.components.Badge
import com.example.maharti.ui.components.CardBlock
import com.example.maharti.ui.components.Choice
import com.example.maharti.ui.components.Header
import com.example.maharti.ui.components.Page
import com.example.maharti.ui.components.PrimaryButton
import com.example.maharti.ui.components.SectionTitle
import com.example.maharti.ui.components.SkillMenu

@Composable
fun SessionSchedulingScreen(request: ExchangeRequest, dates: List<String>, times: List<String>, confirmed: Boolean, confirming: Boolean, bookingFailed: Boolean, onBack: () -> Unit, onConfirm: (SessionChoice) -> Unit, onAnother: () -> Unit) {
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

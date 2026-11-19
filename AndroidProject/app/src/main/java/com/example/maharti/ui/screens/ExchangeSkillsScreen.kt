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
fun ExchangeSkillsScreen(status: ExchangeStatus, wantedSkills: List<String>, offeredSkills: List<String>, onBack: () -> Unit, onSend: (String, String) -> Unit, onSchedule: () -> Unit) {
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

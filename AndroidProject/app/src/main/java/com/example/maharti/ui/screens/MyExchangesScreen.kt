package com.example.maharti.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ExchangesBackground = Color(0xFFFFFBF4)
private val ExchangesPurple = Color(0xFF6121AB)
private val ExchangesInk = Color(0xFF291F38)
private val ExchangesMuted = Color(0xFF51465E)

data class ExchangeListItem(
    val studentName: String,
    val wantedSkill: String,
    val offeredSkill: String,
    val description: String,
    val status: String
)
/**
 * Maharti - CSC 402
 * Milestone 3 - Stage 2
 *
 * Screen: My Exchanges (08)
 * Developer: Samar Alasmakh
 */
@Composable
fun MyExchangesScreen(
    exchanges: List<ExchangeListItem> = emptyList(),
    onExploreSkills: () -> Unit = {},
    onExchangeClick: (ExchangeListItem) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedTab by rememberSaveable { mutableStateOf("Pending") }
    val visible = exchanges.filter { it.status.equals(selectedTab, ignoreCase = true) }

    Column(
        modifier = modifier.fillMaxSize().background(ExchangesBackground)
            .verticalScroll(rememberScrollState()).padding(horizontal = 32.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("My Exchanges", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ExchangesInk)
        Text("Your learning journey in one place.", fontSize = 12.sp, color = ExchangesMuted)
        Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
            listOf("Pending", "Upcoming", "Completed").forEach { tab ->
                val chosen = tab == selectedTab
                Surface(
                    color = if (chosen) ExchangesPurple else when (tab) {
                        "Upcoming" -> Color(0xFFE5F2FF)
                        "Completed" -> Color(0xFFFFE8F5)
                        else -> Color(0xFFF6F0FF)
                    },
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.clickable { selectedTab = tab }
                ) {
                    Text(
                        text = tab, fontSize = 11.sp, fontWeight = FontWeight.SemiBold,
                        color = if (chosen) Color.White else ExchangesPurple,
                        modifier = Modifier.padding(horizontal = 11.dp, vertical = 8.dp)
                    )
                }
            }
        }

        if (visible.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 68.dp, bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                Box(
                    modifier = Modifier.size(220.dp).background(Color(0xFFFFF5D6), RoundedCornerShape(32.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.size(164.dp).background(Color(0xFFFFE8F5), CircleShape), contentAlignment = Alignment.Center) {
                        Box(modifier = Modifier.size(128.dp).background(Color(0xFFEDE2FF), CircleShape), contentAlignment = Alignment.Center) {
                            Box(modifier = Modifier.size(104.dp).background(Color.White, CircleShape), contentAlignment = Alignment.Center) {
                                Box(modifier = Modifier.size(72.dp).background(ExchangesPurple, CircleShape), contentAlignment = Alignment.Center) {
                                    Text("✦", fontSize = 36.sp, color = Color.White)
                                }
                            }
                        }
                    }
                    Text("✧", color = ExchangesPurple, fontSize = 24.sp, modifier = Modifier.align(Alignment.TopStart).padding(20.dp))
                    Text("★", color = ExchangesPurple, fontSize = 22.sp, modifier = Modifier.align(Alignment.BottomEnd).padding(20.dp))
                }
                Text("No active exchanges yet", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ExchangesInk, textAlign = TextAlign.Center)
                Text(
                    "Browse available skills to start your first exchange and bring your learning journey to life.",
                    fontSize = 12.sp, lineHeight = 18.sp, color = ExchangesMuted, textAlign = TextAlign.Center
                )
                Button(
                    onClick = onExploreSkills,
                    colors = ButtonDefaults.buttonColors(containerColor = ExchangesPurple),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) { Text("Explore Available Skills", color = Color.White, fontSize = 13.sp) }
            }
        } else {
            visible.forEach { item ->
                Surface(
                    modifier = Modifier.fillMaxWidth().clickable { onExchangeClick(item) },
                    color = Color(0xFFF6F0FF), shape = RoundedCornerShape(16.dp)
                ) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(item.studentName, color = ExchangesInk, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text("You want: ${item.wantedSkill}", color = ExchangesPurple, fontSize = 12.sp)
                        Text("You offer: ${item.offeredSkill}", color = ExchangesMuted, fontSize = 12.sp)
                        Text(item.description, color = ExchangesMuted, fontSize = 11.sp)
                        Text(item.status, color = ExchangesPurple, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun MyExchangesEmptyPreview() { MyExchangesScreen() }

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun MyExchangesSamplePreview() {
    MyExchangesScreen(exchanges = listOf(ExchangeListItem("Layan Ahmed", "Logo Design", "PowerPoint", "Exchange request awaiting response", "Pending")))
}
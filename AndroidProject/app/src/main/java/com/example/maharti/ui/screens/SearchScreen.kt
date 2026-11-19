package com.example.maharti.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maharti.ui.theme.*

import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi

@Composable
fun SearchScreen(onOpenFilters: () -> Unit = {}, onOpenStudent: (String) -> Unit = {}, onClearFilters: () -> Unit = {}) {
    var query by remember { mutableStateOf("UI Design") }
    var category by remember { mutableStateOf("All") }
    var matchesOnly by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize().background(Cream).padding(horizontal = 24.dp)) {
        Spacer(Modifier.height(32.dp))
        Text("Find your skill match", fontSize = 27.sp, fontWeight = FontWeight.Bold, color = DarkText)
        Spacer(Modifier.height(12.dp))
        Text("Search students across campus.", fontSize = 14.sp, color = Gray)
        Spacer(Modifier.height(22.dp))
        Text("Search", fontWeight = FontWeight.SemiBold, color = DarkText)
        Spacer(Modifier.height(6.dp))
        Row(
            modifier = Modifier.fillMaxWidth().height(58.dp)
                .border(1.dp, LightPurple, RoundedCornerShape(16.dp))
                .background(Color.White, RoundedCornerShape(16.dp))
                .padding(start = 12.dp, end = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.foundation.text.BasicTextField(
                value = query, onValueChange = { query = it },
                singleLine = true,
                textStyle = androidx.compose.ui.text.TextStyle(color = DarkText, fontSize = 15.sp),
                modifier = Modifier.weight(1f),
                decorationBox = { inner ->
                    Box {
                        if (query.isEmpty()) Text("Search for a skill", color = Gray, fontSize = 15.sp)
                        inner()
                    }
                }
            )
            Surface(onClick = onOpenFilters, shape = RoundedCornerShape(14.dp), color = Purple) {
                Box(Modifier.size(48.dp), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Tune, contentDescription = "Open filters", tint = Color.White)
                }
            }
        }
        Spacer(Modifier.height(18.dp))
        @OptIn(ExperimentalLayoutApi::class)
        FlowRow(horizontalArrangement = Arrangement.spacedBy(7.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
            listOf("All", "Design", "Coding", "Languages").forEach { item ->
                Surface(onClick = { category = item }, shape = RoundedCornerShape(22.dp),
                    color = if (category == item) Purple else LightPurple) {
                    Text(item, modifier = Modifier.padding(horizontal = 12.dp, vertical = 9.dp),
                        color = if (category == item) Color.White else Purple,
                        fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Surface(onClick = { matchesOnly = !matchesOnly }, shape = RoundedCornerShape(20.dp),
            color = Color(0xFFFFF4D7)) {
            Text("✨ Matches for me", modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                color = Purple, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        }
        Column(Modifier.weight(1f).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Box(Modifier.size(172.dp).background(Color(0xFFFFF4D7), CircleShape), contentAlignment = Alignment.Center) {
                Box(Modifier.size(132.dp).background(Color.White, CircleShape), contentAlignment = Alignment.Center) {
                    Box(Modifier.size(76.dp).background(Purple, CircleShape), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Search, contentDescription = null, tint = Color.White,
                            modifier = Modifier.size(36.dp))
                    }
                }
            }
            Spacer(Modifier.height(18.dp))
            Text("No students found matching\nyour search", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center, color = DarkText, lineHeight = 26.sp)
            Spacer(Modifier.height(12.dp))
            Text("Try another skill or clear your filters to see\nmore matches.", fontSize = 14.sp,
                color = Gray, textAlign = TextAlign.Center, lineHeight = 20.sp)
            Spacer(Modifier.height(20.dp))
            Button(onClick = { query = ""; category = "All"; matchesOnly = false; onClearFilters() },
                shape = RoundedCornerShape(20.dp), colors = ButtonDefaults.buttonColors(containerColor = Purple)) {
                Text("Clear filters", modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp))
            }
        }
    }
}

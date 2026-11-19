package com.example.maharti.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val SenderCream = Color(0xFFFFFBF4)
private val SenderPurple = Color(0xFF6121AB)
private val SenderInk = Color(0xFF291F38)
private val SenderMuted = Color(0xFF51465E)

data class SenderProfileUiModel(
    val name: String = "Sara Alqahtani",
    val major: String = "Computer Science",
    val rating: String = "4.8",
    val isVerified: Boolean = true,
    val exchanges: Int = 12,
    val skillsShared: Int = 8,
    val offeredSkills: List<String> = listOf("PowerPoint", "Excel"),
    val learningSkills: List<String> = listOf("Logo Design", "Photography")
)
/**
 * Maharti - CSC 402
 * Milestone 3 - Stage 2
 *
 * Screen: Sender Profile (09)
 * Developer: Samar Alasmakh
 */
@Composable
fun SenderProfileScreen(
    profile: SenderProfileUiModel = SenderProfileUiModel(),
    onEditProfile: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().background(SenderCream)
            .verticalScroll(rememberScrollState()).padding(horizontal = 32.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("✦", fontSize = 38.sp, color = SenderPurple, fontWeight = FontWeight.Black)
        Text(profile.name, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = SenderInk)
        Text("${profile.major} • ★ ${profile.rating}", color = SenderMuted, fontSize = 12.sp)
        if (profile.isVerified) {
            Surface(shape = RoundedCornerShape(15.dp), color = Color(0xFFE3FAF7)) {
                Text("✓ Verified University Student", modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp), color = Color(0xFF00695F), fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            SenderStatCard(profile.exchanges.toString(), "Exchanges", Color(0xFFF6F0FF), Modifier.weight(1f))
            SenderStatCard(profile.skillsShared.toString(), "Skills Shared", Color(0xFFFFF5D6), Modifier.weight(1f))
            SenderStatCard(profile.rating, "Rating", Color(0xFFE3FAF7), Modifier.weight(1f))
        }
        Text("Skills I Offer", color = SenderInk, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
        SenderSkillRow(profile.offeredSkills, listOf(Color(0xFFF6F0FF), Color(0xFFFFE8F5)))
        Text("Skills I Want to Learn", color = SenderInk, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
        SenderSkillRow(profile.learningSkills, listOf(Color(0xFFFFF5D6), Color(0xFFE3FAF7)))
        Surface(color = Color(0xFFFFE8F5), shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Exchange history", color = SenderInk, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text("${profile.exchanges} completed exchanges", color = SenderMuted, fontSize = 12.sp)
                Text("★ ${profile.rating} average rating", color = SenderPurple, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }
        }
        Button(
            onClick = onEditProfile,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = SenderPurple)
        ) { Text("Edit Profile", fontSize = 13.sp, color = Color.White) }
    }
}

@Composable
private fun SenderStatCard(number: String, caption: String, bg: Color, modifier: Modifier = Modifier) {
    Surface(modifier = modifier.height(66.dp), shape = RoundedCornerShape(16.dp), color = bg) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(number, color = SenderPurple, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(caption, color = SenderMuted, fontWeight = FontWeight.SemiBold, fontSize = 9.sp)
        }
    }
}

@Composable
private fun SenderSkillRow(skills: List<String>, backgrounds: List<Color>) {
    Row(horizontalArrangement = Arrangement.spacedBy(7.dp), modifier = Modifier.fillMaxWidth()) {
        skills.forEachIndexed { index, skill ->
            Surface(color = backgrounds[index % backgrounds.size], shape = RoundedCornerShape(18.dp)) {
                Text(skill, color = SenderPurple, fontSize = 11.sp, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 11.dp, vertical = 8.dp))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun SenderProfilePreview() { SenderProfileScreen() }
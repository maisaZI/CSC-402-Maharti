package com.example.maharti.ui.components
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

@Composable internal fun Page(content: @Composable ColumnScope.() -> Unit) {
    Column(Modifier.fillMaxSize().background(Cream).verticalScroll(rememberScrollState()).padding(horizontal = 28.dp, vertical = 32.dp), content = content)
}
@Composable internal fun Header(title: String, subtitle: String?, onBack: () -> Unit) {
    Row(verticalAlignment = Alignment.Top) {
        Text("←", Modifier.background(Lavender, RoundedCornerShape(14.dp)).clickable(onClick = onBack).padding(horizontal = 14.dp, vertical = 8.dp), color = Purple, fontSize = 25.sp)
        Spacer(Modifier.width(18.dp))
        Column { Text(title, color = Ink, fontWeight = FontWeight.Bold, fontSize = 26.sp, lineHeight = 30.sp); if (subtitle != null) Text(subtitle, color = Ink.copy(alpha = .75f), fontSize = 13.sp) }
    }
}
@Composable internal fun CardBlock(content: @Composable ColumnScope.() -> Unit) { Column(Modifier.fillMaxWidth().background(Lavender, RoundedCornerShape(20.dp)).padding(16.dp), content = content) }
@Composable internal fun Badge(label: String) { Text(label, Modifier.background(Mint, RoundedCornerShape(50)).padding(horizontal = 12.dp, vertical = 8.dp), color = Purple, fontSize = 13.sp, fontWeight = FontWeight.SemiBold) }
@Composable internal fun SectionTitle(label: String) { Text(label, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 18.sp) }
@Composable internal fun PrimaryButton(label: String, enabled: Boolean, onClick: () -> Unit) { Button(onClick = onClick, enabled = enabled, modifier = Modifier.fillMaxWidth().heightIn(min = 54.dp), shape = RoundedCornerShape(18.dp), colors = ButtonDefaults.buttonColors(containerColor = Purple)) { Text(label, fontSize = 16.sp, fontWeight = FontWeight.SemiBold) } }
@Composable internal fun Choice(label: String, selected: Boolean, enabled: Boolean, onClick: () -> Unit) { Text(label, Modifier.padding(top = 8.dp, end = 8.dp).background(if (selected) Purple else Lavender, RoundedCornerShape(50)).clickable(enabled = enabled, onClick = onClick).padding(horizontal = 14.dp, vertical = 9.dp), color = if (selected) Color.White else Purple, fontWeight = FontWeight.SemiBold) }
@Composable internal fun SkillMenu(label: String, selected: String, choices: List<String>, enabled: Boolean, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Text("$label: ${selected.ifBlank { "Select a skill" }} ▾", Modifier.clickable(enabled = enabled) { expanded = true }.padding(vertical = 4.dp), color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) { choices.forEach { skill -> DropdownMenuItem(text = { Text(skill) }, onClick = { onSelect(skill); expanded = false }) } }
    }
}

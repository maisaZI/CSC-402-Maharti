package edu.iau.cshj.csc402.navbar.screens

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
import edu.iau.cshj.csc402.navbar.ui.theme.*

@Composable
fun FiltersScreen(onBack: () -> Unit = {}, onApply: (category: String, matchesOnly: Boolean, rating: String, sort: String) -> Unit = { _, _, _, _ -> }) {
    var category by remember { mutableStateOf("Design") }
    var matchesOnly by remember { mutableStateOf(true) }
    var rating by remember { mutableStateOf("4.0+") }
    var sort by remember { mutableStateOf("Best match") }
    Column(Modifier.fillMaxSize().background(Color.White).padding(horizontal = 20.dp)) {
        Row(Modifier.fillMaxWidth().padding(top = 20.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(52.dp).background(LightPurple, RoundedCornerShape(14.dp)), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Tune, contentDescription = null, tint = Purple)
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text("Filters", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = DarkText)
                Text("Find your ideal skill match", fontSize = 13.sp, color = Gray)
            }
            IconButton(onClick = onBack) { Icon(Icons.Default.Close, contentDescription = "Close", tint = Gray) }
        }
        HorizontalDivider(Modifier.padding(vertical = 18.dp), color = LightPurple)
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            Text("Skill category", fontSize = 19.sp, fontWeight = FontWeight.Bold, color = DarkText)
            Spacer(Modifier.height(10.dp))
            val options = listOf("Programming", "Design", "Languages", "Mathematics", "Data Analysis", "Academic Writing")
            options.chunked(2).forEach { pair ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                    pair.forEach { item ->
                        Surface(onClick = { category = item }, modifier = Modifier.weight(1f).height(52.dp),
                            shape = RoundedCornerShape(14.dp), color = if (category == item) Purple else LightPurple) {
                            Box(contentAlignment = Alignment.Center) {
                                Text((if (category == item) "✓  " else "") + item,
                                    color = if (category == item) Color.White else Purple,
                                    fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(18.dp))
            Text("Skill match", fontSize = 19.sp, fontWeight = FontWeight.Bold, color = DarkText)
            Spacer(Modifier.height(10.dp))
            Row(Modifier.fillMaxWidth().background(LightPurple, RoundedCornerShape(14.dp)).padding(14.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Groups, contentDescription = null, tint = Purple)
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Matches for me", fontWeight = FontWeight.SemiBold, color = DarkText)
                    Text("Students who want a skill I offer", fontSize = 11.sp, color = Gray)
                }
                Switch(checked = matchesOnly, onCheckedChange = { matchesOnly = it },
                    colors = SwitchDefaults.colors(checkedTrackColor = Purple))
            }
            Spacer(Modifier.height(20.dp))
            Text("Minimum rating", fontSize = 19.sp, fontWeight = FontWeight.Bold, color = DarkText)
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf("Any", "4.0+", "4.5+").forEach { value ->
                    OutlinedButton(onClick = { rating = value }, modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp), contentPadding = PaddingValues(8.dp),
                        border = BorderStroke(1.dp, if (rating == value) Purple else LightPurple)) {
                        Text(if (value == "Any") value else "$value ★", color = DarkText, fontSize = 12.sp)
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Text("Sort by", fontSize = 19.sp, fontWeight = FontWeight.Bold, color = DarkText)
            listOf("Best match" to "Students who are the best fit for you", "Highest rated" to "Students with the highest average rating").forEach { (value, hint) ->
                Row(Modifier.fillMaxWidth().clickable { sort = value }.padding(vertical = 7.dp), verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = sort == value, onClick = { sort = value },
                        colors = RadioButtonDefaults.colors(selectedColor = Purple))
                    Column {
                        Text(value, color = DarkText, fontWeight = FontWeight.SemiBold)
                        Text(hint, color = Gray, fontSize = 11.sp)
                    }
                }
            }
        }
        HorizontalDivider(color = LightPurple)
        Row(Modifier.fillMaxWidth().padding(vertical = 14.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedButton(onClick = { category = ""; matchesOnly = false; rating = "Any"; sort = "Best match" },
                modifier = Modifier.weight(1f).height(50.dp), shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Purple)) { Text("Reset", color = Purple) }
            Button(onClick = { onApply(category, matchesOnly, rating, sort) },
                modifier = Modifier.weight(1.5f).height(50.dp), shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Purple)) {
                Text("Apply filters  →")
            }
        }
    }
}

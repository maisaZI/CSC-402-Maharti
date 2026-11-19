package com.example.maharti.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maharti.ui.theme.BrandPurple
import com.example.maharti.ui.theme.CardLavender
import com.example.maharti.ui.theme.MutedText
import com.example.maharti.ui.theme.PrimaryDarkText
import com.example.maharti.ui.theme.SoftMint
import com.example.maharti.ui.theme.WarmOffWhite

@Composable
fun BuildProfileScreen(
    onBack: () -> Unit = {},
    onSave: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var teachPowerPoint by remember { mutableStateOf(true) }
    var teachExcel by remember { mutableStateOf(false) }
    var teachPhotography by remember { mutableStateOf(false) }
    var learnLogoDesign by remember { mutableStateOf(true) }
    var learnPython by remember { mutableStateOf(false) }
    var learnPublicSpeaking by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WarmOffWhite)
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                colors = IconButtonDefaults.iconButtonColors(containerColor = CardLavender),
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = BrandPurple
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Maharti",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = BrandPurple
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Build your skill profile",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryDarkText
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Tell Maharti what you can teach and what you want to learn. This powers your student matches.",
            fontSize = 14.sp,
            color = MutedText,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(24.dp))
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            color = CardLavender
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "I CAN TEACH",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandPurple,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    FilterChip(
                        selected = teachPowerPoint,
                        onClick = { teachPowerPoint = !teachPowerPoint },
                        label = { Text("PowerPoint") },
                        leadingIcon = if (teachPowerPoint) {
                            {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        } else null,
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.White,
                            selectedContainerColor = Color(0xFFFFB300),
                            labelColor = PrimaryDarkText,
                            selectedLabelColor = PrimaryDarkText,
                            selectedLeadingIconColor = PrimaryDarkText
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    FilterChip(
                        selected = teachExcel,
                        onClick = { teachExcel = !teachExcel },
                        label = { Text("Excel") },
                        leadingIcon = if (teachExcel) {
                            {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        } else null,
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.White,
                            selectedContainerColor = Color(0xFFFFB300),
                            labelColor = MutedText,
                            selectedLabelColor = PrimaryDarkText
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )


                    FilterChip(
                        selected = teachPhotography,
                        onClick = { teachPhotography = !teachPhotography },
                        label = { Text("Photography") },
                        leadingIcon = if (teachPhotography) {
                            {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        } else null,
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.White,
                            selectedContainerColor = Color(0xFFFFB300),
                            labelColor = MutedText,
                            selectedLabelColor = PrimaryDarkText
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // I WANT TO LEARN Section (Soft Mint Panel)
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            color = SoftMint
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "I WANT TO LEARN",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF006064),
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Logo Design Chip (Selected - Dark Teal)
                    FilterChip(
                        selected = learnLogoDesign,
                        onClick = { learnLogoDesign = !learnLogoDesign },
                        label = { Text("Logo Design") },
                        leadingIcon = if (learnLogoDesign) {
                            {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        } else null,
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.White,
                            selectedContainerColor = Color(0xFF00695C),
                            labelColor = PrimaryDarkText,
                            selectedLabelColor = Color.White,
                            selectedLeadingIconColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )

                    // Python Chip
                    FilterChip(
                        selected = learnPython,
                        onClick = { learnPython = !learnPython },
                        label = { Text("Python") },
                        leadingIcon = if (learnPython) {
                            {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        } else null,
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.White,
                            selectedContainerColor = Color(0xFF00695C),
                            labelColor = MutedText,
                            selectedLabelColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )

                    // Public Speaking Chip
                    FilterChip(
                        selected = learnPublicSpeaking,
                        onClick = { learnPublicSpeaking = !learnPublicSpeaking },
                        label = { Text("Public Speaking") },
                        leadingIcon = if (learnPublicSpeaking) {
                            {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        } else null,
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.White,
                            selectedContainerColor = Color(0xFF00695C),
                            labelColor = MutedText,
                            selectedLabelColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "Add another skill",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MutedText
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search skills...", color = Color(0xFF9E9E9E)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = BrandPurple,
                unfocusedBorderColor = Color(0xFFE0D8EC)
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Save & Find My Matches Button
        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BrandPurple)
        ) {
            Text(
                text = "Save & Find My Matches",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

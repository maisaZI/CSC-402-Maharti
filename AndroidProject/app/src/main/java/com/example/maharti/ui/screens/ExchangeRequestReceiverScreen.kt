package com.example.maharti.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ReceiverRequestUiModel(
    val senderName: String,
    val badgeText: String,
    val majorAndRating: String,
    val teachesSkill: String,
    val youTeachSkill: String,
    val message: String,
    val availability: String
)

private enum class ReceiverBottomTab(val label: String, val icon: ImageVector) {
    HOME("Home", Icons.Default.Home),
    PROFILE("Profile", Icons.Default.Person),
    EXCHANGES("Exchanges", Icons.AutoMirrored.Filled.CompareArrows),
    SEARCH("Search", Icons.Default.Search),
    REQUESTS("Requests", Icons.Outlined.Inbox)
}

@Composable
fun ExchangeRequestReceiverScreen(
    modifier: Modifier = Modifier,
    requestData: ReceiverRequestUiModel = ReceiverRequestUiModel(
        senderName = "Layan Alharbi",
        badgeText = "University Student",
        majorAndRating = "Computer Science • ★ 4.9",
        teachesSkill = "She teaches: Logo Design",
        youTeachSkill = "You teach: PowerPoint",
        message = "“I’d love to exchange these skills this week!”",
        availability = "Sun–Tue • 4:00–8:00 PM"
    ),
    onBackClick: () -> Unit = {},
    onAcceptExchangeClick: () -> Unit = {},
    onConfirmDeclineClick: () -> Unit = {},
    onReportRequestClick: () -> Unit = {},
    onBottomNavClick: (routeName: String) -> Unit = {}
) {
    var showDeclineConfirm by remember { mutableStateOf(true) }
    var selectedTab by remember { mutableStateOf(ReceiverBottomTab.REQUESTS) }

    val screenBackground = Color(0xFFFFFDF8)
    val surfaceWhite = Color(0xFFFFFFFF)
    val cardLavender = Color(0xFFF3EDF7)
    val backButtonBg = Color(0xFFF3EDF7)
    val borderColor = Color(0xFFE7E0EC)
    val primaryPurple = Color(0xFF671AB6)
    val acceptGreen = Color(0xFF065F46)
    val declineRed = Color(0xFFB91C1C)
    val mintBadgeBg = Color(0xFFD1FAE5)
    val mintBadgeText = Color(0xFF065F46)
    val textDark = Color(0xFF1D1B20)
    val textMuted = Color(0xFF6E6A73)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = screenBackground,
        bottomBar = {
            NavigationBar(
                containerColor = surfaceWhite,
                tonalElevation = 0.dp
            ) {
                ReceiverBottomTab.entries.forEach { tab ->
                    val isSelected = selectedTab == tab
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            selectedTab = tab
                            onBottomNavClick(tab.name.lowercase())
                        },
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label,
                                modifier = Modifier.size(22.dp)
                            )
                        },
                        label = {
                            Text(
                                text = tab.label,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                maxLines = 1
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = primaryPurple,
                            selectedTextColor = primaryPurple,
                            indicatorColor = cardLavender,
                            unselectedIconColor = textMuted,
                            unselectedTextColor = textMuted
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            // Top bar: Back button + Brand title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    onClick = onBackClick,
                    modifier = Modifier.size(44.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = backButtonBg
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = primaryPurple
                        )
                    }
                }

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = "Maharti",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    color = primaryPurple
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Screen Title & Subtitle
            Text(
                text = "New exchange request",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                ),
                color = textDark
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${requestData.senderName.substringBefore(" ")} wants to swap skills with you.",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                color = textMuted
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sender & Skill Exchange Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = cardLavender
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = requestData.senderName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = textDark
                        )
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = textDark,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = requestData.badgeText,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = textDark
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = requestData.majorAndRating.substringBefore("★").trimEnd(' ', '•'),
                            fontSize = 12.sp,
                            color = textMuted
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = textMuted,
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = requestData.majorAndRating.substringAfter("★", "").trim(),
                            fontSize = 12.sp,
                            color = textMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = requestData.teachesSkill,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryPurple,
                            maxLines = 1
                        )

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.CompareArrows,
                            contentDescription = null,
                            tint = primaryPurple,
                            modifier = Modifier.size(18.dp)
                        )

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = mintBadgeBg
                        ) {
                            Text(
                                text = requestData.youTeachSkill,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = mintBadgeText,
                                maxLines = 1,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = requestData.message,
                        fontSize = 13.sp,
                        fontStyle = FontStyle.Italic,
                        color = textMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Availability Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = surfaceWhite,
                shadowElevation = 1.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Availability",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textDark
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = requestData.availability,
                        fontSize = 13.sp,
                        color = textMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Accept Exchange Button
            Button(
                onClick = onAcceptExchangeClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = acceptGreen,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                Text(
                    text = "Accept Exchange",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Decline Button
            Button(
                onClick = { showDeclineConfirm = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = declineRed,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                Text(
                    text = "Decline",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Report Link
            Text(
                text = "Report this request",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = declineRed,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clickable { onReportRequestClick() }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Inline Decline Confirmation Box
            if (showDeclineConfirm) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFFAF7FC),
                    shadowElevation = 1.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Decline this exchange request?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = textDark
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = "Cancel",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = declineRed,
                                modifier = Modifier.clickable { showDeclineConfirm = false }
                            )
                            Text(
                                text = "•",
                                fontSize = 13.sp,
                                color = declineRed
                            )
                            Text(
                                text = "Yes, decline",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = declineRed,
                                modifier = Modifier.clickable {
                                    showDeclineConfirm = false
                                    onConfirmDeclineClick()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ExchangeRequestReceiverScreenPreview() {
    ExchangeRequestReceiverScreen()
}

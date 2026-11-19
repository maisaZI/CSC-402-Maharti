package com.example.maharti.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maharti.ui.theme.Cream
import com.example.maharti.ui.theme.Gold
import com.example.maharti.ui.theme.Ink
import com.example.maharti.ui.theme.Lavender
import com.example.maharti.ui.theme.MahartiTheme
import com.example.maharti.ui.theme.Mint
import com.example.maharti.ui.theme.MutedText
import com.example.maharti.ui.theme.Purple

enum class NotificationType { EXCHANGE_REQUEST, SESSION_REMINDER, REVIEW }

data class NotificationItem(
    val type: NotificationType,
    val title: String,
    val body: String,
    val time: String,
    val unread: Boolean = false
)

val sampleNotifications = listOf(
    NotificationItem(NotificationType.EXCHANGE_REQUEST, "New exchange request", "Layan wants to learn PowerPoint and teach you Logo Design.", "2m ago", unread = true),
    NotificationItem(NotificationType.SESSION_REMINDER, "Session reminder", "Your session with Sara starts tomorrow at 5:30 PM.", "1h ago", unread = true),
    NotificationItem(NotificationType.REVIEW, "Rate your exchange", "Your exchange with Noura is complete. Share your review.", "Yesterday")
)

@Composable
fun NotificationsScreen(
    notifications: List<NotificationItem> = sampleNotifications,
    onBackClick: () -> Unit = {},
    onExchangeRequestClick: () -> Unit = {},
    onReviewClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Cream)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(Modifier.height(24.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onBackClick,
                colors = IconButtonDefaults.iconButtonColors(containerColor = Lavender)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Purple)
            }
            Spacer(Modifier.width(16.dp))
            Column {
                Text("Notifications", color = Ink, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Text("Updates on your exchanges and sessions", color = MutedText, fontSize = 13.sp)
            }
        }
        Spacer(Modifier.height(24.dp))

        if (notifications.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.size(88.dp).background(Lavender, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Notifications, contentDescription = null, tint = Purple, modifier = Modifier.size(40.dp))
                }
                Spacer(Modifier.height(16.dp))
                Text("No notifications yet", color = Ink, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Text(
                    "Requests, reminders and reviews will appear here.",
                    color = MutedText,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(notifications) { item ->
                    NotificationCard(
                        item = item,
                        onClick = when (item.type) {
                            NotificationType.EXCHANGE_REQUEST -> onExchangeRequestClick
                            NotificationType.REVIEW -> onReviewClick
                            NotificationType.SESSION_REMINDER -> ({})
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun NotificationCard(item: NotificationItem, onClick: () -> Unit) {
    val (icon, iconBg) = notificationIcon(item.type)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (item.unread) Lavender else Color.White, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier.size(44.dp).background(iconBg, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = Purple)
        }
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(item.title, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            Spacer(Modifier.height(4.dp))
            Text(item.body, color = MutedText, fontSize = 13.sp)
            Spacer(Modifier.height(6.dp))
            Text(item.time, color = Purple, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        }
        if (item.unread) {
            Box(Modifier.padding(top = 6.dp).size(10.dp).background(Gold, CircleShape))
        }
    }
}

private fun notificationIcon(type: NotificationType): Pair<ImageVector, Color> = when (type) {
    NotificationType.EXCHANGE_REQUEST -> Icons.Filled.SwapHoriz to Mint
    NotificationType.SESSION_REMINDER -> Icons.Filled.CalendarMonth to Color(0xFFE5F2FF)
    NotificationType.REVIEW -> Icons.Filled.Star to Color(0xFFFFF1D6)
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun NotificationsScreenPreview() {
    MahartiTheme { NotificationsScreen() }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun NotificationsEmptyPreview() {
    MahartiTheme { NotificationsScreen(notifications = emptyList()) }
}

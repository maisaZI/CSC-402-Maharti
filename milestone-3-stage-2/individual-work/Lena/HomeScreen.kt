package edu.iau.cshj.csc402.navbar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke
import edu.iau.cshj.csc402.navbar.ui.theme.BrandPurple
import edu.iau.cshj.csc402.navbar.ui.theme.CardLavender
import edu.iau.cshj.csc402.navbar.ui.theme.MutedText
import edu.iau.cshj.csc402.navbar.ui.theme.PrimaryDarkText
import edu.iau.cshj.csc402.navbar.ui.theme.ScreenBackground
import edu.iau.cshj.csc402.navbar.ui.theme.SoftMint

@Composable
fun HomeScreen(
    onNavigateToSearch: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Top Header with Greeting and Notification Badge
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hi, Sara ✨",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryDarkText
            )

            Box(
                modifier = Modifier.size(48.dp)
            ) {
                IconButton(
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White),
                    modifier = Modifier
                        .size(44.dp)
                        .align(Alignment.Center)
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = BrandPurple
                    )
                }
                // Notification badge count
                Badge(
                    containerColor = Color(0xFFE57373),
                    contentColor = Color.White,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Text("3", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pastel Banner ("Share what you know")
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFEAE1F8),
                                Color(0xFFFBF4DC)
                            )
                        )
                    )
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Share what you know.",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3F2B63)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Learn what you love.",
                            fontSize = 15.sp,
                            color = MutedText
                        )
                    }
                    // Decorative illustration box / placeholder icon
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(BrandPurple.copy(alpha = 0.1f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SwapHoriz,
                            contentDescription = null,
                            tint = BrandPurple,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Find students to learn with and teach.",
            fontSize = 14.sp,
            color = MutedText
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Your Skill Exchange Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "YOUR SKILL EXCHANGE",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandPurple
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = CardLavender,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Teach: PowerPoint",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = BrandPurple,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(ScreenBackground, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SwapHoriz,
                            contentDescription = "Swap",
                            tint = BrandPurple,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = SoftMint,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Learn: Logo Design",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF006064),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BrandPurple)
                ) {
                    Text(
                        text = "Find My Match →",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                TextButton(onClick = {}) {
                    Text(
                        text = "Edit my skills",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BrandPurple
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Search Field
        Text(
            text = "Search",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = MutedText
        )
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            onClick = onNavigateToSearch,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            border = BorderStroke(1.dp, Color(0xFFE0D8EC))
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = BrandPurple
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "What would you like to learn?",
                    color = Color(0xFF9E9E9E)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Explore Skills
        Text(
            text = "Explore skills",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryDarkText
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val categories = listOf(
                Pair("Programming", CardLavender),
                Pair("Design", Color(0xFFFCE4EC)),
                Pair("Languages", Color(0xFFFFFDE7)),
                Pair("Mathematics", Color(0xFFE8F5E9)),
                Pair("Business", Color(0xFFE3F2FD))
            )

            categories.forEach { (cat, bg) ->
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = bg,
                    modifier = Modifier.height(40.dp)
                ) {
                    Box(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cat,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF3F2B63)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Recommended Students
        Text(
            text = "Recommended students",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryDarkText
        )

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Layan Ahmed",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryDarkText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFB300),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "4.9 • Graphic Design",
                        fontSize = 13.sp,
                        color = MutedText
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = CardLavender
                ) {
                    Text(
                        text = "Teaches Logo Design",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BrandPurple,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Why you match",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandPurple
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Layan wants PowerPoint, which you teach. She teaches Logo Design, which you want to learn.",
                    fontSize = 13.sp,
                    color = MutedText,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

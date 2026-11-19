package com.example.maharti.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maharti.ui.theme.MahartiTheme

val ProfileWarmCream = Color(0xFFFCF8F2)
val ProfilePurpleAccent = Color(0xFF5F1BA9)
val ProfileDarkPlum = Color(0xFF281E39)
val ProfileMutedPurpleGray = Color(0xFF51465E)
val ProfileLightLavenderChip = Color(0xFFF2ECFC)
val ProfileLavenderReviewCard = Color(0xFFF2ECFC)
val ProfileMintBadgeBg = Color(0xFFDFF8F3)
val ProfileMintBadgeText = Color(0xFF00695F)
val ProfileCardWhite = Color(0xFFFFFFFF)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReceiverProfileScreen(
    onBackClick: () -> Unit = {},
    onRequestExchange: () -> Unit = {},
    onSeeAllReviews: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ProfileWarmCream)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(ProfileCardWhite)
                .clickable(onClick = onBackClick),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = ProfileDarkPlum,
                modifier = Modifier.size(20.dp),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .clip(CircleShape)
                    .background(ProfileLightLavenderChip),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "LA",
                    color = ProfilePurpleAccent,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "Layan Ahmed",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProfileDarkPlum,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = "Graphic Design",
                        fontSize = 14.sp,
                        color = ProfileMutedPurpleGray,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        text = "•",
                        fontSize = 14.sp,
                        color = ProfileMutedPurpleGray,
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFB300),
                        modifier = Modifier.size(14.dp),
                    )
                    Text(
                        text = "4.9 (24 reviews)",
                        fontSize = 14.sp,
                        color = ProfileMutedPurpleGray,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }

        Surface(
            shape = RoundedCornerShape(50.dp),
            color = ProfileMintBadgeBg,
            modifier = Modifier.wrapContentWidth(),
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = "✓",
                    color = ProfileMintBadgeText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )
                Text(
                    text = "Verified University Student",
                    color = ProfileMintBadgeText,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = "Skills I Offer",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ProfileDarkPlum,
            )

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = ProfileCardWhite,
                shadowElevation = 2.dp,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Text(text = "✨", fontSize = 14.sp)
                        Text(
                            text = "GREAT MATCH FOR YOU",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = ProfilePurpleAccent,
                            letterSpacing = 0.5.sp,
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ProfileLightLavenderChip, RoundedCornerShape(12.dp))
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "You want",
                                fontSize = 11.sp,
                                color = ProfileMutedPurpleGray,
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = ProfileCardWhite,
                            ) {
                                Text(
                                    text = "Logo Design",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = ProfileDarkPlum,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                )
                            }
                        }

                        Text(
                            text = "⇄",
                            fontSize = 18.sp,
                            color = ProfilePurpleAccent,
                            modifier = Modifier.padding(horizontal = 8.dp),
                        )

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.End,
                        ) {
                            Text(
                                text = "Layan wants",
                                fontSize = 11.sp,
                                color = ProfileMutedPurpleGray,
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = ProfileMintBadgeBg,
                            ) {
                                Text(
                                    text = "PowerPoint",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = ProfileMintBadgeText,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                )
                            }
                        }
                    }
                }
            }

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ProfileSkillChip(text = "Logo Design")
                ProfileSkillChip(text = "Figma")
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = "Skills I Want to Learn",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ProfileDarkPlum,
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ProfileSkillChip(text = "PowerPoint")
                ProfileSkillChip(text = "Excel")
            }
        }

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = ProfileLavenderReviewCard,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Student review",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = ProfileDarkPlum,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFB300),
                            modifier = Modifier.size(16.dp),
                        )
                        Text(
                            text = "5.0",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = ProfileDarkPlum,
                        )
                    }
                }

                Text(
                    text = "“Friendly, patient and creative.”",
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic,
                    color = ProfileDarkPlum,
                )

                Text(
                    text = "See all reviews →",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ProfilePurpleAccent,
                    modifier = Modifier
                        .clickable(onClick = onSeeAllReviews)
                        .padding(top = 2.dp),
                )
            }
        }

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = ProfileCardWhite,
            shadowElevation = 1.dp,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = "Availability",
                        fontSize = 12.sp,
                        color = ProfileMutedPurpleGray,
                    )
                    Text(
                        text = "Usually available",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = ProfileDarkPlum,
                    )
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = ProfileLightLavenderChip,
                ) {
                    Text(
                        text = "Sun–Tue • 4:00–8:00 PM",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = ProfilePurpleAccent,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    )
                }
            }
        }

        Button(
            onClick = onRequestExchange,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ProfilePurpleAccent),
        ) {
            Text(
                text = "Request Exchange",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Report profile",
                fontSize = 13.sp,
                color = ProfileMutedPurpleGray,
                modifier = Modifier.clickable { },
            )
        }
    }
}

@Composable
fun ProfileSkillChip(text: String) {
    Surface(
        shape = RoundedCornerShape(50.dp),
        color = ProfileLightLavenderChip,
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = ProfilePurpleAccent,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
        )
    }
}

@Preview(widthDp = 390, heightDp = 844, showBackground = true)
@Composable
fun ReceiverProfileScreenPreview() {
    MahartiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = ProfileWarmCream,
        ) {
            ReceiverProfileScreen()
        }
    }
}
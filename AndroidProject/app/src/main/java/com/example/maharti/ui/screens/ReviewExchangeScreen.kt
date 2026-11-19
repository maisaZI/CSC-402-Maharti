package com.example.maharti.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maharti.ui.theme.MahartiTheme

val ReviewBgWarmCream = Color(0xFFFCF8F2)
val ReviewPurpleAccent = Color(0xFF5F1BA9)
val ReviewMainText = Color(0xFF281E39)
val ReviewSecondaryText = Color(0xFF51465E)
val ReviewLavenderCard = Color(0xFFF2ECFC)
val ReviewCardWhite = Color(0xFFFFFFFF)
val ReviewBorderLavender = Color(0xFFE8E2EE)

@Composable
fun ReviewExchangeScreen(
    onBackClick: () -> Unit = {},
    onReviewSubmitted: () -> Unit = {},
) {
    var teachingRating by remember { mutableIntStateOf(0) }
    var communicationRating by remember { mutableIntStateOf(0) }
    var reviewText by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ReviewBgWarmCream)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(ReviewCardWhite)
                    .clickable(onClick = onBackClick),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = ReviewMainText,
                    modifier = Modifier.size(20.dp),
                )
            }
            Text(
                text = "Maharti",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = ReviewPurpleAccent,
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = "How was your exchange?",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = ReviewMainText,
            )
            Text(
                text = "Your review helps students find great skill partners.",
                fontSize = 14.sp,
                color = ReviewSecondaryText,
            )
        }

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = ReviewLavenderCard,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(ReviewCardWhite),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "⇄",
                        fontSize = 20.sp,
                        color = ReviewPurpleAccent,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = "Logo Design ⇌ PowerPoint",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = ReviewMainText,
                    )
                    Text(
                        text = "Exchange completed with Layan ✓",
                        fontSize = 13.sp,
                        color = ReviewSecondaryText,
                    )
                }
            }
        }

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = ReviewCardWhite,
            border = BorderStroke(1.dp, ReviewBorderLavender),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Teaching",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = ReviewMainText,
                    )
                    StarRatingRow(
                        rating = teachingRating,
                        onRatingChanged = {
                            teachingRating = it
                            errorMessage = null
                        },
                    )
                }

                HorizontalDivider(
                    thickness = 1.dp,
                    color = ReviewBorderLavender,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Communication",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = ReviewMainText,
                    )
                    StarRatingRow(
                        rating = communicationRating,
                        onRatingChanged = {
                            communicationRating = it
                            errorMessage = null
                        },
                    )
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Your review",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ReviewMainText,
                )
                Text(
                    text = "Optional",
                    fontSize = 13.sp,
                    color = ReviewSecondaryText,
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(ReviewCardWhite)
                    .border(1.dp, ReviewBorderLavender, RoundedCornerShape(16.dp))
                    .padding(16.dp),
            ) {
                if (reviewText.isEmpty()) {
                    Text(
                        text = "Write a short review...",
                        fontSize = 14.sp,
                        color = ReviewSecondaryText.copy(alpha = 0.6f),
                    )
                }
                BasicTextField(
                    value = reviewText,
                    onValueChange = {
                        if (it.length <= 300) {
                            reviewText = it
                        }
                    },
                    modifier = Modifier.fillMaxSize(),
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        color = ReviewMainText,
                    ),
                    enabled = !isSubmitted,
                )
                Text(
                    text = "${reviewText.length} / 300",
                    fontSize = 12.sp,
                    color = ReviewSecondaryText,
                    modifier = Modifier.align(Alignment.BottomEnd),
                )
            }
        }

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                fontSize = 13.sp,
                color = Color(0xFFD32F2F),
                fontWeight = FontWeight.Medium,
            )
        }

        if (isSubmitted) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFE8F5E9),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Review submitted successfully!",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32),
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }

        Button(
            onClick = {
                if ((teachingRating == 0) || (communicationRating == 0)) {
                    errorMessage = "Please provide both Teaching and Communication ratings before submitting."
                } else {
                    errorMessage = null
                    isSubmitted = true
                    onReviewSubmitted()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ReviewPurpleAccent,
                disabledContainerColor = ReviewPurpleAccent.copy(alpha = 0.6f),
            ),
            enabled = !isSubmitted,
        ) {
            Text(
                text = if (isSubmitted) "Submitted" else "Submit Review",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )
        }
    }
}

@Composable
fun StarRatingRow(
    rating: Int,
    onRatingChanged: (Int) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 1..5) {
            IconButton(
                onClick = { onRatingChanged(i) },
                modifier = Modifier.size(32.dp),
            ) {
                Icon(
                    imageVector = if (i <= rating) Icons.Default.Star else Icons.Outlined.StarBorder,
                    contentDescription = "$i Star",
                    tint = ReviewPurpleAccent,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

@Preview(widthDp = 390, heightDp = 844, showBackground = true)
@Composable
fun ReviewExchangeScreenPreview() {
    MahartiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = ReviewBgWarmCream,
        ) {
            ReviewExchangeScreen()
        }
    }
}
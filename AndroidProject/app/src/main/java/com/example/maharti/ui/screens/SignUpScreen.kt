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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    majors: List<String> = listOf(
        "Computer Science",
        "Information Technology",
        "Software Engineering",
        "Cybersecurity"
    ),
    onBackClick: () -> Unit = {},
    onCreateAccountClick: (
        fullName: String,
        universityEmail: String,
        major: String,
        password: String,
        confirmPassword: String
    ) -> Unit = { _, _, _, _, _ -> },
    onLoginClick: () -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var universityEmail by remember { mutableStateOf("") }
    var selectedMajor by remember { mutableStateOf(majors.firstOrNull().orEmpty()) }
    var majorMenuExpanded by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val screenBackground = Color(0xFFFFFDF8)
    val surfaceWhite = Color(0xFFFFFFFF)
    val backButtonBg = Color(0xFFF3EDF7)
    val borderColor = Color(0xFFE7E0EC)
    val primaryPurple = Color(0xFF671AB6)
    val textDark = Color(0xFF1D1B20)
    val textMuted = Color(0xFF6E6A73)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(screenBackground)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // Top Header: Back Button + Title + Subtitle
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

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Create your account",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    color = textDark,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Join a campus community built around sharing.",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                    color = textMuted,
                    textAlign = TextAlign.Center
                )
            }

            // Spacer to keep title centered relative to back button
            Spacer(modifier = Modifier.size(44.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Full Name
        FieldLabel(text = "Full Name", color = textDark)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            placeholder = {
                Text(
                    text = "Enter full name",
                    color = textMuted,
                    fontSize = 14.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = surfaceWhite,
                unfocusedContainerColor = surfaceWhite,
                focusedBorderColor = primaryPurple,
                unfocusedBorderColor = borderColor,
                focusedTextColor = textDark,
                unfocusedTextColor = textDark
            )
        )

        Spacer(modifier = Modifier.height(14.dp))

        // University Email
        FieldLabel(text = "University Email", color = textDark)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = universityEmail,
            onValueChange = { universityEmail = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            placeholder = {
                Text(
                    text = "Enter university email",
                    color = textMuted,
                    fontSize = 14.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = surfaceWhite,
                unfocusedContainerColor = surfaceWhite,
                focusedBorderColor = primaryPurple,
                unfocusedBorderColor = borderColor,
                focusedTextColor = textDark,
                unfocusedTextColor = textDark
            )
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Major Dropdown Layout
        FieldLabel(text = "Major", color = textDark)
        Spacer(modifier = Modifier.height(6.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedMajor,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { majorMenuExpanded = true },
                enabled = false,
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select major",
                        tint = textMuted
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledContainerColor = surfaceWhite,
                    disabledBorderColor = borderColor,
                    disabledTextColor = textDark
                )
            )

            // Invisible clickable overlay so tapping anywhere opens the menu
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { majorMenuExpanded = true }
            )

            DropdownMenu(
                expanded = majorMenuExpanded,
                onDismissRequest = { majorMenuExpanded = false },
                modifier = Modifier.background(surfaceWhite)
            ) {
                majors.forEach { majorOption ->
                    DropdownMenuItem(
                        text = { Text(text = majorOption, color = textDark) },
                        onClick = {
                            selectedMajor = majorOption
                            majorMenuExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Password
        FieldLabel(text = "Password", color = textDark)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            visualTransformation = PasswordVisualTransformation(),
            placeholder = {
                Text(
                    text = "Enter password",
                    color = textMuted,
                    fontSize = 14.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = surfaceWhite,
                unfocusedContainerColor = surfaceWhite,
                focusedBorderColor = primaryPurple,
                unfocusedBorderColor = borderColor,
                focusedTextColor = textDark,
                unfocusedTextColor = textDark
            )
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "8+ characters • 1 number • 1 uppercase letter",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
            color = textMuted
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Confirm Password
        FieldLabel(text = "Confirm Password", color = textDark)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            visualTransformation = PasswordVisualTransformation(),
            placeholder = {
                Text(
                    text = "Enter confirm password",
                    color = textMuted,
                    fontSize = 14.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = surfaceWhite,
                unfocusedContainerColor = surfaceWhite,
                focusedBorderColor = primaryPurple,
                unfocusedBorderColor = borderColor,
                focusedTextColor = textDark,
                unfocusedTextColor = textDark
            )
        )

        Spacer(modifier = Modifier.height(22.dp))

        // Primary CTA Button
        Button(
            onClick = {
                onCreateAccountClick(
                    fullName,
                    universityEmail,
                    selectedMajor,
                    password,
                    confirmPassword
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryPurple,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Footer Link: Already have an account? Log in
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account? ",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                color = primaryPurple
            )
            Text(
                text = "Log in",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                ),
                color = primaryPurple,
                modifier = Modifier.clickable { onLoginClick() }
            )
        }
    }
}

@Composable
private fun FieldLabel(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp
        ),
        color = color,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}

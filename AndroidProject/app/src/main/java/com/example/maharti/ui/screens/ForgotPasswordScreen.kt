package com.example.maharti.ui.screens

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
import com.example.maharti.ui.theme.*

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun ForgotPasswordScreen(onBackToLogin: () -> Unit = {}, onRequestReset: (String) -> Unit = {}) {
    var email by remember { mutableStateOf("") }
    var attempted by remember { mutableStateOf(false) }
    var requested by remember { mutableStateOf(false) }
    val valid = android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() && email.trim().endsWith("@iau.edu.sa", ignoreCase = true)
    Column(Modifier.fillMaxSize().background(Cream).padding(horizontal = 28.dp)) {
        Spacer(Modifier.height(24.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(onClick = onBackToLogin, shape = RoundedCornerShape(13.dp), color = LightPurple) {
                Box(Modifier.size(46.dp), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Purple)
                }
            }
            Spacer(Modifier.width(25.dp))
            Text("Maharti", color = Purple, fontSize = 23.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(22.dp))
        Text("Forgot your password?", color = DarkText, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(14.dp))
        Text("Enter your university email and we’ll send you a secure reset link.", color = Gray, fontSize = 14.sp, lineHeight = 20.sp)
        Spacer(Modifier.height(18.dp))
        Text("University Email", color = DarkText, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        Spacer(Modifier.height(5.dp))
        OutlinedTextField(value = email, onValueChange = { email = it; attempted = false; requested = false },
            modifier = Modifier.fillMaxWidth(), singleLine = true, placeholder = { Text("student@iau.edu.sa") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = attempted && !valid, shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White, focusedBorderColor = Purple,
                unfocusedBorderColor = LightPurple))
        if (attempted && !valid) Text("Enter a valid @iau.edu.sa university email.", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        Spacer(Modifier.height(16.dp))
        Button(onClick = { attempted = true; if (valid) { requested = true; onRequestReset(email.trim()) } },
            modifier = Modifier.fillMaxWidth().height(53.dp), shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Purple)) {
            Text("Send Reset Link", fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(18.dp))
        Column(Modifier.fillMaxWidth().background(LightPurple, RoundedCornerShape(20.dp)).padding(18.dp)) {
            Text("✉  Check your university inbox", color = Purple, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(Modifier.height(8.dp))
            Text("The reset link will expire for your security.", color = Gray, fontSize = 12.sp)
            if (requested) {
                Spacer(Modifier.height(8.dp))
                Text("Request submitted to the app callback; email delivery requires backend integration.", color = Purple, fontSize = 11.sp)
            }
        }
    }
}

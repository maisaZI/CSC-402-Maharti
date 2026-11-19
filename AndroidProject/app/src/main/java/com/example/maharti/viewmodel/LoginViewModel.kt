package com.example.maharti.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val message: String = "Ready to login"
)

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                emailError = null
            )
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                passwordError = null
            )
        }
    }

    fun login(onSuccess: () -> Unit) {
        val currentState = _uiState.value

        val emailValid = currentState.email.endsWith(".edu", ignoreCase = true) ||
                currentState.email.endsWith(".edu.sa", ignoreCase = true) ||
                currentState.email.contains("@")

        val passwordValid = currentState.password.isNotBlank()

        if (!emailValid || !passwordValid) {
            _uiState.update {
                it.copy(
                    emailError = if (!emailValid) "Must use a valid university email" else null,
                    passwordError = if (!passwordValid) "Password is required" else null,
                    message = "Please check your information"
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    message = "Logging in..."
                )
            }

            delay(1000)

            _uiState.update {
                it.copy(
                    isLoading = false,
                    isSuccess = true,
                    message = "Login successful"
                )
            }
            onSuccess()
        }
    }
}

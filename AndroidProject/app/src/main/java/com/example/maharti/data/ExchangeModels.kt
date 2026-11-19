package com.example.maharti.data

/** Acceptance must come from the recipient/backend for the current request ID. */
enum class ExchangeStatus { IDLE, SENDING, PENDING, ACCEPTED, FAILED }
data class ExchangeRequest(val id: String, val wanted: String, val offered: String)
data class SessionChoice(val date: String, val time: String, val location: String)

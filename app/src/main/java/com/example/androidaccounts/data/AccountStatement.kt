package com.example.androidaccounts.data

data class AccountStatement(
    val transactionType: String, // "Deposit" or "Withdraw"
    val date: String,           // e.g., "2025-05-01"
    val description: String,    // e.g., "Grocery Payment"
    val amount: Double,         // e.g., 50.75
    val balanceAfter: Double,   // e.g., 1050.25
    val currency: String        // e.g., "KWD"
)
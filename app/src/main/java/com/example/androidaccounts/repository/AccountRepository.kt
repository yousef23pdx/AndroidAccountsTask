package com.example.androidaccounts.repository

import com.example.androidaccounts.data.AccountStatement

object AccountRepository {
    object AccountRepo {
        val statementsList = listOf(
            AccountStatement(
                transactionType = "Deposit",
                date = "2025-05-01",
                description = "Salary Deposit",
                amount = 1000.0,
                balanceAfter = 2000.0,
                currency = "KWD"
            ),
            AccountStatement(
                transactionType = "Withdraw",
                date = "2025-05-03",
                description = "Grocery Payment",
                amount = 50.75,
                balanceAfter = 1949.25,
                currency = "KWD"
            ),
            AccountStatement(
                transactionType = "Deposit",
                date = "2025-05-05",
                description = "Freelance Payment",
                amount = 300.0,
                balanceAfter = 2249.25,
                currency = "KWD"
            ),
            AccountStatement(
                transactionType = "Withdraw",
                date = "2025-05-07",
                description = "Utility Bill",
                amount = 100.0,
                balanceAfter = 2149.25,
                currency = "KWD"
            ),
            AccountStatement(
                transactionType = "Withdraw",
                date = "2025-05-10",
                description = "Restaurant",
                amount = 25.5,
                balanceAfter = 2123.75,
                currency = "KWD"
            ),
            AccountStatement(
                transactionType = "Deposit",
                date = "2025-05-12",
                description = "Deposit from Barrak",
                amount = 500.0,
                balanceAfter = 2623.75,
                currency = "KWD"
            ),
            AccountStatement(
                transactionType = "Withdraw",
                date = "2025-05-15",
                description = "Payment to Mohammed",
                amount = 75.0,
                balanceAfter = 2548.75,
                currency = "KWD"
            ),
            AccountStatement(
                transactionType = "Withdraw",
                date = "2025-05-18",
                description = "Transfer to Abdullah",
                amount = 200.0,
                balanceAfter = 2348.75,
                currency = "KWD"
            ),
            AccountStatement(
                transactionType = "Deposit",
                date = "2025-05-20",
                description = "Gift from Abdulaziz",
                amount = 150.0,
                balanceAfter = 2498.75,
                currency = "KWD"
            ),
            AccountStatement(
                transactionType = "Withdraw",
                date = "2025-05-21",
                description = "Purchase from Asma",
                amount = 30.0,
                balanceAfter = 2468.75,
                currency = "KWD"
            )


        )
    }
}
package com.example.androidaccounts.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidaccounts.data.AccountStatement
import com.example.androidaccounts.repository.AccountRepository
import com.example.androidaccounts.ui.theme.AndroidAccountsTheme
import com.example.androidaccounts.ui.theme.DepositColor
import com.example.androidaccounts.ui.theme.WithdrawColor

// Account Statement Card Composable
@Composable
fun AccountStatementCard(statement: AccountStatement, index: Int) {
    // Alternating background colors based on index, adjusted for light/dark mode
    val backgroundColor = if (index % 2 == 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
        ) {
            // Vertical colored bar to indicate transaction type
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(80.dp)
                    .background(
                        if (statement.transactionType == "Deposit") DepositColor else WithdrawColor
                    )
            )

            // Main content of the card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Row for Date, Description, and Transaction Type/Amount
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left: Date and Description
                    Column {
                        Text(
                            text = statement.date,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                        Text(
                            text = statement.description,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    // Right: Amount and Transaction Type
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "${statement.amount} ${statement.currency}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (statement.transactionType == "Deposit") DepositColor else WithdrawColor,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = statement.transactionType,
                            style = MaterialTheme.typography.bodySmall,
                            color = if (statement.transactionType == "Deposit") DepositColor else WithdrawColor
                        )
                    }
                }

                // Spacer for separation
                Spacer(modifier = Modifier.height(8.dp))

                // Balance After Transaction
                Text(
                    text = "Balance: ${statement.balanceAfter} ${statement.currency}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

// Account Statements List Composable
@Composable
fun AccountStatementsList(statements: List<AccountStatement>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        // Using itemsIndexed to get the index for alternating background colors
        itemsIndexed(statements) { index, statement ->
            AccountStatementCard(statement = statement, index = index)
            // Add a divider between items (except for the last item)
            if (index < statements.size - 1) {
                Divider(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

// Main Screen Composable with Theme Toggle
@Composable
fun AccountStatementsScreen() {
    // State for toggling light/dark mode
    var isDarkTheme by remember { mutableStateOf(false) }

    // Wrap the content in the theme with dynamic darkTheme value
    AndroidAccountsTheme(darkTheme = isDarkTheme) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Toggle Button for Light/Dark Mode
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = { isDarkTheme = !isDarkTheme }) {
                    Text(text = if (isDarkTheme) "Switch to Light Mode" else "Switch to Dark Mode")
                }
            }

            // Wrap the list in a state for potential updates
            val statementsListState by remember { mutableStateOf(AccountRepository.AccountRepo.statementsList) }

            // Display the list
            AccountStatementsList(statements = statementsListState)
        }
    }
}

// Previews for Visualization
@Preview(showBackground = true)
@Composable
fun PreviewAccountStatementCardDepositLight() {
    AndroidAccountsTheme(darkTheme = false) {
        Surface {
            AccountStatementCard(
                statement = AccountStatement(
                    transactionType = "Deposit",
                    date = "2025-05-01",
                    description = "Salary Deposit",
                    amount = 1000.0,
                    balanceAfter = 2000.0,
                    currency = "KWD"
                ),
                index = 0
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountStatementCardWithdrawLight() {
    AndroidAccountsTheme(darkTheme = false) {
        Surface {
            AccountStatementCard(
                statement = AccountStatement(
                    transactionType = "Withdraw",
                    date = "2025-05-03",
                    description = "Grocery Payment",
                    amount = 50.75,
                    balanceAfter = 1949.25,
                    currency = "KWD"
                ),
                index = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountStatementCardDepositDark() {
    AndroidAccountsTheme(darkTheme = true) {
        Surface {
            AccountStatementCard(
                statement = AccountStatement(
                    transactionType = "Deposit",
                    date = "2025-05-01",
                    description = "Salary Deposit",
                    amount = 1000.0,
                    balanceAfter = 2000.0,
                    currency = "KWD"
                ),
                index = 0
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountStatementCardWithdrawDark() {
    AndroidAccountsTheme(darkTheme = true) {
        Surface {
            AccountStatementCard(
                statement = AccountStatement(
                    transactionType = "Withdraw",
                    date = "2025-05-03",
                    description = "Grocery Payment",
                    amount = 50.75,
                    balanceAfter = 1949.25,
                    currency = "KWD"
                ),
                index = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountStatementsListLight() {
    AndroidAccountsTheme(darkTheme = false) {
        Surface {
            AccountStatementsScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountStatementsListDark() {
    AndroidAccountsTheme(darkTheme = true) {
        Surface {
            AccountStatementsScreen()
        }
    }
}
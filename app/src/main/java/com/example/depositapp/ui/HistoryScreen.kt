package com.example.depositapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import com.example.depositapp.R
import com.example.depositapp.roomdatabase.DepositEntry

@Composable
fun HistoryScreen(
    viewModel: DepositViewModel
){
    val deposits by viewModel.deposits.collectAsState(emptyList())
    Scaffold {innerPadding->
    if(deposits.isEmpty()) {
        Text(
            text = "No deposits saved"
        )
    } else {
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(
                deposits
            ) { dep->
                DepositCard(dep,
                    {
                        viewModel.removeDeposit(dep)
                    }
                )
            }
        }
    }
    }
}

@Composable
fun DepositCard(depositEntry: DepositEntry,
                onRemoveButtonClicked: () -> Unit){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(

                ) {
                    Text(
                        text = stringResource(R.string.calculations_result),
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(
                        onRemoveButtonClicked,
                        modifier = Modifier.size(24.dp)) {
                        Icon(
                            modifier = Modifier,
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Deposit Entry Removal",
                            tint = MaterialTheme.colorScheme.error,
                        )
                    }
                }
                Row(modifier = Modifier) {
                    Text(
                        text = stringResource(R.string.total_savings)
                    )
                    Text(
                        text = depositEntry.totalSavings.toString()
                    )
                }
                Divider(
                    modifier = Modifier,
                    thickness = 2.dp
                )
                Row(modifier = Modifier) {
                    Text(
                        text = stringResource(R.string.total_sum)
                    )
                    Text(
                        text = depositEntry.futureValue.toString()
                    )
                }
                Row(modifier = Modifier) {
                    Text(
                        text = stringResource(R.string.total_income)
                    )
                    Text(
                        text = depositEntry.totalIncome.toString()
                    )
                }
                Row(modifier = Modifier) {
                    Text(
                        text = stringResource(R.string.income_precentage)
                    )
                    Text(
                        text = depositEntry.incomeRate.toString()
                    )
                }
            }
        }
    }
}
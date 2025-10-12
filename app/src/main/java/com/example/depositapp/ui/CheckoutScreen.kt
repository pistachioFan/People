/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.depositapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.depositapp.R
import com.example.depositapp.datasource.DataSource
import com.example.depositapp.model.ScreenFields
import com.example.depositapp.model.DepositUiState

@Composable
fun CheckoutScreen(
    depositUiState: DepositUiState,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(R.string.calculations_result),
                    fontWeight = FontWeight.Bold
                )
                Row(modifier = Modifier) {
                    Text(
                        text = stringResource(R.string.total_savings)
                    )
                    Text(
                        text = depositUiState.totalSavings.toString()
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
                        text = depositUiState.futureValue.toString()
                    )
                }
                Row(modifier = Modifier) {
                    Text(
                        text = stringResource(R.string.total_income)
                    )
                    Text(
                        text = depositUiState.totalIncome.toString()
                    )
                }
                Row(modifier = Modifier) {
                    Text(
                        text = stringResource(R.string.income_precentage)
                    )
                    Text(
                        text = depositUiState.incomeRate.toString()
                    )
                }
                Button(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally),
                    onClick = onNextButtonClicked
                ) {
                    Text(
                        text = stringResource(id = R.string.return_to_main_screen)
                    )
                }
            }
        }
    }
}

/*
@Preview
@Composable
fun CheckoutScreenPreview() {
    CheckoutScreen(
        orderUiState = DepositUiState(
            firstScreen = DataSource.firstScreenT,
            sideDish = DataSource.sideDishMenuItems[0],
            accompaniment = DataSource.accompanimentMenuItems[0],
            itemTotalPrice = 15.00,
            orderTax = 1.00,
            orderTotalPrice = 16.00
        ),
        onNextButtonClicked = {},
        onCancelButtonClicked = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState())
    )
}
*/
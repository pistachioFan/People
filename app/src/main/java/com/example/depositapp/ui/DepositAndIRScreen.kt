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

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.depositapp.R
import com.example.depositapp.datasource.DataSource
//import com.example.depositapp.model.MenuItem
import com.example.depositapp.model.ScreenFields

@Composable
fun DepositAndIRScreen( //deposit and interest rate screen
    screenState: ScreenFields,
    textFields: ScreenFields,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onValuesChanged: (ScreenFields) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseMenuScreen(
        screenState = screenState,
        textFields = textFields,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        onValuesChanged = onValuesChanged as (ScreenFields) -> Unit,
        modifier = modifier
    )
}


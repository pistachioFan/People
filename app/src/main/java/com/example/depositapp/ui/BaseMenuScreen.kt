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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.depositapp.R
//import com.example.depositapp.model.
import com.example.depositapp.model.ScreenFields

@Composable
fun BaseMenuScreen(
    screenState: ScreenFields,
    textFields: ScreenFields,
    modifier: Modifier = Modifier,
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    onValuesChanged: (ScreenFields) -> Unit
) {

    var firstTextFieldValue by remember { mutableStateOf(screenState.firstField) }
    var secondTextFieldValue by remember { mutableStateOf(screenState.secondField) }
    //var screenState by remember {mutableStateOf(ScreenFields(screenState.firstField, screenState.secondField))}
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ValidTextField(
                modifier = modifier,
                label = textFields.firstField as String,
                value = firstTextFieldValue,//screenState.firstField as String,
                onValueChange = {
                    firstTextFieldValue = it
                    screenState.firstField = firstTextFieldValue
                    //screenState.secondField = secondTextFieldValue
                    onValuesChanged(screenState)
                }
            )
            ValidTextField(
                modifier = modifier,
                label = textFields.secondField as String,
                value = secondTextFieldValue as String,
                onValueChange = {
                    secondTextFieldValue = it
                    //screenState.firstField = firstTextFieldValue
                    screenState.secondField = secondTextFieldValue
                    onValuesChanged(screenState)
                }
            )


            MenuScreenButtonGroup(
                firstTextFieldValue,
                secondTextFieldValue,
                onCancelButtonClicked = onCancelButtonClicked,
                onNextButtonClicked = {
                    // Assert not null bc next button is not enabled unless selectedItem is not null.

                    onNextButtonClicked()
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}


@Composable
fun ValidTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
//enteredValue: String
){
    TextField(
        value = value,
        label = { Text(label) },
        onValueChange = { newText ->
            if (newText.isEmpty() || newText.matches("-?\\d+(\\.\\d+)?".toRegex())) {
                onValueChange(newText);
            }
        } ,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        )
    )
}
@Composable
fun MenuScreenButtonGroup(
    firstFieldValue: String,
    secondFieldValue: String,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ){
        OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
            Text(stringResource(R.string.cancel).uppercase())
        }
        Button(
            modifier = Modifier.weight(1f),
            // the button is enabled when the user enters values to both fields
            enabled = firstFieldValue.isNotEmpty() && secondFieldValue.isNotEmpty(),
            onClick = onNextButtonClicked
        ) {
            Text(stringResource(R.string.next).uppercase())
        }
    }
}

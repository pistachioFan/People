package com.example.depositapp.ui

import android.widget.NumberPicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.depositapp.auth.LoginReqDC

@Composable
fun LoginScreen(
    onLoginButtonClicked: (LoginReqDC) -> Unit,
    onRegButtonClick: () -> Unit
) {
    var login by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = login,
                label = {"Login"},
                onValueChange = {
                    login = it
                },
                //visualTransformation = PasswordVisualTransformation()
            )
            TextField(
                value = password,
                label = {"Password"},
                onValueChange = {
                    password = it
                },
                visualTransformation = PasswordVisualTransformation()
            )
            Row() {
                Button(
                    onClick = {onRegButtonClick}
                ){
                    Text(
                        text = "Register an account"
                    )
                }
                Button(
                    onClick = { onLoginButtonClicked(LoginReqDC(login, password)) }
                ) {
                    Text(
                        text = "Login"
                    )
                }
            }
        }
    }
}
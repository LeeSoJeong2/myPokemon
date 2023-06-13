package com.kt.startkit.ui.features.login

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kt.startkit.core.base.StateViewModelListener
import com.kt.startkit.ui.features.main.LocalNavigationProvider
import com.kt.startkit.ui.navigator.AppNavigationRoute
import com.kt.startkit.ui.navigator.navigate
import timber.log.Timber

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val navController = LocalNavigationProvider.current

    val state by loginViewModel.viewState.collectAsStateWithLifecycle()
    val idTextState = rememberSaveable {
        mutableStateOf("")
    }
    val passwordTextState = rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        loginViewModel.isAutoLogin()
    }

    when (state) {
        is LoginState.AuthSuccess -> {
            navController.navigate(route = AppNavigationRoute.ROUTE) {
                navController.popBackStack()
                it.graph.setStartDestination(AppNavigationRoute.ROUTE.routeName)
            }
        }
        else -> {
            Column {
                LoginTextField(idTextState, "아이디") { text -> loginViewModel.updateId(text) }
                LoginTextField(passwordTextState, "비밀번호") { text ->
                    loginViewModel.updatePassword(
                        text
                    )
                }
                AutoLoginCheckBox(state) { loginViewModel.updateCheckedBox() }
                SubmitButton(state, onSubmit = { loginViewModel.login() })
            }
        }
    }
}

@Composable
fun AutoLoginCheckBox(state: LoginState, onCheckedChange: (Any) -> Unit) {
    Timber.d("checked ${state.inputs.autoLogin} ${state} ${state.inputs}")
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = state.inputs.autoLogin, onCheckedChange = onCheckedChange)
        ClickableText(
            text = AnnotatedString("자동 로그인"), onClick = onCheckedChange, modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun LoginTextField(
    textState: MutableState<String>,
    title: String,
    onUpdateField: (String) -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onPrimary)
        )
        BasicTextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
                onUpdateField(it)
            },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .border(
                            border = BorderStroke(1.dp, Color.LightGray),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                ) {
                    innerTextField()
                    Divider()
                }
            },
            textStyle = MaterialTheme.typography.body2.copy(color = Color.Black)
        )
    }
}

@Composable
private fun SubmitButton(state: LoginState, onSubmit: () -> Unit) {
    Button(
        enabled = state is LoginState.ValidSuccess,
        onClick = {
            onSubmit()
        },
        modifier = Modifier.background(if (state is LoginState.ValidSuccess) Color.Blue else Color.LightGray)
    ) {
        if (state is LoginState.Loading) {
            CircularProgressIndicator()
        } else {
            Text("로그인")
        }
    }

}

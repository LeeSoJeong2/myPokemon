package com.kt.startkit.ui.features.login

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.domain.usecase.LoginAuthUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usecase: LoginAuthUsecase,
) : StateViewModel<LoginState>(
    initialState = LoginState.Input("", ""),
) {

    private fun isValid(id: String, password: String) {
        viewModelScope.launch {
            if(password.length >= 6) {
                updateState { LoginState.ValidSuccess(id, password) }
            } else {
                updateState { LoginState.Input(id, password) }
            }
        }
    }

    fun updateId(id: String) {
        isValid(id, viewState.value.password)
    }

    fun updatePassword(password: String) {
        isValid(viewState.value.id, password)
    }

    fun login() {
        viewModelScope.launch {
            updateState { LoginState.Loading(viewState.value.id, viewState.value.password) }
            try {
                usecase.checkAuth(viewState.value.id, viewState.value.password)
                updateState { LoginState.AuthSuccess(viewState.value.id, viewState.value.password) }
            }  catch (e: Exception) {
                updateState { LoginState.Fail(viewState.value.id, viewState.value.password) }
            }
        }
    }

}

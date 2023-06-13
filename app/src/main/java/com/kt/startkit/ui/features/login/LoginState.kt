package com.kt.startkit.ui.features.login

import com.kt.startkit.core.base.ViewState

sealed class LoginState : ViewState() {
    abstract val inputs: LoginInputData

    abstract fun copyState(newInputs: LoginInputData): LoginState


    data class Input(override val inputs: LoginInputData) : LoginState() {
        override fun copyState(newInputs: LoginInputData): LoginState {
            return Input(newInputs)
        }
    }

    data class Loading(override val inputs: LoginInputData) : LoginState() {
        override fun copyState(newInputs: LoginInputData): LoginState {
            return Loading(newInputs)

        }
    }

        data class ValidSuccess(override val inputs: LoginInputData) : LoginState() {
            override fun copyState(newInputs: LoginInputData): LoginState {
                return ValidSuccess(newInputs)
            }
        }

        data class AuthSuccess(override val inputs: LoginInputData) : LoginState() {
            override fun copyState(newInputs: LoginInputData): LoginState {
                return AuthSuccess(newInputs)
            }
        }

        data class Fail(override val inputs: LoginInputData) : LoginState() {
            override fun copyState(newInputs: LoginInputData): LoginState {
                return Fail(newInputs)
            }
        }
    }

data class LoginInputData(val id: String, val password: String, val autoLogin: Boolean) {
    fun copyData(
        id: String? = null,
        password: String? = null,
        autoLogin: Boolean? = null
    ): LoginInputData {
        return LoginInputData(
            id = id ?: this.id,
            password = password ?: this.password,
            autoLogin = autoLogin ?: this.autoLogin,
        )
    }
}

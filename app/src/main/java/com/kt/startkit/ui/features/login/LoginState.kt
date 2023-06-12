package com.kt.startkit.ui.features.login

import com.kt.startkit.core.base.ViewState

sealed class LoginState: ViewState() {
    abstract val id: String
    abstract val password: String

    data class Input(override val id: String, override val password: String ): LoginState()
    data class Loading(override val id: String, override val password: String): LoginState()
    data class ValidSuccess(override val id: String, override val password: String): LoginState()
    data class AuthSuccess(override val id: String, override val password: String): LoginState()
    data class Fail(override val id: String, override val password: String): LoginState()

}

package com.kt.startkit.ui.features.login

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.domain.usecase.LoginUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usecase: LoginUsecase,
) : StateViewModel<LoginState>(
    initialState = LoginState.Input(LoginInputData("", "", false)),
) {

    fun isAutoLogin() {
        viewModelScope.launch {
            val isAutoLogin = usecase.isAutoLogin()
            updateState {
                this.copyState(
                    viewState.value.inputs.copyData(
                        autoLogin = isAutoLogin
                    )
                )
            }
        }
    }

    private fun isValid(id: String, password: String) {
        viewModelScope.launch {
            if (password.length >= 6) {
                updateState {
                    LoginState.ValidSuccess(
                        viewState.value.inputs.copyData(
                            id = id,
                            password = password
                        )
                    )
                }
            } else {
                updateState {
                    LoginState.Input(
                        viewState.value.inputs.copyData(
                            id = id,
                            password = password
                        )
                    )
                }
            }
        }
    }

    fun updateCheckedBox() {
        viewModelScope.launch {
            updateState {
                this.copyState(
                    viewState.value.inputs.copyData(
                        autoLogin = !(viewState.value.inputs.autoLogin)
                    )

                )
            }
        }
    }

    fun updateId(id: String) {
        isValid(id, viewState.value.inputs.password)
    }

    fun updatePassword(password: String) {
        isValid(viewState.value.inputs.id, password)
    }

    fun login() {
        viewModelScope.launch {
            updateState {
                LoginState.Loading(
                    viewState.value.inputs.copyData()

                )
            }
            try {
                usecase.checkAuth(viewState.value.inputs.id, viewState.value.inputs.password)
                updateState {
                    LoginState.AuthSuccess(
                        viewState.value.inputs.copyData()
                    )
                }
                /// TODO ❓updateState 후에 suspend 호출 하면.. 어떻게 되지? suspend 호출 후 무시되고 종료될 수도 있나? 왜냐면 바뀐 상태 갖고 네비게이션 하면서 화면 팝할 거니까..흠...
                usecase.setAutoLogin(viewState.value.inputs.autoLogin)
            } catch (e: Exception) {
                updateState {
                    LoginState.Fail(
                        viewState.value.inputs.copyData()
                    )
                }
            }
        }
    }

}

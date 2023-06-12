package com.kt.startkit.domain.usecase

import com.kt.startkit.data.datasource.login.LoginDataSource
import com.kt.startkit.domain.usecase.Usecase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginAuthUsecase(
    private val dataSource: LoginDataSource,
    private val dispatcher: CoroutineDispatcher,
): Usecase {
    suspend fun checkAuth(id: String, password: String) {
            withContext(dispatcher) {
                Thread.sleep(3000)
                dataSource.checkAuth(id, password)
            }
    }
}

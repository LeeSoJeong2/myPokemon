package com.kt.startkit.domain.usecase

import com.kt.startkit.core.datastore.PreferenceDataStore
import com.kt.startkit.data.datasource.login.LoginDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUsecase(
    private val preferences: PreferenceDataStore,
    private val dataSource: LoginDataSource,
    private val dispatcher: CoroutineDispatcher,
) : Usecase {

    suspend fun checkAuth(id: String, password: String) {
        withContext(dispatcher) {
            Thread.sleep(3000)
            dataSource.checkAuth(id, password)
        }
    }

    suspend fun setAutoLogin(isAutoLogIn: Boolean) {
        preferences.updateAutoLogin(autoLogIn = isAutoLogIn)
    }

    suspend fun isAutoLogin(): Boolean {
        return preferences.isAutoLogin().first()
    }
}

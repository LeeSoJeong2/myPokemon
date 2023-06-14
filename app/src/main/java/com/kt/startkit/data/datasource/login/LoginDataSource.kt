package com.kt.startkit.data.datasource.login

import com.kt.startkit.data.ApiService
import com.kt.startkit.data.datasource.DataSource
import dagger.hilt.android.scopes.ViewModelScoped
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@ViewModelScoped
class LoginDataSource @Inject constructor(
    private val apiService: ApiService
): DataSource {
    fun checkAuth(id: String, password: String) {
//        ApiService.invoke(
//
//        )
        try {
            Timber.d(id + "password: " + password)
        } catch(e: Exception) {
            Timber.d("checkAuth: $e")
            throw e
        }
    }
}

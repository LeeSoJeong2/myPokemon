package com.kt.startkit.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber


class PreferenceDataStore(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher,
) {

    private object PrefConstants {
        const val storeKey = "preferences"
        val loggedIn = booleanPreferencesKey("auto_login")
        val showOnBoarding = booleanPreferencesKey("show_onboarding")
    }

    private val Context.dataStore by preferencesDataStore(
        name = PrefConstants.storeKey,
    )

    suspend fun updateAutoLogin(autoLogIn: Boolean) {
        CoroutineScope(dispatcher).launch { // TODO: io 로 감쌀 필요없나? 지동으로 io로 되는건가?
            context.dataStore.edit {
                it[PrefConstants.loggedIn] = autoLogIn
            }
        }
    }

    fun isAutoLogin(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[PrefConstants.loggedIn] ?: false
        }
    }

    fun needToShowOnBoarding(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[PrefConstants.showOnBoarding] ?: true
        }
    }

    suspend fun updateShowOnBoarding() {
        CoroutineScope(dispatcher).launch {
            context.dataStore.edit {
                it[PrefConstants.showOnBoarding] = false
            }
        }
    }
}

package com.kt.startkit.di

import com.kt.startkit.data.ApiService
import com.kt.startkit.data.datasource.login.LoginDataSource
import com.kt.startkit.domain.usecase.LoginAuthUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object LoginModule {
    @ViewModelScoped
    @Provides
    fun provideLoginUsecase(
        dataSource: LoginDataSource,
        @AppDispatchers(AppCoroutineDispatchers.IO) dispatcher: CoroutineDispatcher
    ): LoginAuthUsecase {
        return LoginAuthUsecase(dataSource, dispatcher)
    }

    @ViewModelScoped
    @Provides
    fun provideLoginDatasource(
        apiService: ApiService,
        //@AppDispatchers(AppCoroutineDispatchers.IO) dispatcher: CoroutineDispatcher
    ): LoginDataSource {
        return LoginDataSource(apiService)
    }
}

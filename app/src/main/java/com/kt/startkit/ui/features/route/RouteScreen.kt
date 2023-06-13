package com.kt.startkit.ui.features.route

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.preferencesDataStore
import androidx.hilt.navigation.compose.hiltViewModel
import com.kt.startkit.core.base.StateViewModelListener
import com.kt.startkit.core.logger.Logger
import com.kt.startkit.ui.features.main.LocalNavigationProvider
import com.kt.startkit.ui.features.onboarding.OnBoardingScreen
import com.kt.startkit.ui.navigator.AppNavigationRoute
import com.kt.startkit.ui.navigator.navigate
import timber.log.Timber

@NonRestartableComposable
@Composable
fun RouteScreen(screenViewModel: RouteViewModel = hiltViewModel()) {
    val activity = (LocalContext.current as? Activity)
    val navController = LocalNavigationProvider.current

    LaunchedEffect(key1 = "", block = {
        screenViewModel.fetchInitialData()
    })

    StateViewModelListener(stateViewModel = screenViewModel, listen = {
        when(it) {
            is RouteState.FailToInitialize -> {
                // 앱 실행 실패 팝업
                Logger.e("Fail to start App!!")
                activity?.finish()
            }
            is RouteState.NavigateToMain -> {
                /// root
                navController.navigate(route = AppNavigationRoute.ROOT) {
                    navController.popBackStack()
                    it.graph.setStartDestination(AppNavigationRoute.ROOT.routeName)
                }
            }
            is RouteState.NavigateToOnBoarding -> {
                navController.navigate(route = AppNavigationRoute.ON_BOARDING) {
                    navController.popBackStack()
                    it.graph.setStartDestination(AppNavigationRoute.ON_BOARDING.routeName)
                }
            }
            else -> {
                // do nothing
            }

        }
    })
}

@Composable
fun RouteScreenContent() {
    Box (
        modifier= Modifier.fillMaxSize()
    ){
        Text("Route")
        // Splash
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

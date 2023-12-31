package com.kt.startkit.ui.features.main.root

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kt.startkit.ui.common.AppFinishHandler
import com.kt.startkit.ui.features.main.LocalNavigationProvider

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: RootViewModel = hiltViewModel(),
    appFinishHandler: AppFinishHandler = AppFinishHandler(),
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    BackHandler(enabled = true){
        appFinishHandler.onWillPop(context)
    }

    when (state) {
        is RootViewState.Initial -> {
            viewModel.observeUserProfile()
        }

        is RootViewState.Fetched -> {
            CompositionLocalProvider(LocalNavigationProvider provides navController) {
                RootContentView()
            }
        }

        is RootViewState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material.Text(
                    (state as RootViewState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootContentView() {
    val navController = LocalNavigationProvider.current

    Scaffold(
        bottomBar = {
            if (navController.isShowBottomBar()) {
                RootTabBar(
                    tabBarItems = RootTabBarItem.items(),
                    onNavigateToTab = navController::navigateToMainTap,
                    currentDestination = navController.currentDestination,
                )
            }
        },
    ) { padding ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(padding)
//                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        ) {
            Column(Modifier.fillMaxSize()) {
                // Show the top app bar on top level destinations.
                val destination = navController.currentRootTapBarItem()
                if (destination != null) {
                    CenterAlignedTopAppBar(
                        title = {},
                        actions = {
                            Row {
                                IconButton(onClick = {
                                    // TODO: 즐겨 찾기 화면 으로 이동
                                } ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Favorite,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.onSurface,
                                    )
                                }
                                IconButton(onClick = {
                                    navController.navigate(NavigationRoute.SETTING_GRAPH .routeName)
                                } ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Settings,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.onSurface,
                                    )
                                }
                            }

                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent,
                        ),
                    )
                }

                RootNavHost()
            }
        }
    }
}









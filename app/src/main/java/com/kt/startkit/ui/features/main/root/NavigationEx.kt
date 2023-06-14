package com.kt.startkit.ui.features.main.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions

@Composable
fun NavHostController.isShowBottomBar(): Boolean {
    return when(this.currentDestinationAsState()?.route) {
        // Hom Tab
        NavigationRoute.HOME_GRAPH.routeName -> true
        NavigationRoute.HOME.routeName -> true
        NavigationRoute.HOME_DETAIL.routeName -> true

        // Berry Tab
        NavigationRoute.BERRY_GRAPH.routeName -> true
        NavigationRoute.BERRY.routeName -> true
        NavigationRoute.BERRY_DETAIL.routeName -> true

        else -> false
    }
}

@Composable
fun NavHostController.currentDestinationAsState(): NavDestination? =
    currentBackStackEntryAsState().value?.destination

@Composable
fun NavHostController.currentRootTapBarItem(): RootTabBarItem? {
//    return when (currentDestination?.route) {
    return when (currentDestinationAsState()?.route) {
        NavigationRoute.HOME.routeName -> RootTabBarItem.HOME
//        NavigationRoute.SETTING.routeName -> RootTabBarItem.SETTING
        NavigationRoute.BERRY.routeName -> RootTabBarItem.BERRY
        else -> null
    }
}

fun NavHostController.navigateToMainTap(route: NavigationRoute) {
    val topLevelNavOptions = navOptions {
        // Pop up to the start destination of the graph to

        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

    when (route) {
        NavigationRoute.HOME_GRAPH -> navigateToHome(topLevelNavOptions)
//        NavigationRoute.SETTING_GRAPH -> navigateToSetting(topLevelNavOptions)
        NavigationRoute.BERRY -> navigateToBerry(topLevelNavOptions)
        else -> {} // main tap 외의 route 는 무시한다.
    }
}

/// 현재의 route path 에 입력한 탭이 포함되어 있는지 확인한다.
fun NavDestination?.isMainTabInHierarchy(rootTabBarItem: RootTabBarItem) =
    this?.hierarchy?.any {
        it.route?.contains(rootTabBarItem.route.routeName, true) ?: false
    } ?: false
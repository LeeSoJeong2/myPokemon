package com.kt.startkit.ui.features.main.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.kt.startkit.ui.features.main.LocalNavigationProvider
import com.kt.startkit.ui.features.main.home.HomeScreen
import com.kt.startkit.ui.features.main.home.detail.PokemonDetailScreen
import com.kt.startkit.ui.features.main.setting.SettingScreen
import com.kt.startkit.ui.features.main.setting.notice.NoticeScreen

enum class NavigationRoute(val routeName: String) {
    HOME_GRAPH("/home"),
    HOME("/home/root"),
    HOME_DETAIL("/home/detail"),

    SETTING_GRAPH("/setting"),
    SETTING("/setting/root"),
    SETTING_PROFILE_NAME("/setting/profile_name")
    ;

    companion object {
        const val HOME_DETAIL_NAME = "home_detail_name"
    }
}

@Composable
fun RootNavHost() {
    val navController = LocalNavigationProvider.current

    NavHost(
        navController = navController,
        startDestination = NavigationRoute.HOME_GRAPH.routeName,
    ) {
        homeGraph(navController = navController)
        settingGraph(navController = navController)
    }
}


/// HomeScreen
fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(NavigationRoute.HOME_GRAPH.routeName, navOptions)

    backQueue.lastOrNull()?.arguments?.apply {
        putString("name", "who a f u?")
    }
}

fun NavGraphBuilder.homeGraph(
    navController: NavController,
) {
    navigation(
        route = NavigationRoute.HOME_GRAPH.routeName,
        startDestination = NavigationRoute.HOME.routeName,
    ) {

        composable(route = NavigationRoute.HOME.routeName) {
            HomeScreen(onPokemonClick = { name ->
                navController.navigate("${NavigationRoute.HOME_DETAIL.routeName}/$name")
            })
        }
        composable(
            route = "${NavigationRoute.HOME_DETAIL.routeName}/{${NavigationRoute.HOME_DETAIL_NAME}}",
            arguments = listOf(
                navArgument(NavigationRoute.HOME_DETAIL_NAME) { type = NavType.StringType }
            )
        ) {
            val name = it.arguments?.getString(NavigationRoute.HOME_DETAIL_NAME)

            PokemonDetailScreen(
                name = name,
                onBackClick = navController::popBackStack,

            )
        }
    }
}

/// SettingScreen
fun NavController.navigateToSetting(navOptions: NavOptions? = null) {
    navigate(NavigationRoute.SETTING_GRAPH.routeName, navOptions)
}

fun NavGraphBuilder.settingGraph(
    navController: NavController,
) {
    navigation(
        route = NavigationRoute.SETTING_GRAPH.routeName,
        startDestination = NavigationRoute.SETTING.routeName,
    ) {

        composable(route = NavigationRoute.SETTING.routeName) {
            SettingScreen(
                onItemClick = { route ->
                    navController.navigateToSettingItem(route)
                },
            )
        }
        composable(route = NavigationRoute.SETTING_PROFILE_NAME.routeName) {
            NoticeScreen(
                onBackClick = navController::popBackStack
            )
        }
    }
}

fun NavController.navigateToSettingItem(route: String) {
//    val encodedId = Uri.encode(itemId)
//    this.navigate("topic_route/$encodedId")
//    this.navigate("setting/$itemId")
    this.navigate(route)
}

//fun NavGraphBuilder.settingItemScreen(
//    onBackClick: () -> Unit,
////    onItemClick: (String) -> Unit,
//) {
//    composable(
//        route = NavigationRoute.SETTING_PROFILE_NAME.routeName,
////        route = "topic_route/{$topicIdArg}",
////        arguments = listOf(
////            navArgument(topicIdArg) { type = NavType.StringType },
////        ),
//    ) {
//        NoticeScreen(
//            onBackClick = onBackClick
//        )
//    }
//}





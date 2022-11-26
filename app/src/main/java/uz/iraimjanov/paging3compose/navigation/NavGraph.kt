package uz.iraimjanov.paging3compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import uz.iraimjanov.paging3compose.screens.home.HomeScreen
import uz.iraimjanov.paging3compose.screens.search.SearchScreen
import uz.iraimjanov.paging3compose.screens.search.SearchViewModel

@ExperimentalAnimationApi
@ExperimentalPagingApi
@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(searchViewModel: SearchViewModel, navHostController: NavHostController) {
    AnimatedNavHost(navController = navHostController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route,
//            exitTransition = {
//                slideOutHorizontally(
//                    targetOffsetX = { -300 },
//                    animationSpec = tween(
//                        durationMillis = 300,
//                        easing = FastOutSlowInEasing
//                    )
//                ) + fadeOut(animationSpec = tween(durationMillis = 300))
//            },
//            popEnterTransition = {
//                slideInHorizontally(
//                    initialOffsetX = { -300 },
//                    animationSpec = tween(
//                        durationMillis = 300,
//                        easing = FastOutSlowInEasing
//                    )
//                ) + fadeIn(animationSpec = tween(durationMillis = 300))
//            },
        ) {
            HomeScreen(navHostController)
        }
        composable(
            route = Screen.Search.route,
//            enterTransition = {
//                slideInHorizontally(
//                    initialOffsetX = { 300 },
//                    animationSpec = tween(
//                        durationMillis = 300,
//                        easing = FastOutSlowInEasing
//                    )
//                ) + fadeIn(animationSpec = tween(durationMillis = 300))
//            },
//            popExitTransition = {
//                slideOutHorizontally(
//                    targetOffsetX = { 300 },
//                    animationSpec = tween(
//                        durationMillis = 300,
//                        easing = FastOutSlowInEasing
//                    )
//                ) + fadeOut(animationSpec = tween(durationMillis = 300))
//            },
        ) {
            SearchScreen(searchViewModel = searchViewModel, navController = navHostController)
        }
    }
}
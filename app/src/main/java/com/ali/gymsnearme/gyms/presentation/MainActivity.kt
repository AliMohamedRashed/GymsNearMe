package com.ali.gymsnearme.gyms.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.ali.gymsnearme.gyms.presentation.details.GymsDetailsScreen
import com.ali.gymsnearme.gyms.presentation.details.GymsDetailsViewModel
import com.ali.gymsnearme.gyms.presentation.gymslist.GymScreen
import com.ali.gymsnearme.gyms.presentation.gymslist.GymsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymsAroundApp()
        }
    }
}
@Composable
private fun GymsAroundApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "gyms") {
        composable(route = "gyms") {
            val vm: GymsViewModel = hiltViewModel()
            GymScreen(
                state = vm.state.value,
                onItemClicked = { id ->
                    navController.navigate("gyms/$id")
                },
                onFavouriteIconClicked = {id, oldValue ->
                    vm.toggleFavouriteState(id,oldValue)
                }
            )
        }
        composable(
            route = "gyms/{gym_id}",
            arguments = listOf(navArgument("gym_id") {
                type = NavType.IntType
            }),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://www.gymsaround.com/details/{gym_id}"
                }
            ),
        ) {
            val vm: GymsDetailsViewModel = viewModel()
            GymsDetailsScreen(
                state = vm.state.value
            )
        }
    }
}



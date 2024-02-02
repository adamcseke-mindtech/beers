package mindtech.adam.beers.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import mindtech.adam.beers.Data.Models.Beer
import mindtech.adam.beers.Modules.Details.DetailsScreen
import mindtech.adam.beers.Modules.MainViewModel
import mindtech.adam.beers.Modules.Pager.PagerScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()

    NavHost(navController,
        startDestination = Screen.PagerScreen.route) {
        composable(Screen.PagerScreen.route) {
            PagerScreen(navController = navController,
                viewModel = viewModel)
        }

        composable(Screen.DetailsScreen.route) {
            DetailsScreen(viewModel = viewModel)
        }
    }
}
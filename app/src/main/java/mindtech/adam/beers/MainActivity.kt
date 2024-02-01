package mindtech.adam.beers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mindtech.adam.beers.Modules.MainViewModel
import mindtech.adam.beers.Modules.Pager.PagerScreen
import mindtech.adam.beers.ui.theme.BeersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BeersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "shoppinglistscreen") {
        composable("shoppinglistscreen") {
            PagerScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BeersTheme {
    }
}
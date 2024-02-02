package mindtech.adam.beers.Modules.Views

import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppBarView(
    title: String,
){
    TopAppBar(
        title = {
            Text(text = title,
                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge
            )
        },
        elevation = 3.dp,
        backgroundColor = MaterialTheme.colorScheme.background,
    )
}
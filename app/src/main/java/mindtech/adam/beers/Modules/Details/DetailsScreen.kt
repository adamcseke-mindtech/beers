package mindtech.adam.beers.Modules.Details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import mindtech.adam.beers.Modules.MainViewModel

@Composable
fun DetailsScreen(viewModel: MainViewModel) {
    val beer = viewModel.selectedBeer

    Box {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxSize()
        ) {
            item {
                Column {
                    Text(
                        modifier = Modifier
                            .padding(top = 75.dp),
                        text = beer.name,
                        color = Color.Black,
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 24.dp, bottom = 12.dp),
                            text = beer.tagline,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Image(
                            painter = rememberAsyncImagePainter(model = beer.imageURL),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(100.dp)
                                .weight(1f)
                                .padding(top = 12.dp)
                        )
                    }

                    Text(
                        modifier = Modifier
                            .padding(top = 24.dp, bottom = 12.dp),
                        text = beer.description
                    )

                    Divider(modifier = Modifier
                        .padding(end = 12.dp))

                    Text(
                        modifier = Modifier
                            .padding(top = 24.dp, bottom = 12.dp),
                        text = "Tips for brewing: " + beer.brewersTips
                    )

                    Divider(modifier = Modifier
                        .padding(end = 12.dp))

                    Text(
                        modifier = Modifier
                            .padding(top = 24.dp, bottom = 12.dp),
                        text = "First brewed: " + beer.firstBrewed
                    )

                    Divider(modifier = Modifier
                        .padding(end = 12.dp))

                    Text(
                        modifier = Modifier
                            .padding(top = 24.dp),
                        text = "Best with these foods:"
                    )
                    beer.foodPairing.forEach() {food ->
                        Text(
                            modifier = Modifier
                                .padding(bottom = 12.dp),
                            text = food
                        )
                    }

                    Divider(modifier = Modifier
                        .padding(end = 12.dp))

                    Text(
                        modifier = Modifier
                            .padding(top = 24.dp, bottom = 12.dp),
                        text = "Contributed by: " + beer.contributedBy
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0x66000000),
                            Color.Transparent
                        )
                    )
                )
        )
    }
}
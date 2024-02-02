package mindtech.adam.beers.Modules.Pager.Views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import mindtech.adam.beers.Data.Models.Beer
import mindtech.adam.beers.Modules.MainViewModel
import mindtech.adam.beers.ui.theme.DislikeRed
import mindtech.adam.beers.ui.theme.LikeGreen

@Composable
fun BeerItem(beer: Beer, viewModel: MainViewModel, onPageChange: () -> Unit) {
    Surface(color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .padding(top = 54.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = rememberAsyncImagePainter(beer.imageURL),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize(0.5F)
            )

            Text(modifier = Modifier
                .padding(
                    horizontal = 12.dp,
                    vertical = 12.dp
                ),
                text = beer.name)

            Text(modifier = Modifier
                .padding(
                    horizontal = 12.dp,
                    vertical = 12.dp
                ),
                text = beer.tagline)

            Row {
                Button(onClick = {
                    viewModel.deleteBeer(beer)
                    onPageChange()
                },
                    colors = ButtonDefaults.buttonColors(DislikeRed),
                    modifier = Modifier
                        .padding(
                            horizontal = 12.dp,
                            vertical = 12.dp
                        ))
                {
                    Text(text = "Dislike")
                }

                Button(onClick = {
                    viewModel.saveBeer(beer)
                    onPageChange()
                },
                    colors = ButtonDefaults.buttonColors(LikeGreen),
                    modifier = Modifier
                        .padding(
                            horizontal = 12.dp,
                            vertical = 12.dp
                        ))
                {
                    Text(text = "Like")
                }
            }
        }
    }
}
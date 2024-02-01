package mindtech.adam.beers.Modules.Pager

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mindtech.adam.beers.Modules.MainViewModel
import mindtech.adam.beers.Network.Models.Beer
import coil.compose.rememberAsyncImagePainter
import mindtech.adam.beers.R
import mindtech.adam.beers.ui.theme.DislikeRed
import mindtech.adam.beers.ui.theme.LikeGreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerView(modifier: Modifier = Modifier,
              viewModel: MainViewModel,
              navController: NavController,
              context: Context) {

    val viewState by viewModel.beersState
    val pagerState = rememberPagerState(pageCount = { viewState.list.count() })

    HorizontalPager(state = pagerState) { page ->
        // Ensure the page index is within the list range to avoid IndexOutOfBoundsException
        if (page >= 0 && page < viewState.list.size) {
            val beer = viewState.list[page]
            BeerItem(beer = beer, context = context)
        }
    }

}

@Composable
fun BeerItem(beer: Beer, context: Context) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
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
                Toast.makeText(context,
                    "You disliked the " + beer.name + " beer.", Toast.LENGTH_LONG)
                    .show()
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
                Toast.makeText(context,
                    "You liked® the " + beer.name + " beer.", Toast.LENGTH_LONG)
                    .show()
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
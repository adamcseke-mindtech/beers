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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import mindtech.adam.beers.Modules.MainViewModel
import mindtech.adam.beers.Data.Models.Beer
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import mindtech.adam.beers.Modules.Pager.Views.BeerItem
import mindtech.adam.beers.ui.theme.DislikeRed
import mindtech.adam.beers.ui.theme.LikeGreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerScreen(viewModel: MainViewModel = hiltViewModel()) {

    val viewState by viewModel.beersState
    val pagerState = rememberPagerState(pageCount = { viewState.list.count() })
    val scope = rememberCoroutineScope()

    HorizontalPager(state = pagerState, userScrollEnabled = false) { page ->
        if (page < viewState.list.size - 1) {
            val beer = viewState.list[page]
            BeerItem(beer = beer,
                viewModel = viewModel,
                onPageChange = {
                    scope.launch {
                        val nextPage = (pagerState.currentPage + 1) % viewState.list.size
                        pagerState.scrollToPage(nextPage)
                    }
            })
        } else {
            FavoriteBeersList(favoritBeers = viewModel.currentFavorites)
        }
    }
}

@Composable
fun FavoriteBeersList(favoritBeers: List<Beer>) {
    Column {
        favoritBeers.forEach { beer ->
            Text(beer.name)
        }
    }
}
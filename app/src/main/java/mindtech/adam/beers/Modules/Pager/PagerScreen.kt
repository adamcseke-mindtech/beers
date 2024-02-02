package mindtech.adam.beers.Modules.Pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Scaffold
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mindtech.adam.beers.Modules.MainViewModel
import mindtech.adam.beers.Data.Models.Beer
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import mindtech.adam.beers.Modules.Pager.Views.BeerItem
import mindtech.adam.beers.Modules.Views.AppBarView
import mindtech.adam.beers.Navigation.Screen
import mindtech.adam.beers.ui.theme.BeersTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerScreen(viewModel: MainViewModel,
                navController: NavController
) {

    val viewState by viewModel.beersState
    val pagerState = rememberPagerState(pageCount = { viewState.list.count() })
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val navTitle = remember { mutableStateOf("") }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AppBarView(title = navTitle.value)}
    ) { it
        HorizontalPager(state = pagerState, userScrollEnabled = false) { page ->
            if (pagerState.currentPage < viewState.list.size - 1) {
                navTitle.value = "Beers"
            } else {
                navTitle.value = "Favorite Beers"
            }

            if (page < viewState.list.size - 1) {
                val beer = viewState.list[page]
                    BeerItem(beer = beer,
                        viewModel = viewModel,
                        onPageChange = {
                            scope.launch {
                                val nextPage = (pagerState.currentPage + 1) % viewState.list.size
                                pagerState.animateScrollToPage(nextPage)
                            }
                        }, onClick = {
                            viewModel.selectBeer(beer)
                            navController.navigate(Screen.DetailsScreen.route)
                        }
                    )
            } else {
                FavoriteBeersList(favoriteBeers = viewModel.currentFavorites,
                    onBeerClick = { beer ->
                        viewModel.selectBeer(beer)
                        navController.navigate(Screen.DetailsScreen.route)
                    }
                )
            }
        }
    }
}

@Composable
fun FavoriteBeersList(favoriteBeers: List<Beer>, onBeerClick: (Beer) -> Unit) {
        LazyColumn {
            items(favoriteBeers) {beer ->
                Row(modifier = Modifier
                    .clickable { onBeerClick(beer) }
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(beer.imageURL),
                        contentDescription = null,
                        modifier = Modifier
                            .size(128.dp)
                            .padding(
                                top = 22.dp,
                            )
                    )

                    Column {
                        Text(modifier = Modifier
                            .padding(
                                top = 12.dp,
                            ),
                            text = beer.name)

                        Text(modifier = Modifier
                            .padding(
                                bottom = 12.dp
                            ),
                            text = beer.tagline)
                    }
                }
                Divider(modifier = Modifier
                    .padding(horizontal = 12.dp,
                        vertical = 12.dp),
                )
            }
    }
}

//fun getDummyBeers(): List<Beer> {
//    return listOf(
//        Beer(id = 1, name = "Beer 1", tagline = "Tagline 1", imageURL = "https://images.punkapi.com/v2/7.png", description = "desc"),
//        Beer(id = 2, name = "Beer 2", tagline = "Tagline 2", imageURL = "https://images.punkapi.com/v2/2.png", description = ""),
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun BeerListPreview() {
//    BeersTheme {
//        FavoriteBeersList(favoriteBeers = getDummyBeers(), onBeerClick = {})
//    }
//}
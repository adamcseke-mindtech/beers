package mindtech.adam.beers.Navigation

sealed class Screen(val route:String) {
    object PagerScreen: Screen("beerPagerScreen")
    object DetailsScreen: Screen("beerDetailsScreen")
}
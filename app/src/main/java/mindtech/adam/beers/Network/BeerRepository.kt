package mindtech.adam.beers.Network

import javax.inject.Inject

class BeerRepository @Inject constructor(private val beerService: BeerService) {
    suspend fun getAllBeer() = beerService.getBeers()
}
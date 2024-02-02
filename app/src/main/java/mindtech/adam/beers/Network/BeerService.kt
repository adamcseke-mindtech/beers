package mindtech.adam.beers.Network

import mindtech.adam.beers.Data.Models.Beers
import retrofit2.http.GET
import javax.inject.Inject

interface BeerService {
    @GET("beers")
    suspend fun getBeers(): Beers
}
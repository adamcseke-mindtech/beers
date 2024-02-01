package mindtech.adam.beers.Network

import mindtech.adam.beers.Network.Models.Beers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofitBeer = Retrofit.Builder()
    .baseUrl("https://api.punkapi.com/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val beerService: BeerService = retrofitBeer.create(BeerService::class.java)

interface BeerService {
    @GET("beers")
    suspend fun getBeers():Beers
}
package mindtech.adam.beers.Modules

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mindtech.adam.beers.Data.Models.Beer
import mindtech.adam.beers.Network.BeerRepository
import mindtech.adam.beers.Network.BeerService
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val beerRepository: BeerRepository) : ViewModel() {

    private val _beerState = mutableStateOf(BeerState())
    val beersState: State<BeerState> = _beerState

    private val _favoriteBeers = mutableStateOf<List<Beer>>(listOf())
    val currentFavorites = _favoriteBeers.value.toMutableList()

    init {
        fetchBeers()
    }

    fun deleteBeer(beer: Beer) {
        if (currentFavorites.contains(beer)) {
            currentFavorites.remove(beer)
            _favoriteBeers.value = currentFavorites
            Log.i("SaveBeer", "You removed this " + beer.name + " beer.")
            Log.i("SaveBeer", currentFavorites.toString())
        } else {
            Log.i("SaveBeer", "You haven't saved this " + beer.name + " beer before.")
        }
    }

    fun saveBeer(beer: Beer) {
        if (!currentFavorites.contains(beer)) {
            currentFavorites.add(beer)
            _favoriteBeers.value = currentFavorites
            Log.i("SaveBeer", "You saved this " + beer.name + " beer.")
            Log.i("SaveBeer", currentFavorites.toString())
        } else {
            Log.i("SaveBeer", "You already saved this " + beer.name + " beer.")
        }
    }

    private fun fetchBeers(){
        viewModelScope.launch {
            try {
                val response = beerRepository.getAllBeer()
                val randomTenBeers = response.shuffled().take(10)

                _beerState.value = _beerState.value.copy(
                    list = randomTenBeers,
                    loading = false,
                    error = null
                )
                Log.i("viewmodel response", response.toString())
            } catch (e: Exception) {
                _beerState.value = _beerState.value.copy(
                    loading = false,
                    error = "Error fetching Beers ${e.message}"
                )
                Log.e("viewmodel error", beersState.value.error.toString())
            }
        }
    }

    data class BeerState(
        val loading: Boolean = true,
        val list: List<Beer> = emptyList(),
        val error: String? = null
    )

}
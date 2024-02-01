package mindtech.adam.beers.Modules

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mindtech.adam.beers.Network.Models.Beer
import mindtech.adam.beers.Network.beerService

class MainViewModel : ViewModel() {

    private val _beerState = mutableStateOf(BeerState())
    val beersState: State<BeerState> = _beerState

    init {
        fetchBeers()
    }

    private fun fetchBeers(){
        viewModelScope.launch {
            try {
                val response = beerService.getBeers()
                val uniqueBeers = response.distinct() // Removes duplicates

                val selectedBeers = if (uniqueBeers.size >= 10) {
                    uniqueBeers.shuffled().take(10)
                } else {
                    uniqueBeers
                }

                _beerState.value = _beerState.value.copy(
                    list = selectedBeers,
                    loading = false,
                    error = null
                )
                Log.i("Beer Service response", response.toString())
                Log.i("Selected beers", selectedBeers.toString())
            } catch (e: Exception) {
                _beerState.value = _beerState.value.copy(
                    loading = false,
                    error = "Error fetching Beers ${e.message}"
                )
                Log.e("error", beersState.value.error.toString())
            }
        }
    }

    data class BeerState(
        val loading: Boolean = true,
        val list: List<Beer> = emptyList(),
        val error: String? = null
    )

}
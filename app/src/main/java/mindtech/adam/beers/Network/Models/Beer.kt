package mindtech.adam.beers.Network.Models

import com.google.gson.annotations.SerializedName

data class Beer (
    val id: Long,
    val name: String,
    val tagline: String,
    @SerializedName("image_url")
    val imageURL: String
)

typealias Beers = List<Beer>
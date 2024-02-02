package mindtech.adam.beers.Data.Models

import com.google.gson.annotations.SerializedName

data class Beer (
    val id: Long,
    val name: String,
    val tagline: String,
    val description: String,

    @SerializedName("image_url")
    val imageURL: String,

    @SerializedName("first_brewed")
    val firstBrewed: String,

    @SerializedName("brewers_tips")
    val brewersTips: String,

    @SerializedName("contributed_by")
    val contributedBy: String,

    @SerializedName("food_pairing")
    val foodPairing: Foods,

) {
    companion object {
        val empty: Beer
            get() = Beer(
                id = 0L,
                name = "",
                tagline = "",
                description = "",
                imageURL = "",
                firstBrewed = "",
                brewersTips = "",
                contributedBy = "",
                foodPairing = mutableListOf("")
            )
    }
}

typealias Beers = List<Beer>

typealias Foods = List<String>

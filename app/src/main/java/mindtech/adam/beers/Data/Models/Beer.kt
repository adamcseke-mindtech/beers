package mindtech.adam.beers.Data.Models

import com.google.gson.annotations.SerializedName

data class Beer (
    val id: Long,
    val name: String,
    val tagline: String,
    @SerializedName("image_url")
    val imageURL: String
) {
    companion object {
        val empty: Beer
            get() = Beer(
                id = 0L,
                name = "",
                tagline = "",
                imageURL = ""
            )
    }
}

typealias Beers = List<Beer>
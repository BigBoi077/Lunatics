package cegepst.example.lunatics.models

import com.google.gson.annotations.SerializedName

class Game(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("background_image") val imageUrl: String,
    @SerializedName("rating") val rating: Int,
    @SerializedName("metacritic") val metacritic: Int,
    @SerializedName("released") val released: String,
    val singleGamePage: String
)
package cegepst.example.lunatics.models.baseModels

import com.google.gson.annotations.SerializedName

class Genre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("games_count") val gameCount: Int,
    @SerializedName("image_background") val imageUrl: String
)

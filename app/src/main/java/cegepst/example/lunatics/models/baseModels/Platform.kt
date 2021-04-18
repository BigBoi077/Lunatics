package cegepst.example.lunatics.models.baseModels

import com.google.gson.annotations.SerializedName

data class Platform(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("games_count") val gameCount: Int,
    @SerializedName("image_background") val backgroundImage: String,
    @SerializedName("image") val mainImage: String,
    @SerializedName("year_start") val yearStart: Int,
    @SerializedName("year_end") val yearEnd: Int
)

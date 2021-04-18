package cegepst.example.lunatics.models.baseModels

import com.google.gson.annotations.SerializedName

class Game(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("description") val description: String,
        @SerializedName("background_image") val imageUrl: String,
        @SerializedName("rating") val rating: String,
        @SerializedName("metacritic") val metacritic: String?,
        @SerializedName("released") val released: String,
        @SerializedName("website") val website: String,
        @SerializedName("platforms") val platformParents: List<PlatformParent>
)
package cegepst.example.lunatics.models.baseModels

import com.google.gson.annotations.SerializedName

class Achievement(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("description") val description: String,
        @SerializedName("image") val imageUrl: String,
        @SerializedName("percent") val successPercentage: String
)

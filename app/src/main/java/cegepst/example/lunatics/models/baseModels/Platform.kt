package cegepst.example.lunatics.models.baseModels

import com.google.gson.annotations.SerializedName

class Platform(
        @SerializedName("platform") val content: PlatformContent
)

class PlatformContent(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String
)

package cegepst.example.lunatics.models.results

import cegepst.example.lunatics.models.baseModels.Platform
import com.google.gson.annotations.SerializedName

class PlatformResult(
    @SerializedName("results") val platforms: List<Platform>
)
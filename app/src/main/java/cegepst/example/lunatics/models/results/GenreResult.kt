package cegepst.example.lunatics.models.results

import cegepst.example.lunatics.models.baseModels.Genre
import com.google.gson.annotations.SerializedName

class GenreResult(
    @SerializedName("results") val genres: List<Genre>,
    @SerializedName("next") val next: String
)
package cegepst.example.lunatics.models.restults

import cegepst.example.lunatics.models.baseModels.Game
import com.google.gson.annotations.SerializedName

class GameResult(
        @SerializedName("results") val games: List<Game>,
        @SerializedName("next") val next: String,
        @SerializedName("previous") val previous: String,
        @SerializedName("count") val count: Int
)
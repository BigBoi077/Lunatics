package cegepst.example.lunatics.models

import com.google.gson.annotations.SerializedName

class GameResult(
        @SerializedName("results") val games: List<Game>
)
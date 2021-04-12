package cegepst.example.lunatics.models.results

import cegepst.example.lunatics.models.baseModels.Achievement
import com.google.gson.annotations.SerializedName

class AchievementsResults(
        @SerializedName("results") val achievements: ArrayList<Achievement>
)
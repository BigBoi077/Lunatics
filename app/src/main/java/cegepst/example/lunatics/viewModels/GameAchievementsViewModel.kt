package cegepst.example.lunatics.viewModels

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cegepst.example.lunatics.models.baseModels.Achievement
import cegepst.example.lunatics.models.managers.ErrorManager
import cegepst.example.lunatics.models.managers.LoadingManager
import cegepst.example.lunatics.models.results.AchievementsResults
import cegepst.example.lunatics.services.RawgService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameAchievementsViewModel : ViewModel() {

    val achievements = MutableLiveData(listOf<Achievement>())
    private val loadingManager = LoadingManager()
    private val errorManager = ErrorManager()

    private val rawgService by lazy {
        RawgService.create()
    }

    fun giveComponents(textView: TextView) {
        errorManager.setComponents(textView)
    }

    fun fetchGameAchievements(gameId: Int, lambda: (ArrayList<Achievement>) -> Unit) {
        rawgService.getAchievements(gameId.toString())
            .enqueue(object : Callback<AchievementsResults> {
                override fun onResponse(
                    call: Call<AchievementsResults>,
                    response: Response<AchievementsResults>
                ) {
                    achievements.value = response.body()?.achievements
                    lambda(achievements.value!! as ArrayList<Achievement>)
                }

                override fun onFailure(call: Call<AchievementsResults>, t: Throwable) {
                    errorManager.raiseError(t.message ?: "Unrecognized error")
                }
            })
    }
}
package cegepst.example.lunatics.viewModels

import android.widget.TextView
import androidx.lifecycle.ViewModel
import cegepst.example.lunatics.models.baseModels.Game
import cegepst.example.lunatics.models.managers.ErrorManager
import cegepst.example.lunatics.models.managers.LoadingManager
import cegepst.example.lunatics.models.results.GameResult
import cegepst.example.lunatics.services.RawgService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SameSeriesViewModel : ViewModel() {

    private val loadingManager = LoadingManager()
    private val errorManager = ErrorManager()

    private val rawgService by lazy {
        RawgService.create()
    }

    fun giveComponents(textView: TextView) {
        errorManager.setComponents(textView)
    }

    fun fetchSameSeriesGame(gameId: Int, lambda: (ArrayList<Game>) -> Unit) {
        loadingManager.isLoading()
        rawgService.getAllSameGames(gameId.toString(), RawgService.API_KEY)
            .enqueue(object : Callback<GameResult> {
                override fun onResponse(call: Call<GameResult>, response: Response<GameResult>) {
                    lambda(response.body()?.games as ArrayList<Game>)
                    loadingManager.isSuccess()
                }

                override fun onFailure(call: Call<GameResult>, t: Throwable) {
                    errorManager.raiseError(t.message ?: "Unrecognized error")
                    loadingManager.isError()
                }
            })
    }
}
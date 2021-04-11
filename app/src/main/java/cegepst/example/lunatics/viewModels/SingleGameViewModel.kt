package cegepst.example.lunatics.viewModels

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import cegepst.example.lunatics.models.baseModels.Game
import cegepst.example.lunatics.models.managers.ErrorManager
import cegepst.example.lunatics.models.managers.LoadingManager
import cegepst.example.lunatics.services.RawgService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val API_KEY = "762f85b6be7c4c90ba98b1c82b67a075"

class SingleGameViewModel : ViewModel() {

    private val loadingManager = LoadingManager()
    private val errorManager = ErrorManager()

    private val rawgService by lazy {
        RawgService.create()
    }

    fun giveComponents(textView: TextView) {
        errorManager.setComponents(textView)
    }

    fun fetchSingleGames(gameId: Int, lambda: (Game) -> Unit) {
        rawgService.getSingleGame(gameId.toString()).enqueue(object : Callback<Game> {
            override fun onResponse(call: Call<Game>, response: Response<Game>) {

                Log.d("RESPONSE", response.raw().toString())
                Log.d("GAME ID", gameId.toString())

                val content = response.body()
                val game = Game(
                    gameId,
                    content!!.name,
                    content.imageUrl,
                    content.rating,
                    content.metacritic,
                    content.released,
                    content.website,
                    content.platforms
                )
                lambda(game)
            }

            override fun onFailure(call: Call<Game>, t: Throwable) {
                errorManager.raiseError(t.message ?: "Unrecognized error")
            }
        })
    }
}
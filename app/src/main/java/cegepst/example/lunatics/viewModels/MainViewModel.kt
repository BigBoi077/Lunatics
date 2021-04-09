package cegepst.example.lunatics.viewModels

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.lunatics.models.ErrorManager
import cegepst.example.lunatics.models.Game
import cegepst.example.lunatics.models.GameResult
import cegepst.example.lunatics.models.LoadingManager
import cegepst.example.lunatics.services.RawgService
import cegepst.example.lunatics.views.GameAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val API_KEY = "762f85b6be7c4c90ba98b1c82b67a075"

class MainViewModel(component: TextView) : ViewModel() {

    private lateinit var errorManager: ErrorManager
    private val games = MutableLiveData(listOf<Game>())
    lateinit var adapter: GameAdapter
    private val loadingManager = LoadingManager()

    private val rawgService by lazy {
        RawgService.create()
    }

    fun getGames(): LiveData<List<Game>> {
        return games
    }

    fun giveComponents(textView: TextView, recyclerView: RecyclerView) {
        errorManager.setComponents(textView, recyclerView)
    }

    fun fetchGames() {
        loadingManager.isLoading()
        rawgService.getGames(API_KEY).enqueue(object : Callback<GameResult> {
            override fun onResponse(call: Call<GameResult>, response: Response<GameResult>) {
                games.value = response.body()!!.games
                loadingManager.isSuccess()
            }

            override fun onFailure(call: Call<GameResult>, t: Throwable) {
                errorManager.raiseError(t.message ?: "Unrecognized error")
                loadingManager.isError()
            }
        })
    }
}
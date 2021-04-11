package cegepst.example.lunatics.viewModels

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cegepst.example.lunatics.models.baseModels.Game
import cegepst.example.lunatics.models.managers.ErrorManager
import cegepst.example.lunatics.models.managers.LoadingManager
import cegepst.example.lunatics.models.restults.GameResult
import cegepst.example.lunatics.services.RawgService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val API_KEY = "762f85b6be7c4c90ba98b1c82b67a075"

class MainViewModel : ViewModel() {

    var wantedSize = 10
    val games = MutableLiveData(listOf<Game>())
    private val loadingManager = LoadingManager()
    private val errorManager = ErrorManager()

    private val rawgService by lazy {
        RawgService.create()
    }

    fun getGames(): LiveData<List<Game>> {
        return games
    }

    fun giveComponents(textView: TextView) {
        errorManager.setComponents(textView)
    }

    fun fetchGames() {
        loadingManager.isLoading()
        rawgService.getGames(API_KEY, wantedSize).enqueue(object : Callback<GameResult> {
            override fun onResponse(call: Call<GameResult>, response: Response<GameResult>) {
                if (games.value!!.isEmpty()) {
                    games.value = response.body()!!.games
                } else {
                    makeTempList(games, response)
                }
                loadingManager.isSuccess()
                wantedSize += 10
            }

            override fun onFailure(call: Call<GameResult>, t: Throwable) {
                errorManager.raiseError(t.message ?: "Unrecognized error")
                loadingManager.isError()
            }
        })
    }

    private fun makeTempList(data: MutableLiveData<List<Game>>, response: Response<GameResult>) {
        val list = data.value as ArrayList<Game>
        list.clear()
        list.addAll(response.body()!!.games)
        data.value = list
    }
}